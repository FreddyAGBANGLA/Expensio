package com.example.expensio.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.expensio.Model.Compte;
import com.example.expensio.Model.Depense;
import com.example.expensio.Model.Revenu;
import com.example.expensio.Model.Transfert;

import java.util.ArrayList;
import java.util.List;

public class GestDataBase extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "EXPENSIO_DATABASE.db";
    private static final int DATABASE_VERSION = 3;

    private static final String TABLE_COMPTE = "COMPTE";
    private static final String COL_1 = "NOM_COMPTE";
    private static final String COL_2 = "SOLDE_COMPTE";
    private static final String COL_3 = "REVENU_COMPTE";
    private static final String COL_4 = "DEPENSE_COMPTE";

    private static final String TABLE_REVENU = "REVENU";
    private static final String ID_REVENU = "ID_REVENU";
    private static final String MONTANT_REVENU = "MONTANT_REVENU";
    private static final String CATEGORIE_REVENU = "CATEGORIE_REVENU";
    private static final String DATE_REVENU = "DATE_REVENU";
    private static final String HEURE_REVENU = "HEURE_REVENU";
    private static final String DESC_REVENU = "DESC_REVENU";
    private static final String NOM_COMPTE_REVENU = "NOM_COMPTE_REVENU";

    private static final String TABLE_DEPENSE = "DEPENSE";
    private static final String ID_DEPENSE = "ID_DEPENSE";
    private static final String MONTANT_DEPENSE = "MONTANT_DEPENSE";
    private static final String CATEGORIE_DEPENSE = "CATEGORIE_DEPENSE";
    private static final String DATE_DEPENSE = "DATE_DEPENSE";
    private static final String HEURE_DEPENSE = "HEURE_DEPENSE";
    private static final String DESC_DEPENSE = "DESC_DEPENSE";
    private static final String NOM_COMPTE_DEPENSE = "NOM_COMPTE_DEPENSE";

    private static final String TABLE_TRANSFERT = "TRANSFERT";
    private static final String ID_TRANSFERT = "ID_TRANSFERT";
    private static final String MONTANT_TRANSFERT = "MONTANT_TRANSFERT";
    private static final String DATE_TRANSFERT = "DATE_TRANSFERT";
    private static final String HEURE_TRANSFERT = "HEURE_TRANSFERT";
    private static final String DESC_TRANSFERT = "DESC_TRANSFERT";
    private static final String NOM_COMPTE_SOURCE_TRANSFERT = "NOM_COMPTE_SOURCE_TRANSFERT";
    private static final String NOM_COMPTE_DESTINATION_TRANSFERT = "NOM_COMPTE_DESTINATION_TRANSFERT";


    public GestDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_COMPTE
                +"(NOM_COMPTE TEXT PRIMARY KEY, SOLDE_COMPTE INTEGER, " +
                "REVENU_COMPTE INTEGER, DEPENSE_COMPTE INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_REVENU
                +"("+ID_REVENU+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +MONTANT_REVENU+" INTEGER, "
                +CATEGORIE_REVENU+" TEXT, "
                +DATE_REVENU+" TEXT, "
                +HEURE_REVENU+" TEXT, "
                +DESC_REVENU+" TEXT, "
                +NOM_COMPTE_REVENU+" TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_DEPENSE
                +"("+ID_DEPENSE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +MONTANT_DEPENSE+" INTEGER, "
                +CATEGORIE_DEPENSE+" TEXT, "
                +DATE_DEPENSE+" TEXT, "
                +HEURE_DEPENSE+" TEXT, "
                +DESC_DEPENSE+" TEXT, "
                +NOM_COMPTE_DEPENSE+" TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_TRANSFERT
                +"("+ID_TRANSFERT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +MONTANT_TRANSFERT+" INTEGER,  "
                +DATE_TRANSFERT+" TEXT, "
                +HEURE_TRANSFERT+" TEXT, "
                +DESC_TRANSFERT+" TEXT, "
                +NOM_COMPTE_SOURCE_TRANSFERT+" TEXT, "
                +NOM_COMPTE_DESTINATION_TRANSFERT+" TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COMPTE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_REVENU);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DEPENSE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRANSFERT);
        onCreate(db);
    }

    public Boolean insert_compte(Compte compte){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, compte.getNom_compte());
        values.put(COL_2, compte.getSolde_compte());
        values.put(COL_3, compte.getRevenu_compte());
        values.put(COL_4, compte.getDepense_compte());
        long result = db.insert(TABLE_COMPTE, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean insert_revenu(Revenu revenu){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONTANT_REVENU, revenu.getMontant());
        values.put(CATEGORIE_REVENU, revenu.getCategorie());
        values.put(DATE_REVENU, revenu.getDate());
        values.put(HEURE_REVENU, revenu.getHeure());
        values.put(DESC_REVENU, revenu.getDesc());
        values.put(NOM_COMPTE_REVENU, revenu.getNom_compte());
        long result = db.insert(TABLE_REVENU, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean insert_depense(Depense depense){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONTANT_DEPENSE, depense.getMontant());
        values.put(CATEGORIE_DEPENSE, depense.getCategorie());
        values.put(DATE_DEPENSE, depense.getDate());
        values.put(HEURE_DEPENSE, depense.getHeure());
        values.put(DESC_DEPENSE, depense.getDesc());
        values.put(NOM_COMPTE_DEPENSE, depense.getNom_compte());
        long result = db.insert(TABLE_DEPENSE, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean insert_transfert(Transfert transfert){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONTANT_TRANSFERT, transfert.getMontant());
        values.put(DATE_TRANSFERT, transfert.getDate());
        values.put(HEURE_TRANSFERT, transfert.getHeure());
        values.put(DESC_TRANSFERT, transfert.getDesc());
        values.put(NOM_COMPTE_SOURCE_TRANSFERT, transfert.getNom_compte_source());
        values.put(NOM_COMPTE_DESTINATION_TRANSFERT, transfert.getNom_compte_destination());
        long result = db.insert(TABLE_DEPENSE, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean update_nom_compte(String nom){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, nom);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0){
            long result = db.update(TABLE_COMPTE, values, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
            if (result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public Boolean update_solde_compte(String nom, int solde, int revenu, int depense){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, solde);
        values.put(COL_3, revenu);
        values.put(COL_4, depense);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0) {
            long result = db.update(TABLE_COMPTE, values, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
            if (result == -1)
                return false;
            else
                return true;
        }
        else{
            return false;
        }
    }

    public Boolean delete_nom_compte(String nom){
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0){
            long result = db.delete(TABLE_COMPTE, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
            if (result == -1)
                return false;
            else
                return true;
        }
        else
            return false;
    }

    public Compte get_solde_compte(String nom){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor != null){
            if (cursor.moveToFirst()){
                Compte compte = new Compte();
                compte.setNom_compte(cursor.getString(cursor.getColumnIndex(COL_1)));
                compte.setSolde_compte(cursor.getInt(cursor.getColumnIndex(COL_2)));
                compte.setRevenu_compte(cursor.getInt(cursor.getColumnIndex(COL_3)));
                compte.setDepense_compte(cursor.getInt(cursor.getColumnIndex(COL_4)));
                return compte;
            }
            else
                return null;
        }
        else
            return null;
    }

    public List<Compte> getAllComptes(){

        db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Compte> compteList = new ArrayList<>();

        db.beginTransaction();
        try{
            cursor = db.query(TABLE_COMPTE, null, null, null, null, null, null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Compte compteModel = new Compte();
                        compteModel.setNom_compte(cursor.getString(cursor.getColumnIndex(COL_1)));
                        compteModel.setSolde_compte(cursor.getInt(cursor.getColumnIndex(COL_2)));
                        compteModel.setRevenu_compte(cursor.getInt(cursor.getColumnIndex(COL_3)));
                        compteModel.setDepense_compte(cursor.getInt(cursor.getColumnIndex(COL_4)));
                        compteList.add(compteModel);

                    }while (cursor.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cursor.close();
        }
        return compteList;
    }

    public List<Revenu> getAllRevenus(){
        db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Revenu> revenuList = new ArrayList<>();

        db.beginTransaction();
        try{
            cursor = db.query(TABLE_REVENU, null, null, null, null, null, null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Revenu revenuModel = new Revenu();
                        revenuModel.setId(cursor.getInt(cursor.getColumnIndex(ID_REVENU)));
                        revenuModel.setMontant(cursor.getInt(cursor.getColumnIndex(MONTANT_REVENU)));
                        revenuModel.setCategorie(cursor.getString(cursor.getColumnIndex(CATEGORIE_REVENU)));
                        revenuModel.setDate(cursor.getString(cursor.getColumnIndex(DATE_REVENU)));
                        revenuModel.setHeure(cursor.getString(cursor.getColumnIndex(HEURE_REVENU)));
                        revenuModel.setDesc(cursor.getString(cursor.getColumnIndex(DESC_REVENU)));
                        revenuModel.setNom_compte(cursor.getString(cursor.getColumnIndex(NOM_COMPTE_REVENU)));
                        revenuList.add(revenuModel);

                    }while (cursor.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cursor.close();
        }
        return revenuList;
    }
}
