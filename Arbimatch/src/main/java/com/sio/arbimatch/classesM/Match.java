package com.sio.arbimatch.classesM;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Thib'
 * Date: 27/11/13
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public class Match {
    private String codeM;
    private Club equipe1;
    private Club equipe2;
    private ArrayList<Joueur> e1listTitu = new ArrayList<Joueur>();
    private ArrayList<Joueur> e1listRemp = new ArrayList<Joueur>();
    private ArrayList<Joueur> e2listTitu = new ArrayList<Joueur>();
    private ArrayList<Joueur> e2listRemp = new ArrayList<Joueur>();
    private ArrayList<Joueur> e1joueursTerrain = new ArrayList<Joueur>();
    private ArrayList<Joueur> e2joueursTerrain = new ArrayList<Joueur>();
    private ArrayList<Fait> listfait = new ArrayList<Fait>();
    private int bute1 = 0;
    private int bute2 = 0;

    public Match(String uncodeM, Club unEquipe1, Club unEquipe2) {
        codeM = uncodeM;
        equipe1 = unEquipe1;
        equipe2 = unEquipe2;
    }
    public Match() {
    }

    public String getCodeM() {
        return codeM;
    }

    public void setCodeM(String codeM) {
        this.codeM = codeM;
    }

    public Club getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Club equipe1) {
        this.equipe1 = equipe1;
    }

    public Club getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Club equipe2) {
        this.equipe2 = equipe2;
    }

    public int getBute1() {
        return bute1;
    }

    public int getBute2() {
        return bute2;
    }

    public ArrayList<Joueur> getE1listTitu() {
        return e1listTitu;
    }

    public void setE1listTitu(ArrayList<Joueur> e1listTitu) {
        this.e1listTitu = e1listTitu;
    }

    public ArrayList<Joueur> getE1listRemp() {
        return e1listRemp;
    }

    public void setE1listRemp(ArrayList<Joueur> e1listRemp) {
        this.e1listRemp = e1listRemp;
    }

    public ArrayList<Joueur> getE2listTitu() {
        return e2listTitu;
    }

    public void setE2listTitu(ArrayList<Joueur> e2listTitu) {
        this.e2listTitu = e2listTitu;
    }

    public ArrayList<Joueur> getE2listRemp() {
        return e2listRemp;
    }

    public void setE2listRemp(ArrayList<Joueur> e2listRemp) {
        this.e2listRemp = e2listRemp;
    }

    public ArrayList<Joueur> getE1joueursTerrain() {
        return e1joueursTerrain;
    }

    public void setE1joueursTerrain(ArrayList<Joueur> e1joueursTerrain) {
        this.e1joueursTerrain = e1joueursTerrain;
    }

    public ArrayList<Joueur> getE2joueursTerrain() {
        return e2joueursTerrain;
    }

    public void setE2joueursTerrain(ArrayList<Joueur> e2joueursTerrain) {
        this.e2joueursTerrain = e2joueursTerrain;
    }

    public ArrayList<Fait> getListfait() {
        return listfait;
    }

    public void setListfait(ArrayList<Fait> listfait) {
        this.listfait = listfait;
    }

    public void ajouterFait(Fait f1){
        listfait.add(f1);
    }
    public void exclusion(int position){
        if (position > e1listTitu.size()-1){
            e2joueursTerrain.remove(position - e1listTitu.size());
        }else{
            e1joueursTerrain.remove(position);
        }
    }
    public void remplacement(Joueur in, Joueur out, int equipe){
        if(equipe ==1) {
            e1joueursTerrain.remove(out);
            e1joueursTerrain.add(in);
            e1listRemp.remove(in);
        }else{
            e2joueursTerrain.remove(out);
            e2joueursTerrain.add(in);
            e2listRemp.remove(in);
        }
    }
    public void ajoutBute1(){
        bute1++;
    }
    public void ajoutBute2(){
        bute2++;
    }
}

