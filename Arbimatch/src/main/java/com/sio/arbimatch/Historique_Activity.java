package com.sio.arbimatch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.sio.arbimatch.classesM.Fait;
import com.sio.arbimatch.classesM.Match;
import com.sio.arbimatch.sql.MatchDAO;

import java.util.ArrayList;
import java.util.Collections;

public class Historique_Activity extends ActionBarActivity {

    ListView listmatch;
    ListView listfaits;
    TextView txt_e1;
    TextView txt_e2;
    ArrayList<String> listMatch = new ArrayList();
    MatchDAO dao = new MatchDAO(this);

    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historique);

        txt_e1 = (TextView) findViewById(R.id.txt_e1);
        txt_e2 = (TextView) findViewById(R.id.txt_e2);
        listfaits = (ListView) findViewById(R.id.listfaits);
        ImageButton bt_retour = (ImageButton) findViewById(R.id.bt_retour);
        bt_retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        listMatch = dao.getMatch();
        String[] ListDesMatchs = new String[listMatch.size()];

        int i = 0;
        for (String s : listMatch){
            ListDesMatchs[i] = s;
            i++;
        }


        listmatch = (ListView) findViewById(R.id.listmatch);

        listfaits = (ListView) findViewById(R.id.listfaits);

        listmatch.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListDesMatchs));

        listmatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                code = listMatch.get(position);
                Match m1 = dao.createOldMatch(code);

                ArrayList<Fait> listfait = m1.getListfait();
                Collections.sort(listfait);//On trie la liste par le chrono

                String[] ListDesFaits = new String[listfait.size()];

                int i = 0;
                for (Fait f : listfait){
                    ListDesFaits[i] = f.infofait();
                    i++;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Historique_Activity.this, android.R.layout.simple_list_item_1, ListDesFaits);

                ListView list = (ListView) findViewById(R.id.listfaits);

                list.setAdapter(adapter);

                txt_e1.setText(m1.getEquipe1().getNom() + " " + m1.getBute1());
                txt_e2.setText(m1.getEquipe2().getNom() + " " + m1.getBute2());

            }
        }
        );




    }


}
