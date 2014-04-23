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
public class Titulaires_e1Activity extends Activity {
    TextView compt_e1;
    ListView list;
	int compteur = 0;
    ArrayList<Joueur> listTitu = new ArrayList<Joueur>();
    ArrayList<Joueur> joueurs;
    ArrayAdapter<String> adapter;
    String[] joueursEquipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //On affiche la liste des joueurs de l'equipe 1
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titulaires_e1);
        compt_e1 = (TextView) findViewById(R.id.compt_e1);

        Club equipe1 = MatchingActivity.lematch.getEquipe1();
        joueurs = equipe1.getListeJoueurs();

        refresh();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				compteur++;
                compt_e1.setText(Integer.toString(compteur) + "/11");
                Joueur j1= joueurs.get(position);
				listTitu.add(j1);
                joueurs.remove(position);

                refresh();

			   if (compteur == 4){ // Lorsque 4 joueurs on été séléctionné on envoie la liste des titulaires
			    MatchingActivity.lematch.setE1listTitu(listTitu);
                MatchingActivity.lematch.setE1joueursTerrain(listTitu);
                Rempl_e1Activity.setListJ(joueurs); // On envoie la liste des joueurs restant à l'activity remp_e1Activity
                Intent intent = new Intent(Titulaires_e1Activity.this, Titulaires_e2Activity.class);
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

        list = (ListView) findViewById(R.id.ListeTitulaires1);

        list.setAdapter(adapter);
    }
}
