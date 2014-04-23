package com.sio.arbimatch.classesM;

/**
 * Created with IntelliJ IDEA.
 * User: Thib'
 * Date: 27/11/13
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class Carton extends Fait {
    private String couleur;
    private Joueur joueur;

    public Carton(int untemps, String uncouleur, Joueur unjoueur) {
        super(untemps);
        couleur = uncouleur;
        joueur = unjoueur;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public String infofait(){
        String phrase;
        phrase = Integer.toString(super.getTemps()+1) + "\" Carton "+ couleur + " pour " + joueur.getNom();
        return phrase;
    }
}
