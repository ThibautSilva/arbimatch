package com.sio.arbimatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sio.arbimatch.classesM.Fait;
import com.sio.arbimatch.classesM.Match;
import com.sio.arbimatch.sql.MatchDAO;

import java.util.ArrayList;

public class MatchingActivity extends Activity implements Chronometer.OnChronometerTickListener {
    MatchDAO MDAO = new MatchDAO(MatchingActivity.this);
    ArrayAdapter<String> adapter;
    ListView list;
    String[] ListDesFaits;
    ArrayList<Fait> listfait;
    public static int temps;
    Chronometer chrono;
    Button bt_stop;
    Button bt_commencer;
    Button bt_commencer2;
    Button bt_carton;
    Button bt_remplacement;
    Button bt_but1;
    Button bt_but2;
    ImageButton bt_restart;
    ListView listE;
    TextView txt_e1;
    TextView txt_e2;
    TextView txt_mitemps;
    ProgressBar prog_mitemps;
    boolean mt;
    public static Match lematch;
    int but1=0;
    int but2=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) { // On affiche les boutons que l'on veut voir apparaitre
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);
        bt_restart = (ImageButton) findViewById(R.id.bt_restart);

        //On envoie les données recueillis dans la base
        MDAO.createMatch(lematch.getEquipe1(),lematch.getEquipe2());
        MDAO.listtitulaire(lematch.getE1listTitu());
        MDAO.listtitulaire(lematch.getE2listTitu());
        MDAO.listremplacent(lematch.getE1listRemp());
        MDAO.listremplacent(lematch.getE2listRemp());
        int i = MDAO.minIdf();
        Fait.setId(i);
        bt_commencer = (Button) findViewById(R.id.bt_start_chrono);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        bt_commencer2 = (Button) findViewById(R.id.bt_start_2nd);
        bt_carton = (Button) findViewById(R.id.bt_carton);
        bt_remplacement = (Button) findViewById(R.id.bt_remplacement);

        txt_e1 = (TextView) findViewById(R.id.txt_e1);
        txt_e1.setText(lematch.getEquipe1().getNom());

        txt_e2 = (TextView) findViewById(R.id.txt_e2);
        txt_e2.setText(lematch.getEquipe2().getNom());

        bt_but1 = (Button) findViewById(R.id.bt_but1);
        bt_but1.setText(Integer.toString(but1));

        bt_but2 = (Button) findViewById(R.id.bt_but2);
        bt_but2.setText(Integer.toString(but2));

        listE = (ListView) findViewById(R.id.listE);
        txt_mitemps = (TextView) findViewById(R.id.txt_mitemps);
        prog_mitemps = (ProgressBar) findViewById(R.id.prog_mitemps);

        chrono = ((Chronometer) findViewById(R.id.chrono));

        mt = false; // boolean mi temps

        bt_stop.setEnabled(false);
        bt_carton.setEnabled(false);
        bt_remplacement.setEnabled(false);
        bt_but1.setEnabled(false);
        bt_but2.setEnabled(false);
        bt_commencer2.setVisibility(View.GONE);
        txt_mitemps.setVisibility(View.GONE);
        prog_mitemps.setVisibility(View.GONE);

        bt_restart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        bt_but1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MatchingActivity.this, But_e1Activity.class);
                startActivityForResult(i,1);

            }
        });
        bt_but2.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MatchingActivity.this, But_e2Activity.class);
                startActivityForResult(i,2);
            }
        });
        bt_carton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MatchingActivity.this, Carton_Activity.class);
                startActivityForResult(i,3);
            }
        });
        bt_remplacement.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MatchingActivity.this, Remplacement_Activity.class);
                startActivityForResult(i,4);
            }
        });


        bt_commencer.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(MatchingActivity.this, "Début mi-temps", Toast.LENGTH_SHORT).show();
                MatchingActivity.this.startChronometer(null);
                bt_commencer.setVisibility(View.GONE); //On met invisible le bouton commencer
                bt_carton.setEnabled(true);
                bt_remplacement.setEnabled(true);
                bt_but1.setEnabled(true);
                bt_but2.setEnabled(true);

            }
        });

        bt_stop.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (mt == false){
                    Toast.makeText(MatchingActivity.this, "Début mi-temps", Toast.LENGTH_SHORT).show();
                    bt_commencer2.setVisibility(0);
                    txt_mitemps.setVisibility(0);
                    prog_mitemps.setVisibility(0);
                    mt = true;
                    bt_stop.setEnabled(false);
                    bt_remplacement.setEnabled(false);
                    bt_carton.setEnabled(false);
                    bt_but1.setEnabled(false);
                    bt_but2.setEnabled(false);
                    stopChronometer(null);
                }
                else {
                    Toast.makeText(MatchingActivity.this, "Fin du match", Toast.LENGTH_SHORT).show();
                    bt_stop.setEnabled(false);
                    bt_remplacement.setEnabled(false);
                    bt_carton.setEnabled(false);
                    bt_but1.setEnabled(false);
                    bt_but2.setEnabled(false);
                    stopChronometer(null);
                }

            }
        });


        bt_commencer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MatchingActivity.this, "Début 2nd mi-temps", Toast.LENGTH_SHORT).show();
                MatchingActivity.this.startChronometer2nd(null);
                chrono.setTextColor(Color.BLACK);
                bt_commencer2.setVisibility(View.GONE);
                bt_carton.setEnabled(true);
                bt_remplacement.setEnabled(true);
                bt_but1.setEnabled(true);
                bt_but2.setEnabled(true);
                txt_mitemps.setVisibility(View.GONE);
                prog_mitemps.setVisibility(View.GONE);
            }
        });


    }

    public void startChronometer(View view) {
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.setOnChronometerTickListener(this);
        ((Chronometer) findViewById(R.id.chrono)).start();
    }

    public void startChronometer2nd(View view) {
        Chronometer chrono = ((Chronometer) findViewById(R.id.chrono));
        //chrono.setFormat("MM:SS");
        chrono.setBase(SystemClock.elapsedRealtime() - (2700 * 1000));
        chrono.setOnChronometerTickListener(this);
        //chrono.setOnChronometerTickListener(this);
        ((Chronometer) findViewById(R.id.chrono)).start();
    }

    public void stopChronometer(View view) {
        ((Chronometer) findViewById(R.id.chrono)).stop();
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        long t = SystemClock.elapsedRealtime() - chronometer.getBase();
        int h   = (int)(t/3600000);
        int m = (int)(t - h*3600000)/60000;
        int s= (int)(t - h*3600000- m*60000)/1000 ;

        m = m + (h*60);
        String hh = h < 10 ? "0"+h: h+"";
        String mm = m < 10 ? "0"+m: m+"";
        String ss = s < 10 ? "0"+s: s+"";
        chronometer.setText(mm +":"+ss);

        MatchingActivity.temps = Integer.parseInt(mm);

        //ARRETS DE JEU
        if ("00:05".equals(chronometer.getText())) {
            // Create Intent and start the new Activity here
            Toast.makeText(MatchingActivity.this, "ARRETS DE JEU", Toast.LENGTH_SHORT).show();
            ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(2500);
            chronometer.setTextColor(Color.RED);
            bt_stop.setEnabled(true);
            bt_remplacement.setEnabled(true);
            bt_but1.setEnabled(true);
            bt_but2.setEnabled(true);
        }
        if ("45:07".equals(chronometer.getText())) {
            // Create Intent and start the new Activity here
            Toast.makeText(MatchingActivity.this, "ARRETS DE JEU", Toast.LENGTH_SHORT).show();
            ((Vibrator)getSystemService(VIBRATOR_SERVICE)).vibrate(2500);
            chronometer.setTextColor(Color.RED);
            bt_stop.setEnabled(true);
            bt_remplacement.setEnabled(true);
            bt_but1.setEnabled(true);
            bt_but2.setEnabled(true);

        }

    }
    public void refreshFait(){ // On raffraichi la list des faits
        listfait = lematch.getListfait();

        ListDesFaits = new String[listfait.size()];

        int i = 0;
        for (Fait f : listfait){
            ListDesFaits[i] = f.infofait();
            i++;
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ListDesFaits);

        list = (ListView) findViewById(R.id.listE);

        list.setAdapter(adapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1){ // Si on recupere 1 on rajoute un but a la premiere equipe
            if(resultCode == RESULT_OK){
                lematch.ajoutBute1();
                bt_but1.setText(Integer.toString(lematch.getBute1()));
                refreshFait();
            }
        }
        if(requestCode ==2){ // Si on reçoit 2 on rajoute un but a la deuxieme equipe
            if(resultCode == RESULT_OK){
                lematch.ajoutBute2();
                bt_but2.setText(Integer.toString(lematch.getBute2()));
                refreshFait();
            }
        }
        if(requestCode ==3 || requestCode ==4){ // On recharge la liste
            if(resultCode == RESULT_OK){
                refreshFait();
            }
        }
    }
}
