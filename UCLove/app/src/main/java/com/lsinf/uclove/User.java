package com.lsinf.uclove;

/**
 * Created by damien on 26/04/16.
 */

//Définit un utilisateur.

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

    private String hobby[] = null;
    private String naissance= null;
    private String disponibilite[] = null;

    private String ville= null;
    private String mail= null;
    private String yeux= null;
    private String cheveux= null;
    private String tel= null;
    private Filtre filtres[] = null;

    //Oui, c'est pas tres lisible, mais c'est juste un set/get pour chaque variable, et oui, ca a été généré automatiquement :p
    public String getLangue() {return langue;}
    public void setLangue(String langue) {this.langue = langue;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public String getSexe() {return sexe;}
    public void setSexe(String sexe) {this.sexe = sexe;}
    public String[] getPhoto() {if(photo == null)return new String[]{"placeholder.png"};return photo;}
    public void setPhoto(String[] photo) {this.photo = photo;}
    public String getAttirance() {return attirance;}
    public void setAttirance(String attirance) {this.attirance = attirance;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String[] getHobby() {return hobby;}
    public void setHobby(String[] hobby) {this.hobby = hobby;}
    public String getNaissance() {return naissance;}
    public void setNaissance(String naissance) {this.naissance = naissance;}
    public String[] getDisponibilite() {return disponibilite;}
    public void setDisponibilite(String[] disponibilite) {this.disponibilite = disponibilite;}
    public String getVille() {return ville;}
    public void setVille(String ville) {this.ville = ville;}
    public String getMail() {return mail;}
    public void setMail(String mail) {this.mail = mail;}
    public String getYeux() {return yeux;}
    public void setYeux(String yeux) {this.yeux = yeux;}
    public String getCheveux() {return cheveux;}
    public void setCheveux(String cheveux) {this.cheveux = cheveux;}
    public String getTel() {return tel;}
    public void setTel(String tel) {this.tel = tel;}
    public Filtre[] getFiltres() {return filtres;}
    public void setFiltres(Filtre[] filtres) {this.filtres = filtres;}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
