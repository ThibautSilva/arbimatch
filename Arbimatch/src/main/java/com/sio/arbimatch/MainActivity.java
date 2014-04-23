package com.sio.arbimatch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sio.arbimatch.classesM.Club;
import com.sio.arbimatch.classesM.Joueur;
import com.sio.arbimatch.classesM.Match;
import com.sio.arbimatch.sql.EssaiDAO;

public class MainActivity extends ActionBarActivity {

    Button bt_hist;
    EssaiDAO GDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        bt_hist= (Button) findViewById(R.id.bt_hist);
        GDAO = new EssaiDAO(MainActivity.this);

        bt_hist.setOnClickListener(new View.OnClickListener() // view historique
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Historique_Activity.class);
                startActivityForResult(i,1);
            }
        });

        Button debutM = (Button) findViewById(R.id.bt_debut_match);

        debutM.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Match m1 = new Match();
                String codeM = ""+((EditText) findViewById((R.id.et_codeM))).getText();
                if (codeM.equals("")){
                    Toast.makeText(MainActivity.this, "Code match requis", Toast.LENGTH_SHORT).show();
                }
                else {
                    m1.setCodeM(codeM);
                    MatchingActivity.lematch = m1;
                    jeuEssai();
                    Intent i = new Intent(MainActivity.this, Choix_e1Activity.class);
                    startActivityForResult(i, 1);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
   public void jeuEssai(){


        //Jeu d'essai
        Club c1 = new Club(1,"PSG","Paris");
        Joueur j1 = new Joueur(1,"Sirugu","Salvatore","1992");
        Joueur j2 = new Joueur(2,"Lavezzi","Ezequiel","1993");
        Joueur j3 = new Joueur(3,"Da Silva","Thiago","1992");
        Joueur j4 = new Joueur(4,"Van der Wiel","Gregory","1993");
        Joueur j5 = new Joueur(5,"Cavani","Edinson","1992");
        Joueur j6 = new Joueur(6,"Matuidi","Blaise","1993");
        Joueur j7 = new Joueur(7,"Ibrahimovic","Zlatan","1992");
        Joueur j8 = new Joueur(9,"Verrati","Marco","1993");
        Joueur j9 = new Joueur(8,"Mouras","Lucas","1992");
        Joueur j10 = new Joueur(10,"Pastore","Javier","1993");
        Joueur j11 = new Joueur(11,"Rabiot","Adrien","1993");
        Club.ajouterClub(c1);
        c1.ajouterJoueur(j1);
        c1.ajouterJoueur(j2);
        c1.ajouterJoueur(j3);
        c1.ajouterJoueur(j4);
        c1.ajouterJoueur(j5);
        c1.ajouterJoueur(j6);
        c1.ajouterJoueur(j7);
        c1.ajouterJoueur(j8);
        c1.ajouterJoueur(j9);
        c1.ajouterJoueur(j10);
        c1.ajouterJoueur(j11);
        Club c2 = new Club(2, "FCB","Barcelone");
        Joueur j12 = new Joueur(21,"Messi","Lionel","1994");
        Joueur j13 = new Joueur(22,"Neymar","Jr","1996");
        Joueur j14 = new Joueur(12,"Iniesta","Andres","1994");
        Joueur j15 = new Joueur(13,"Puyol","Carles","1996");
        Joueur j16 = new Joueur(14,"Xavi","Hernandez","1994");
        Joueur j17 = new Joueur(15,"Sanchez","Alexis","1996");
        Joueur j18 = new Joueur(16,"Fabregas","Cesc","1994");
        Joueur j19 = new Joueur(17,"Pique","Gerard","1996");
        Joueur j20 = new Joueur(18,"Valdes","Victor","1996");
        Joueur j21 = new Joueur(19,"Alba","Jordi","1994");
        Joueur j22 = new Joueur(20,"Alves","Daniel","1996");
        Club.ajouterClub(c2);
        c2.ajouterJoueur(j12);
        c2.ajouterJoueur(j13);
        c2.ajouterJoueur(j14);
        c2.ajouterJoueur(j15);
        c2.ajouterJoueur(j16);
        c2.ajouterJoueur(j17);
        c2.ajouterJoueur(j18);
        c2.ajouterJoueur(j19);
        c2.ajouterJoueur(j20);
        c2.ajouterJoueur(j21);
        c2.ajouterJoueur(j22);

        int i = GDAO.vide();
        //On test si un jeu d'essai a deja été crée dans la base
        if (i==0){
            GDAO.createClub(c1);
            GDAO.createJoueur(j1,c1);
            GDAO.createJoueur(j2,c1);
            GDAO.createJoueur(j3,c1);
            GDAO.createJoueur(j4,c1);
            GDAO.createJoueur(j5,c1);
            GDAO.createJoueur(j6,c1);
            GDAO.createJoueur(j7,c1);
            GDAO.createJoueur(j8,c1);
            GDAO.createJoueur(j9,c1);
            GDAO.createJoueur(j10,c1);
            GDAO.createJoueur(j11,c1);

            GDAO.createClub(c2);
            GDAO.createJoueur(j14,c2);
            GDAO.createJoueur(j15,c2);
            GDAO.createJoueur(j16,c2);
            GDAO.createJoueur(j17,c2);
            GDAO.createJoueur(j18,c2);
            GDAO.createJoueur(j19,c2);
            GDAO.createJoueur(j20,c2);
            GDAO.createJoueur(j21,c2);
            GDAO.createJoueur(j22,c2);
        }
    }



}
