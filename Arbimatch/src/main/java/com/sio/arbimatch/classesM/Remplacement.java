package com.sio.arbimatch.classesM;

/**
 * Created with IntelliJ IDEA.
 * User: Thib'
 * Date: 27/11/13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class Remplacement extends Fait{
    private Joueur joueurIn;
    private Joueur joueurOut;

    public Remplacement(int n ,Joueur unjoueurOut, Joueur unjoueurIn) {
        super(n);
        joueurOut = unjoueurOut;
        joueurIn = unjoueurIn;
    }
    public Remplacement(Joueur unjoueurOut){
        joueurOut = unjoueurOut;

    }

    public Joueur getJoueurIn() {
        return joueurIn;
    }
    public void setJoueurIn(Joueur joueurIn) {
        this.joueurIn = joueurIn;
    }
    public Joueur getJoueurOut() {
        return joueurOut;
    }
    public void setJoueurOut(Joueur joueurOut) {
        this.joueurOut = joueurOut;
    }
    public String infofait(){
        String phrase;
        phrase = Integer.toString(super.getTemps()+1) + "\" Sortie du "+ joueurOut + " / Entrée du " + joueurIn;
        return phrase;
    }
}
