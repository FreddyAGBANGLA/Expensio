package com.example.expensio;

public class Compte {

    private double solde;

    public Compte(double solde){
        this.solde = solde;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
