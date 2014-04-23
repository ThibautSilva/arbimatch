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

import com.sio.arbimatch.classesM.Joueur;

import java.util.ArrayList;

/**
 * Created by tsilva on 04/12/13.
 */
public class Rempl_e1Activity extends Activity {
    TextView compt_r1;
    ListView list;
    ArrayAdapter<String> adapter;
    ArrayList<Joueur> listRemplace = new ArrayList<Joueur>();
    String[] listJoueur;
    int compteur = 0;
    static ArrayList<Joueur> listJ = new ArrayList<Joueur>();

    public static void setListJ(ArrayList<Joueur> list) {
        listJ = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { // On choisis les remplacents dans la liste
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rempl_e1);
        compt_r1 = (TextView) findViewById(R.id.compt_r1);

        refresh();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                compteur++;
                compt_r1.setText(Integer.toString(compteur) + "/5");
                Joueur j1= listJ.get(position);
                listRemplace.add(j1);
                listJ.remove(position);

                refresh(); // On recharge la liste

                if (compteur == 4){
                    MatchingActivity.lematch.setE1listRemp(listRemplace);
                    Intent intent = new Intent(Rempl_e1Activity.this, Rempl_e2Activity.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
    public void refresh(){
        listJoueur = new String[listJ.size()];
        int i = 0;
        for (Joueur j : listJ){
            listJoueur[i] = j.getNom() + ' ' + j.getPrenom();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listJoueur);

        list = (ListView) findViewById(R.id.ListRemplace1);

        list.setAdapter(adapter);
    }
}
