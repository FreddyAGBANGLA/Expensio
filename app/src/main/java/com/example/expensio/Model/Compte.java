package com.example.expensio.Model;

public class Compte {

    private String nom_compte;
    private int solde_compte;
    private int revenu_compte;
    private int depense_compte;

    public String getNom_compte() {
        return nom_compte;
    }

    public int getSolde_compte() {
        return solde_compte;
    }

    public int getRevenu_compte() {
        return revenu_compte;
    }

    public int getDepense_compte() {
        return depense_compte;
    }

    public void setNom_compte(String nom_compte) {
        this.nom_compte = nom_compte;
    }

    public void setSolde_compte(int solde_sompte) {
        this.solde_compte = solde_sompte;
    }

    public void setRevenu_compte(int revenu_compte) {
        this.revenu_compte = revenu_compte;
    }

    public void setDepense_compte(int depense_compte) {
        this.depense_compte = depense_compte;
    }

}
