package com.example.expensio.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.expensio.Model.Compte;

import java.util.ArrayList;
import java.util.List;

public class DBCompteAdapter extends GestDataBaseAdapter {

    protected static final String TABLE_COMPTE = "COMPTE";
    private static final String COL_1 = "NOM_COMPTE";
    private static final String COL_2 = "SOLDE_COMPTE";
    private static final String COL_3 = "REVENU_COMPTE";
    private static final String COL_4 = "DEPENSE_COMPTE";


    public DBCompteAdapter(Context context) {
        super(context);
    }

    public Boolean insert_compte(Compte compte){
        db = this.DBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, compte.getNom_compte());
        values.put(COL_2, compte.getSolde_compte());
        values.put(COL_3, compte.getRevenu_compte());
        values.put(COL_4, compte.getDepense_compte());
        long result = db.insert(TABLE_COMPTE, null, values);
        return result != -1;
    }

    // @SuppressLint("Recycle")
    public Boolean update_nom_compte(String nom){
        db = this.DBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, nom);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0){
            long result = db.update(TABLE_COMPTE, values, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
            return result != -1;
        }
        else
            return false;
    }

    // @SuppressLint("Recycle")
    public void update_solde_compte(String nom, int solde, int revenu, int depense){
        db = this.DBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, solde);
        values.put(COL_3, revenu);
        values.put(COL_4, depense);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0) {
            db.update(TABLE_COMPTE, values, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
        }
    }

    // @SuppressLint("Recycle")
    public Boolean delete_nom_compte(String nom){
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_COMPTE +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0){
            long result = db.delete(TABLE_COMPTE, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
            return result != -1;
        }
        else
            return false;
    }

    // @SuppressLint("Recycle")
    public Compte get_solde_compte(String nom){
        db = DBHelper.getWritableDatabase();
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
        db = this.DBHelper.getWritableDatabase();
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

}
