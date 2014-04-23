package com.sio.arbimatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.sio.arbimatch.classesM.Club;
import com.sio.arbimatch.classesM.Joueur;
import com.sio.arbimatch.classesM.Match;
import com.sio.arbimatch.MatchingActivity;

import java.util.ArrayList;

/**
 * Created by tsilva on 04/12/13.
 */
public class Choix_e1Activity extends Activity {
    ListView list;
    ArrayAdapter<String> adapter;
    static Club equipe1;
    @Override
    protected void onCreate(Bundle savedInstanceState) { // On crée une liste des clubs qu'on affiche
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_e1);
        final ArrayList<Club> listClub = Club.getListClub();
        String[] nomClub = new String[listClub.size()];
        int i = 0;
        for (Club c : listClub){
            nomClub[i] = c.getNom();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomClub);

        list = (ListView) findViewById(R.id.listequipe1);

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // Lors du clic sur un club on le selectionne et on le range dans le match
                equipe1 = listClub.get(position);
                MatchingActivity.lematch.setEquipe1(equipe1);
                listClub.remove(position);
                Choix_e2Activity.setListClub(listClub);
                Intent i = new Intent(Choix_e1Activity.this, Choix_e2Activity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    public static Club getEquipe1() {
        return equipe1;
    }
}