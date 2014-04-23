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
import android.widget.TextView;

import com.sio.arbimatch.classesM.Fait;
import com.sio.arbimatch.classesM.Joueur;
import com.sio.arbimatch.classesM.Match;
import com.sio.arbimatch.classesM.Remplacement;
import com.sio.arbimatch.sql.MatchDAO;

import java.util.ArrayList;

/**
 * Created by Gatien on 06/12/13.
 */
public class Remplacement_Activity extends Activity {
    MatchDAO GDAO = new MatchDAO(Remplacement_Activity.this);
    ImageButton bt_retour;
    RadioButton rb_e1;
    RadioButton rb_e2;
    TextView txt1;
    ArrayAdapter<String> adapter;
    ListView list;
    ListView listJ2;
    TextView txt_statut;
    ArrayList<Joueur> listjoueurs;
    ArrayList<Joueur> listjoueurs2;
    int e1 = 0;
    int e2 = 0;
    static Integer temps = MatchingActivity.temps;
    static Joueur joueurIn;
    static Joueur joueurOut;
    public static Match lematch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remplacement_e);
        bt_retour = (ImageButton) findViewById(R.id.bt_retour);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText("Joueur sortant :");
        rb_e1 = (RadioButton) findViewById(R.id.rb_e1);
        //rb_e1.setText(lematch.getEquipe1().getNom());
        rb_e2 = (RadioButton) findViewById(R.id.rb_e2);
        //rb_e2.setText(lematch.getEquipe1().getNom());
        list = (ListView) findViewById(R.id.listJ);
        txt_statut = (TextView) findViewById(R.id.txt_statut);
        listJ2 = (ListView) findViewById(R.id.listJ2);
        listJ2.setVisibility(View.GONE);

        e2=0;
        e1=1;
        String[] liste1;
        listjoueurs = MatchingActivity.lematch.getE1joueursTerrain();


        liste1 = new String[listjoueurs.size()];
        int i = 0;
        for (Joueur j : listjoueurs){
            liste1[i] = j.getPrenom() + " " + j.getNom();
            i++;
        }
        listEquipe(liste1);

        bt_retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        rb_e1.setOnClickListener(new View.OnClickListener() { // si l'equipe 1 est selectionné on affiche la liste des joueurs sur le terrain de l'equipe 1
            @Override
            public void onClick(View v) {
                e2=0;
                e1=1;
                String[] liste1;
                listjoueurs = MatchingActivity.lematch.getE1joueursTerrain();


                liste1 = new String[listjoueurs.size()];
                int i = 0;
                for (Joueur j : listjoueurs){
                    liste1[i] = j.getPrenom() + " " + j.getNom();
                    i++;
                }
                listEquipe(liste1);
            }
        });
        rb_e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // si l'equipe 2 est selectionné on affiche la liste des joueurs sur le terrain de l'equipe 2
                e1 = 0;
                e2=1;
                String[] liste1;
                listjoueurs = MatchingActivity.lematch.getE2joueursTerrain();


                liste1 = new String[listjoueurs.size()];
                int i = 0;
                for (Joueur j : listjoueurs){
                    liste1[i] = j.getPrenom() + " " + j.getNom();
                    i++;
                }
                listEquipe(liste1);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                joueurOut = listjoueurs.get(position);
                rb_e1.setVisibility(View.GONE);
                rb_e2.setVisibility(View.GONE);
                list.setVisibility(View.GONE);
                txt1.setText("Joueur entrant :");
                listJ2.setVisibility(0);
                String[] liste2;
                if (e1 == 1){ // Si l'equipe 1 est sélectionné on affiche la liste des remplacent de l'equipe 1
                    listjoueurs2 = MatchingActivity.lematch.getE1listRemp();
                    liste2 = new String[listjoueurs2.size()];
                    int i = 0;
                    for (Joueur j : listjoueurs2){
                        liste2[i] = j.getPrenom() + " " + j.getNom();
                        i++;
                    }
                    listEquipe2(liste2);
                }
                if (e2 == 1) { // Si l'equipe 2 est sélectionné on affiche la liste des remplacent de l'equipe 2
                    listjoueurs2 = MatchingActivity.lematch.getE2listRemp();
                    liste2 = new String[listjoueurs2.size()];
                    int i = 0;
                    for (Joueur j : listjoueurs2){
                        liste2[i] = j.getPrenom() + " " + j.getNom();
                        i++;
                    }
                    listEquipe2(liste2);
                }
            }
        }
        );
        listJ2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                joueurIn = listjoueurs2.get(position);
                if (e1==1){
                    MatchingActivity.lematch.remplacement(joueurIn, joueurOut, 1);
                }else{
                    MatchingActivity.lematch.remplacement(joueurIn, joueurOut, 2);
                }
                listJ2.setVisibility(0);
                Fait f1 = new Remplacement(temps, joueurOut, joueurIn); // On crée un nouveau fait
                MatchingActivity.lematch.ajouterFait(f1);
                GDAO.remplacement(f1,joueurOut,joueurIn);

                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                Remplacement_Activity.this.finish();
            }
        }
        );

    }
    public void listEquipe(String[] listj){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listj);
        list.setAdapter(adapter);
    }
    public void listEquipe2(String[] listj){
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listj);
        listJ2.setAdapter(adapter);
    }
}