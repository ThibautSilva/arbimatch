package com.sio.arbimatch.classesM;

/**
 * Created with IntelliJ IDEA.
 * User: Thib'
 * Date: 27/11/13
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
public abstract class Fait implements Comparable{
    private int idF;
    private int temps;
    private static int id=1;

    public Fait(int unTemps) {
        idF=id;
        id++;
        this.temps = unTemps;
    }
    public Fait() {
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Fait.id = id;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public abstract String infofait();

    public int compareTo(Object o) // on redéfinit compareTo(Object)
    {
        Fait fait=(Fait)o;
        int resultat = 0;
        if (temps > fait.temps)
            resultat = 1;
        if (temps < fait.temps)
            resultat = -1;
        if (temps == fait.temps)
            resultat = 0;
        return resultat;
    }
}
