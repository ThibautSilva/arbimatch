package com.sio.arbimatch.classesM;

/**
 * Created with IntelliJ IDEA.
 * User: Thib'
 * Date: 27/11/13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class But extends Fait {
    private Joueur buteur;

    public But(int n,Joueur unButeur) {
        super(n);
        buteur = unButeur;
    }
    public Joueur getButeur() {
        return buteur;
    }
    public void setButeur(Joueur buteur) {
        this.buteur = buteur;
    }
    public String infofait(){
        String phrase;
        phrase = Integer.toString(super.getTemps()+1) + "\" But de "+buteur.getNom() + " " + buteur.getPrenom();
        return phrase;
    }
}