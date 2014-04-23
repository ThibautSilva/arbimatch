package com.sio.arbimatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;

import com.sio.arbimatch.classesM.Carton;
import com.sio.arbimatch.classesM.Fait;
import com.sio.arbimatch.classesM.Joueur;
import com.sio.arbimatch.sql.MatchDAO;

import java.util.ArrayList;

public class Carton_Activity extends Activity {
    MatchDAO GDAO = new MatchDAO(Carton_Activity.this);
    ImageButton bt_retour;
    RadioButton rouge;
    RadioButton jaune;
    String couleur;
    Integer temps = MatchingActivity.temps;
    ArrayAdapter<String> adapter;
    ListView list;
    String[] ListJoueurs;
    ArrayList<Joueur> listj1;
    ArrayList<Joueur> listj2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carton);
        bt_retour = (ImageButton) findViewById(R.id.bt_retour);

        bt_retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        rouge = (RadioButton) findViewById(R.id.rb_rouge);
        jaune = (RadioButton) findViewById(R.id.rb_jaune);


        listj1 = MatchingActivity.lematch.getE1joueursTerrain();
        listj2 = MatchingActivity.lematch.getE2joueursTerrain();

        ListJoueurs = new String[listj1.size()+listj2.size()];
        int i = 0;
        for (Joueur j : listj1){
            ListJoueurs[i] = j.getNom()+ " "+j.getPrenom();
            i++;
        }
        for (Joueur j : listj2){
            ListJoueurs[i] = j.getNom()+ " "+j.getPrenom();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListJoueurs);
        list = (ListView) findViewById(R.id.listJoueur);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Joueur j1;
                if (position > listj1.size()-1){
                    int pos = position - listj1.size();
                    j1= listj2.get(position - listj1.size());
                }else{
                    j1= listj1.get(position);
                }
                if (rouge.isChecked()){
                    couleur = "rouge";
                    MatchingActivity.lematch.exclusion(position);
                    /*joueur exclu = retirer de la liste des joueurs sur le terrain*/
                }
                if (jaune.isChecked()){
                    couleur = "jaune";
                    int i = GDAO.secondCarton(j1);
                    if (i==1){
                        MatchingActivity.lematch.exclusion(position);
                    }
                    /*recherche si le joueur a deja eu un carton jaune*/
                }
                Fait f1 = new Carton(temps, couleur, j1);
                MatchingActivity.lematch.ajouterFait(f1);
                GDAO.carton(f1,couleur,j1);

                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                Carton_Activity.this.finish();
            }
        });
    }
}
