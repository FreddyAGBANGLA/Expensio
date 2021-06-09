package com.example.expensio.Model;

public class Transfert {

    private int id;
    private int montant = 0;
    private String date = "";
    private String heure = "";
    private String desc = "";
    private String nom_compte_source = "";
    private String nom_compte_destination = "";


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

    public String getNom_compte_source() {
        return nom_compte_source;
    }

    public void setNom_compte_source(String nom_compte_source) {
        this.nom_compte_source = nom_compte_source;
    }

    public String getNom_compte_destination() {
        return nom_compte_destination;
    }

    public void setNom_compte_destination(String nom_compte_destination) {
        this.nom_compte_destination = nom_compte_destination;
    }
}
