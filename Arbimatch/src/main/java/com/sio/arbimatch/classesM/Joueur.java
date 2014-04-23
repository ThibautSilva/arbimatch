package com.sio.arbimatch.classesM;

/**
 * Created with IntelliJ IDEA.
 * User: siouser
 * Date: 03/10/13
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */
public class Joueur {
    private int idJ;
    private String nom;
    private String prenom;
    private String dateN;

    public Joueur(){

    }

    public Joueur(int id,String nom, String prenom, String dateN) {
        this.idJ= id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateN = dateN;
    }

    public int getIdJ() {
        return idJ;
    }

    public void setIdJ(int idJ) {
        this.idJ = idJ;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateN() {
        return dateN;
    }

    public void setDateN(String dateN) {
        this.dateN = dateN;
    }

    public String toString(){
        return ("Joueur " + this.getNom() + " "+ this.getPrenom());
    }
}
