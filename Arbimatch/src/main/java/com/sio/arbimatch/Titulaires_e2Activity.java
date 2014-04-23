package com.sio.arbimatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sio.arbimatch.classesM.Club;
import com.sio.arbimatch.classesM.Joueur;

import java.util.ArrayList;

/**
 * Created by tsilva on 04/12/13.
 */
public class Titulaires_e2Activity extends Activity {
    ListView list;
    TextView compt_e2;
    int compteur = 0;
    ArrayList<Joueur> listTitu = new ArrayList<Joueur>();
    ArrayList<Joueur> joueurs;
    ArrayAdapter<String> adapter;
    String[] joueursEquipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titulaires_e2);
        compt_e2 = (TextView) findViewById(R.id.compt_e2);

        Club equipe2 = MatchingActivity.lematch.getEquipe2();
        joueurs = equipe2.getListeJoueurs();

        refresh();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                compteur++;
                compt_e2.setText(Integer.toString(compteur) + "/11");
                Joueur j1= joueurs.get(position);
                listTitu.add(j1);
                joueurs.remove(position);

                refresh();

                if (compteur == 4){
                    MatchingActivity.lematch.setE2listTitu(listTitu);
                    MatchingActivity.lematch.setE2joueursTerrain(listTitu);
                    Rempl_e2Activity.setListJ(joueurs);
                    Intent intent = new Intent(Titulaires_e2Activity.this, Rempl_e1Activity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
    public void refresh(){
        joueursEquipe = new String[joueurs.size()];

        int i = 0;
        for (Joueur j : joueurs){
            joueursEquipe[i] = j.getNom()+ " "+j.getPrenom();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, joueursEquipe);

        list = (ListView) findViewById(R.id.ListeTitulaires2);

        list.setAdapter(adapter);
    }
}
