package com.sio.arbimatch.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thibaut on 15/12/13.
 */
public class ArbiSQLiteHelper extends SQLiteOpenHelper {

    static final int DB_VERSION =1;
    static final String DB_NAME = "arbimatch_data";

    public ArbiSQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //execute create table SQL
        db.execSQL("CREATE TABLE club (idC INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT NOT NULL, siege TEXT NOT NULL);");
        db.execSQL("CREATE TABLE joueur (idJ INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,nom TEXT ,prenom TEXT ,dateN TEXT ,idC INTEGER REFERENCES club(idC));");
        db.execSQL("CREATE TABLE matchF (codeM TEXT PRIMARY KEY NOT NULL , domicile INTEGER REFERENCES club(idC) , visiteur INTEGER REFERENCES club(idC));");
        db.execSQL("CREATE TABLE fait (idF INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , chrono INTEGER, codeM INTEGER REFERENCES matchF(codeM));");
        db.execSQL("CREATE TABLE but (idF INTEGER PRIMARY KEY REFERENCES fait(idF), idJ INTEGER REFERENCES joueur(idJ));");
        db.execSQL("CREATE TABLE remplacement (idF INTEGER PRIMARY KEY REFERENCES fait(idF), joueurOut INTEGER REFERENCES joueur(idJ), joueurIN INTEGER REFERENCES joueur(idJ));");
        db.execSQL("CREATE TABLE carton (idF INTEGER PRIMARY KEY REFERENCES fait(idF), couleur TEXT NOT NULL, idJ INTEGER REFERENCES joueur(idJ));");
        db.execSQL("CREATE TABLE titulaire (codeM INTEGER REFERENCES matchF(codeM) NOT NULL, idJ INTEGER REFERENCES joueur(idJ), PRIMARY KEY (codeM, idJ));");
        db.execSQL("CREATE TABLE remplacent (codeM INTEGER REFERENCES matchF(codeM) NOT NULL, idJ INTEGER REFERENCES joueur(idJ), PRIMARY KEY (codeM, idJ));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer){
        //DROP table
        //db.execSQL("DROP TABLE IF EXISTS joueurs");
        // Recreate table
        onCreate(db);
    }

}

