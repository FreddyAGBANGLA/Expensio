package com.example.expensio.Model;

public class Revenu {

    private int id;
    private int montant = 0;
    private String categorie = "";
    private String date = "";
    private String heure = "";
    private String desc = "";
    private String nom_compte = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNom_compte() {
        return nom_compte;
    }

    public void setNom_compte(String nom_compte) {
        this.nom_compte = nom_compte;
    }
}
