package com.sio.arbimatch.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sio.arbimatch.MatchingActivity;
import com.sio.arbimatch.classesM.Club;
import com.sio.arbimatch.classesM.Joueur;

/**
 * Created by Thibaut on 17/12/13.
 */
public class EssaiDAO {
    private static SQLiteDatabase db;
    private static ArbiSQLiteHelper dbHelper;

    public EssaiDAO(Context context){
        dbHelper = new ArbiSQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Close the DB
    public void close (){
        db.close();
    }
    public int vide(){//test si il n'y a pas de joueur ni de club
        int i=0;
        Cursor c = db.rawQuery("SELECT * FROM club, joueur;",null);
        i = c.getCount();
        return  i;
    }

    public void jeuEssai(){ //Ajoute des clubs et joueurs pour former un jeu d'essai
        int i=0;
        Cursor c = db.rawQuery("SELECT * FROM club, joueur;",null);
        i = c.getCount();
        if (i==0){ // Si aucun club et aucun joueur existe on crée un jeu d'essai
            ContentValues contentValues= new ContentValues();
            contentValues.put("idC", "1");
            contentValues.put("nom", "PSG");
            contentValues.put("siege", "Paris");
            db.insert("club", null, contentValues);
            contentValues.clear();
            contentValues.put("idC", "2");
            contentValues.put("nom", "FCB");
            contentValues.put("siege", "Barcelone");
            db.insert("club", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "1");
            contentValues.put("nom", "Sirigu");
            contentValues.put("prenom", "Salvatore");
            contentValues.put("dateN", "10/01/1987");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "2");
            contentValues.put("nom", "Lavezzi");
            contentValues.put("prenom", "Ezequiel");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "3");
            contentValues.put("nom", "Da Silva");
            contentValues.put("prenom", "Thiago");
            contentValues.put("dateN", "12/10/1984");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "4");
            contentValues.put("nom", "Van Der Wiel");
            contentValues.put("prenom", "Gregory");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "5");
            contentValues.put("nom", "Cavani");
            contentValues.put("prenom", "Edison");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "6");
            contentValues.put("nom", "Matuidi");
            contentValues.put("prenom", "Blaise");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "7");
            contentValues.put("nom", "Ibrahimovic");
            contentValues.put("prenom", "Zlatan");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "8");
            contentValues.put("nom", "Verrati");
            contentValues.put("prenom", "Marco");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "9");
            contentValues.put("nom", "Mouras");
            contentValues.put("prenom", "Lucas");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "10");
            contentValues.put("nom", "Pastore");
            contentValues.put("prenom", "Javier");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "11");
            contentValues.put("nom", "Rabiot");
            contentValues.put("prenom", "Adrien");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "1");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "12");
            contentValues.put("nom", "Messi");
            contentValues.put("prenom", "Lionel");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "13");
            contentValues.put("nom", "Neymar");
            contentValues.put("prenom", " ");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "14");
            contentValues.put("nom", "Iniesta");
            contentValues.put("prenom", "Andres");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "15");
            contentValues.put("nom", "Puyol");
            contentValues.put("prenom", "Carles");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "16");
            contentValues.put("nom", "Xavi");
            contentValues.put("prenom", "Hernandez");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "17");
            contentValues.put("nom", "Sanchez");
            contentValues.put("prenom", "Alexis");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "18");
            contentValues.put("nom", "Fabregas");
            contentValues.put("prenom", "Cesc");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "19");
            contentValues.put("nom", "Piqué");
            contentValues.put("prenom", "Gerard");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "20");
            contentValues.put("nom", "Valdès");
            contentValues.put("prenom", "Victor");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "21");
            contentValues.put("nom", "Alba");
            contentValues.put("prenom", "Jordi");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
            contentValues.clear();
            contentValues.put("idJ", "22");
            contentValues.put("nom", "Alves");
            contentValues.put("prenom", "Daniel");
            contentValues.put("dateN", "12/10/1988");
            contentValues.put("idC", "2");
            db.insert("joueur", null, contentValues);
        }
        recupClub();
    }

    //Ajouter un club
    public void createClub(Club c){
        ContentValues contentValues= new ContentValues();
        contentValues.put("idC", c.getIdC());
        contentValues.put("nom", c.getNom());
        contentValues.put("siege", c.getSiege());
        //Insert into DB
        db.insert("club", null, contentValues);
    }

    // Ajouter un Joueur
    public void createJoueur(Joueur j, Club c){
        ContentValues contentValues= new ContentValues();
        contentValues.put("idJ", j.getIdJ());
        contentValues.put("nom", j.getNom());
        contentValues.put("prenom", j.getPrenom());
        contentValues.put("dateN", j.getDateN());
        contentValues.put("idC", c.getIdC());
        //Insert into DB
        db.insert("joueur", null, contentValues);
    }
    //Recuperer en objet TOUT les clubs et joueurs de la base
    public void recupClub(){
        Cursor c1 = db.rawQuery("SELECT idC, nom, siege FROM club;",null);
        c1.moveToFirst();
        while (!c1.isAfterLast()) {
            Club club1 = new Club();
            //Take values from the DB
            club1.setIdC(c1.getInt(0));
            club1.setNom(c1.getString(1));
            club1.setSiege(c1.getString(2));
            Club.ajouterClub(club1);
            recupJoueur(club1);
            c1.moveToNext();
        }
    }
    public void recupJoueur(Club club){
        Cursor c1 = db.rawQuery("SELECT idJ, nom , prenom, dateN, idC FROM joueur WHERE joueur.idC='"+ club.getIdC()+"';",null);
        c1.moveToFirst();
        while (!c1.isAfterLast()) {
            Joueur j = new Joueur();
            //Take values from the DB
            j.setIdJ(c1.getInt(0));
            j.setNom(c1.getString(1));
            j.setPrenom(c1.getString(2));
            j.setDateN(c1.getString(3));
            club.ajouterJoueur(j);
        }
    }
    public static ArbiSQLiteHelper getDbHelper() {
        return dbHelper;
    }

    public static SQLiteDatabase getDb() {
        return db;
    }
}
