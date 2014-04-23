package com.sio.arbimatch.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sio.arbimatch.MatchingActivity;
import com.sio.arbimatch.classesM.But;
import com.sio.arbimatch.classesM.Carton;
import com.sio.arbimatch.classesM.Club;
import com.sio.arbimatch.classesM.Fait;
import com.sio.arbimatch.classesM.Joueur;
import com.sio.arbimatch.classesM.Match;
import com.sio.arbimatch.classesM.Remplacement;

import java.util.ArrayList;

/**
 * Created by Thibaut on 17/12/13.
 */
public class MatchDAO {
    private SQLiteDatabase db;
    private ArbiSQLiteHelper dbHelper;
    private String codeM;

    public MatchDAO(Context context){
        dbHelper = EssaiDAO.getDbHelper();
        db = EssaiDAO.getDb();
    }

    //Close the DB
    public void close (){
        db.close();
    }
    // Creation du match avec le club domicile et visiteur
    public void createMatch(Club c1, Club c2){
        ContentValues contentValues= new ContentValues();
        contentValues.put("codeM", MatchingActivity.lematch.getCodeM());
        contentValues.put("domicile", c1.getIdC());
        contentValues.put("visiteur", c2.getIdC());
        //Insert into DB
        db.insert("matchF", null, contentValues);
    }
    public void listremplacent(ArrayList<Joueur> remplacent){
        for(Joueur j : remplacent){
            ContentValues contentValues= new ContentValues();
            contentValues.put("codeM", MatchingActivity.lematch.getCodeM());
            contentValues.put("idJ", j.getIdJ());
            db.insert("remplacent", null, contentValues);
        }
    }
    public void listtitulaire(ArrayList<Joueur> titulaire){
        for(Joueur j : titulaire){
            ContentValues contentValues= new ContentValues();
            contentValues.put("codeM", MatchingActivity.lematch.getCodeM());
            contentValues.put("idJ", j.getIdJ());
            db.insert("titulaire", null, contentValues);
        }
    }
    public void but(Fait f, Joueur j){
        ContentValues contentValues= new ContentValues();
        contentValues.put("idF", f.getIdF());
        contentValues.put("chrono", f.getTemps());
        contentValues.put("codeM", MatchingActivity.lematch.getCodeM());
        db.insert("fait", null, contentValues);
        contentValues.clear();
        contentValues.put("idF", f.getIdF());
        contentValues.put("idJ", j.getIdJ());
        db.insert("but", null, contentValues);
    }
    public void remplacement(Fait f, Joueur joueurOut, Joueur joueurIn){
        ContentValues contentValues= new ContentValues();
        contentValues.put("idF", f.getIdF());
        contentValues.put("chrono", f.getTemps());
        contentValues.put("codeM", MatchingActivity.lematch.getCodeM());
        db.insert("fait", null, contentValues);
        contentValues.clear();
        contentValues.put("idF", f.getIdF());
        contentValues.put("joueurOut", joueurOut.getIdJ());
        contentValues.put("joueurIn", joueurIn.getIdJ());
        db.insert("remplacement", null, contentValues);
    }
    public void carton(Fait f, String couleur, Joueur j1){
        ContentValues contentValues= new ContentValues();
        contentValues.put("idF", f.getIdF());
        contentValues.put("chrono", f.getTemps());
        contentValues.put("codeM", MatchingActivity.lematch.getCodeM());
        db.insert("fait", null, contentValues);
        contentValues.clear();
        contentValues.put("idF", f.getIdF());
        contentValues.put("couleur", couleur);
        contentValues.put("idJ", j1.getIdJ());
        db.insert("carton", null, contentValues);
    }
    public int secondCarton(Joueur j1){
        int i =0;
        Cursor c = db.rawQuery("SELECT * FROM carton,fait WHERE carton.idF=fait.idF AND idJ='" + j1.getIdJ() + "' AND codeM='" + MatchingActivity.lematch.getCodeM() +"' AND couleur='jaune';",null);
        i = c.getCount();
        return i;
    }
    public int minIdf(){
        int i =0;
        Cursor c = db.rawQuery("SELECT * FROM fait;",null);
        i = c.getCount();
        return i;
    }
    public ArrayList<String> getMatch() {
        ArrayList<String> listMatch = new ArrayList();

        Cursor cursor = db.rawQuery("SELECT codeM FROM matchF;",null);
        cursor.moveToFirst();

        //Iterate the results
        while (!cursor.isAfterLast()) {

            String str = cursor.getString(0);

            listMatch.add(str);

            //Move to the next result
            cursor.moveToNext();
        }
        return listMatch;
    }
    public Match createOldMatch(String code){
        this.codeM = code;
        Cursor c1 = db.rawQuery("SELECT idC, nom, siege FROM matchF, club WHERE club.idC=matchF.domicile AND codeM='"+codeM+"';",null);
        Club club1 = new Club();
        c1.moveToFirst();
        club1.setIdC(c1.getInt(0));
        club1.setNom(c1.getString(1));
        club1.setSiege(c1.getString(2));
        Club.ajouterClub(club1);
        c1 = db.rawQuery("SELECT idC, nom, siege FROM matchF, club WHERE club.idC=matchF.visiteur AND codeM='"+codeM+"';",null);
        Club club2 = new Club();
        c1.moveToFirst();
        club2.setIdC(c1.getInt(0));
        club2.setNom(c1.getString(1));
        club2.setSiege(c1.getString(2));
        Club.ajouterClub(club2);

        c1 = db.rawQuery("SELECT titulaire.idJ, nom , prenom, dateN, idC FROM titulaire, joueur WHERE titulaire.idJ=joueur.idJ AND titulaire.codeM='"+codeM+"' AND joueur.idC="+club1.getIdC()+";",null);
        ArrayList<Joueur> listTitue1 = new ArrayList<Joueur>();
        c1.moveToFirst();
        while (!c1.isAfterLast()) {
            Joueur j = new Joueur();
            //Take values from the DB
            j.setIdJ(c1.getInt(0));
            j.setNom(c1.getString(1));
            j.setPrenom(c1.getString(2));
            j.setDateN(c1.getString(3));
            club1.ajouterJoueur(j);
            listTitue1.add(j);

            //Move to the next result
            c1.moveToNext();
        }
        c1 = db.rawQuery("SELECT titulaire.idJ, nom , prenom, dateN, idC FROM titulaire, joueur WHERE titulaire.idJ=joueur.idJ AND titulaire.codeM='"+codeM+"' AND joueur.idC="+club2.getIdC()+";",null);
        ArrayList<Joueur> listTitue2 = new ArrayList<Joueur>();
        c1.moveToFirst();
        while (!c1.isAfterLast()) {
            Joueur j = new Joueur();
            //Take values from the DB
            j.setIdJ(c1.getInt(0));
            j.setNom(c1.getString(1));
            j.setPrenom(c1.getString(2));
            j.setDateN(c1.getString(3));
            club2.ajouterJoueur(j);
            listTitue2.add(j);

            //Move to the next result
            c1.moveToNext();
        }
        c1 = db.rawQuery("SELECT remplacent.idJ, nom , prenom, dateN, idC FROM remplacent, joueur WHERE remplacent.idJ=joueur.idJ AND remplacent.codeM='"+codeM+"' AND joueur.idC="+club1.getIdC()+";",null);
        ArrayList<Joueur> listRemplace_e1 = new ArrayList<Joueur>();
        c1.moveToFirst();
        while (!c1.isAfterLast()) {
            Joueur j = new Joueur();
            //Take values from the DB
            j.setIdJ(c1.getInt(0));
            j.setNom(c1.getString(1));
            j.setPrenom(c1.getString(2));
            j.setDateN(c1.getString(3));
            club1.ajouterJoueur(j);
            listRemplace_e1.add(j);

            //Move to the next result
            c1.moveToNext();
        }
        c1 = db.rawQuery("SELECT remplacent.idJ, nom , prenom, dateN, idC FROM remplacent, joueur WHERE remplacent.idJ=joueur.idJ AND remplacent.codeM='"+codeM+"' AND joueur.idC="+club2.getIdC()+";",null);
        ArrayList<Joueur> listRemplace_e2 = new ArrayList<Joueur>();
        c1.moveToFirst();
        while (!c1.isAfterLast()) {
            Joueur j = new Joueur();
            //Take values from the DB
            j.setIdJ(c1.getInt(0));
            j.setNom(c1.getString(1));
            j.setPrenom(c1.getString(2));
            j.setDateN(c1.getString(3));
            club2.ajouterJoueur(j);
            listRemplace_e2.add(j);

            //Move to the next result
            c1.moveToNext();
        }
        final Match m1 = new Match(codeM, club1,club2);
        m1.setE1listTitu(listTitue1);
        m1.setE2listTitu(listTitue2);
        m1.setE1listRemp(listRemplace_e1);
        m1.setE2listRemp(listRemplace_e2);

        c1 = db.rawQuery("SELECT chrono,idJ FROM fait,but WHERE fait.idF=but.idF AND codeM='"+codeM+"';",null);
        c1.moveToFirst();
        while (!c1.isAfterLast()){
            int chrono = c1.getInt(0);
            int buteur = c1.getInt(1);
            Joueur j = null;
            boolean trouve = false;
            int i = 0;

                while((trouve == false)&&(i < listTitue1.size())){
                    j = listTitue1.get(i);
                    if (j.getIdJ() == buteur){
                        trouve = true;
                    }
                    i++;
                }
                i=0;
                while((trouve == false)&&(i < listTitue2.size())){
                    j = listTitue2.get(i);
                    if (j.getIdJ() == buteur){
                        trouve = true;
                    }
                    i++;
                }
                i=0;
                while((trouve == false)&&(i < listRemplace_e1.size())){
                    j = listRemplace_e1.get(i);
                    if (j.getIdJ() == buteur){
                        trouve = true;
                    }
                    i++;
                }
                i=0;
                while((trouve == false)&&(i < listRemplace_e2.size())){
                    j = listRemplace_e2.get(i);
                    if (j.getIdJ() == buteur){
                        trouve = true;
                    }
                    i++;
                }
            c1.moveToNext();
            Fait f1 = new But(chrono, j);
            m1.ajouterFait(f1);
            ArrayList<Joueur> Joueure1 = club1.getListeJoueurs();
            for (Joueur joueur : Joueure1){
                if(joueur.getIdJ() == j.getIdJ()){
                    m1.ajoutBute1();
                }
            }
            ArrayList<Joueur> Joueure2 = club2.getListeJoueurs();
            for (Joueur joueur : Joueure2){
                if(joueur.getIdJ() == j.getIdJ()){
                    m1.ajoutBute2();
                }
            }
        }
        c1 = db.rawQuery("SELECT chrono,couleur,idJ FROM fait,carton WHERE fait.idF=carton.idF AND codeM='"+codeM+"';",null);
        c1.moveToFirst();
        while (!c1.isAfterLast()){
            int chrono = c1.getInt(0);
            String couleur = c1.getString(1);
            int joueur = c1.getInt(2);

            Joueur j = null;
            boolean trouve = false;
            int i = 0;

            while((trouve == false)&&(i < listTitue1.size())){
                j = listTitue1.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listTitue2.size())){
                j = listTitue2.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listRemplace_e1.size())){
                j = listRemplace_e1.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listRemplace_e2.size())){
                j = listRemplace_e2.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            c1.moveToNext();
            Fait f2 = new Carton(chrono, couleur, j);
            m1.ajouterFait(f2);
        }
        c1 = db.rawQuery("SELECT chrono, joueurOut, joueurIN FROM fait,remplacement WHERE fait.idF=remplacement.idF AND codeM='"+codeM+"';",null);
        c1.moveToFirst();
        while (!c1.isAfterLast()){
            int chrono = c1.getInt(0);
            int joueur = c1.getInt(1);

            Joueur j = null;
            boolean trouve = false;
            int i = 0;

            while((trouve == false)&&(i < listTitue1.size())){
                j = listTitue1.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listTitue2.size())){
                j = listTitue2.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listRemplace_e1.size())){
                j = listRemplace_e1.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listRemplace_e2.size())){
                j = listRemplace_e2.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            Joueur joueurOut = j;

            joueur = c1.getInt(2);
            j = null;
            trouve = false;
            i = 0;

            while((trouve == false)&&(i < listTitue1.size())){
                j = listTitue1.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listTitue2.size())){
                j = listTitue2.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listRemplace_e1.size())){
                j = listRemplace_e1.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            i=0;
            while((trouve == false)&&(i < listRemplace_e2.size())){
                j = listRemplace_e2.get(i);
                if (j.getIdJ() == joueur){
                    trouve = true;
                }
                i++;
            }
            c1.moveToNext();
            Joueur joueurIn = j;
            Fait f3 = new Remplacement(chrono, joueurOut, joueurIn);
            m1.ajouterFait(f3);
        }
        return m1;
    }
}
