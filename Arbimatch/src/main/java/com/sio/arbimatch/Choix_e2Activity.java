package com.sio.arbimatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sio.arbimatch.classesM.Club;

import java.util.ArrayList;

/**
 * Created by tsilva on 04/12/13.
 */
public class Choix_e2Activity extends Activity {
    ListView list;
    ArrayAdapter<String> adapter;
    Club equipe1 = Choix_e1Activity.getEquipe1();
    static ArrayList<Club> listClub = new ArrayList<Club>();

    public static void setListClub(ArrayList<Club> list) {
        listClub = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_e2);
        String[] nomClub = new String[listClub.size()];
        int i = 0;
        for (Club c : listClub){
            nomClub[i] = c.getNom();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomClub);

        list = (ListView) findViewById(R.id.listequipe2);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Club equipe2;
                equipe2 = listClub.get(position);
                MatchingActivity.lematch.setEquipe2(equipe2);
                Intent i = new Intent(Choix_e2Activity.this, Titulaires_e1Activity.class);
                startActivityForResult(i, 1);
            }
        });

    }
}
