package com.sio.arbimatch.classesM;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: siouser
 * Date: 03/10/13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class Club {
    private int idC;
    private String nom;
    private String siege;

    private ArrayList<Joueur> listeJoueurs;
    private static ArrayList<Club> listClub = new ArrayList<Club>();

    public static ArrayList<Club> getListClub() {
        return listClub;
    }

    public static void setListClub(ArrayList<Club> listClub) {
        Club.listClub = listClub;
    }
    public static void ajouterClub(Club c){
        listClub.add(c);
    }

    public Club(int id, String nom, String siege) {
        this.idC = id;
        this.nom = nom;
        this.siege = siege;
        listeJoueurs = new ArrayList<Joueur>();
    }

    public void ajouterJoueur(Joueur j){
        this.listeJoueurs.add(j);
    }

    public Club(){
        listeJoueurs = new ArrayList<Joueur>();
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSiege() {
        return siege;
    }

    public void setSiege(String siege) {
        this.siege = siege;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public void afficherJoueurs(){
        for (int i = 0; i < this.listeJoueurs.size(); i++){
            Joueur j = this.listeJoueurs.get(i);
            System.out.println(j.toString());
        }
    }

    public String toString(){
        return "CLUB " + this.getNom() + " " + this.getSiege();
    }
}
