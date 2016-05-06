package com.lsinf.uclove;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by damien on 26/04/16.
 * Définit un utilisateur.
 */


public class User
{

    private int ID= 0;
    private String langue= null;
    private String nom= null;
    private String prenom= null;
    private String sexe= null;
    private String photo[]= null;
    private String attirance= null;
    private String description = null;

    private String hobby = null;
    private String naissance= null;
    private String disponibilite[] = null;

    private String ville= null;
    private String mail= null;
    private String yeux= null;
    private String cheveux= null;
    private String tel= null;
    public ArrayList<Filtre> filtres = null;

    //Oui, c'est pas tres lisible, mais c'est juste un set/get pour chaque variable, et oui, ca a été généré automatiquement :p
    public String getLangue() {return langue;}
    public void setLangue(String langue) {this.langue = langue;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}

    public int getIdSexe() {try{if(Integer.parseInt(sexe)>1)return 0; return Integer.parseInt(sexe);}catch (Exception e){return 0;}}
    public String getSexe(Context ctx){return ctx.getResources().getStringArray(R.array.genre)[getIdSexe()];}
    public void setSexe(String sexe) {this.sexe = sexe;}

    public String[] getPhoto() {if(photo == null)return new String[]{"placeholder"};return photo;}
    public void setPhoto(String[] photo) {
        Log.e("dodormeur","set photo"+photo.length+" "+photo[0]);this.photo = photo;}

    public int getIdAttirance() {try{return Integer.parseInt(attirance);}catch (Exception e){return 0;}}
    public String getAttirance(Context ctx){return ctx.getResources().getStringArray(R.array.genre)[getIdAttirance()];}
    public void setAttirance(String attirance) {this.attirance = attirance;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getHobby() {return hobby;}
    public void setHobby(String hobby) {this.hobby = hobby;}
    public String getNaissance() {return naissance;}
    public void setNaissance(String naissance) {this.naissance = naissance;}

    public String[] getDisponibilite() {return disponibilite;}
    public void setDisponibilite(String[] disponibilite) {this.disponibilite = disponibilite;}

    public String getVille() {return ville;}
    public void setVille(String ville) {this.ville = ville;}
    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}

    public int getIDYeux() {try{return Integer.parseInt(yeux);}catch (Exception e){return 0;}}
    public String getYeux(Context ctx){return ctx.getResources().getStringArray(R.array.eye_color)[getIDYeux()];}
    public void setYeux(String yeux) {this.yeux = yeux;}

    public int getIdCheveux() {try{return Integer.parseInt(cheveux);}catch (Exception e){return 0;}}
    public String getCheveux(Context ctx){return ctx.getResources().getStringArray(R.array.hair_color)[getIdCheveux()];}
    public void setCheveux(String cheveux) {this.cheveux = cheveux;}

    public String getTel() {return "0"+tel;}
    public void setTel(String tel) {this.tel = tel;}
    public ArrayList<Filtre> getFiltres() {return filtres;}
    public void setFiltres(ArrayList<Filtre> filtres) {this.filtres = filtres;}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
