package com.example.expensio.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.expensio.Model.Depense;
import com.example.expensio.Model.Revenu;

import java.util.ArrayList;
import java.util.List;

public class DBDepenseAdapter extends GestDataBaseAdapter{

    protected static final String TABLE_DEPENSE = "DEPENSE";
    protected static final String ID_DEPENSE = "ID_DEPENSE";
    protected static final String MONTANT_DEPENSE = "MONTANT_DEPENSE";
    protected static final String CATEGORIE_DEPENSE = "CATEGORIE_DEPENSE";
    protected static final String DATE_DEPENSE = "DATE_DEPENSE";
    protected static final String HEURE_DEPENSE = "HEURE_DEPENSE";
    protected static final String DESC_DEPENSE = "DESC_DEPENSE";
    protected static final String NOM_COMPTE_DEPENSE = "NOM_COMPTE_DEPENSE";

    public DBDepenseAdapter(Context context) {
        super(context);
    }

    public Boolean insert_depense(Depense depense){
        db = this.DBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONTANT_DEPENSE, depense.getMontant());
        values.put(CATEGORIE_DEPENSE, depense.getCategorie());
        values.put(DATE_DEPENSE, depense.getDate());
        values.put(HEURE_DEPENSE, depense.getHeure());
        values.put(DESC_DEPENSE, depense.getDesc());
        values.put(NOM_COMPTE_DEPENSE, depense.getNom_compte());
        long result = db.insert(TABLE_DEPENSE, null, values);
        return result != -1;
    }

    public List<Depense> getAllDepenses() {
        db = this.DBHelper.getWritableDatabase();
        List<Depense> depenseList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_DEPENSE, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Depense depenseModel = new Depense();
                depenseModel.setDate(cursor.getString(cursor.getColumnIndex(DATE_DEPENSE)));
                depenseModel.setHeure(cursor.getString(cursor.getColumnIndex(HEURE_DEPENSE)));
                depenseModel.setNom_compte(cursor.getString(cursor.getColumnIndex(NOM_COMPTE_DEPENSE)));
                depenseModel.setMontant(cursor.getInt(cursor.getColumnIndex(MONTANT_DEPENSE)));
                depenseModel.setCategorie(cursor.getString(cursor.getColumnIndex(CATEGORIE_DEPENSE)));
                depenseModel.setDesc(cursor.getString(cursor.getColumnIndex(DESC_DEPENSE)));
                depenseList.add(depenseModel);
            }
        }
        return depenseList;
    }

}
