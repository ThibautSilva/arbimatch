package com.sio.arbimatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.sio.arbimatch.classesM.But;
import com.sio.arbimatch.classesM.Fait;
import com.sio.arbimatch.classesM.Joueur;
import com.sio.arbimatch.sql.MatchDAO;

import java.util.ArrayList;

/**
 * Created by Gatien on 06/12/13.
 */
public class But_e1Activity extends Activity {
    MatchDAO GDAO = new MatchDAO(But_e1Activity.this);
    String[] joueursEquipe;
    ArrayAdapter<String> adapter;
    ListView list;
    ArrayList<Joueur> joueurs;
    ImageButton bt_retour;
    Integer temps = MatchingActivity.temps;

    protected void onCreate(Bundle savedInstanceState) { // On affiche la liste des joueurs de l'equipe
        joueurs = MatchingActivity.lematch.getE1listTitu();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.but_e1);

        bt_retour = (ImageButton) findViewById(R.id.bt_retour);
        bt_retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        joueursEquipe = new String[joueurs.size()];

        int i = 0;
        for (Joueur j : joueurs){
            joueursEquipe[i] = j.getNom()+ " "+j.getPrenom();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, joueursEquipe);

        list = (ListView) findViewById(R.id.listbut1);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Joueur j1= joueurs.get(position);
                Fait f1 = new But(temps, j1); // on crée un nouveau fait
                MatchingActivity.lematch.ajouterFait(f1);
                GDAO.but(f1, j1);

                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                But_e1Activity.this.finish();
            }
        });
    }
}
