package com.example.expensio.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.expensio.Model.Transfert;

import java.util.ArrayList;
import java.util.List;

public class DBTransfertAdapter extends GestDataBaseAdapter {

    protected static final String TABLE_TRANSFERT = "TRANSFERT";
    protected static final String ID_TRANSFERT = "ID_TRANSFERT";
    protected static final String MONTANT_TRANSFERT = "MONTANT_TRANSFERT";
    protected static final String DATE_TRANSFERT = "DATE_TRANSFERT";
    protected static final String HEURE_TRANSFERT = "HEURE_TRANSFERT";
    protected static final String DESC_TRANSFERT = "DESC_TRANSFERT";
    protected static final String NOM_COMPTE_SOURCE_TRANSFERT = "NOM_COMPTE_SOURCE_TRANSFERT";
    protected static final String NOM_COMPTE_DESTINATION_TRANSFERT = "NOM_COMPTE_DESTINATION_TRANSFERT";


    public DBTransfertAdapter(Context context) {
        super(context);
    }

    public Boolean insert_transfert(Transfert transfert){
        db = this.DBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONTANT_TRANSFERT, transfert.getMontant());
        values.put(DATE_TRANSFERT, transfert.getDate());
        values.put(HEURE_TRANSFERT, transfert.getHeure());
        values.put(DESC_TRANSFERT, transfert.getDesc());
        values.put(NOM_COMPTE_SOURCE_TRANSFERT, transfert.getNom_compte_source());
        values.put(NOM_COMPTE_DESTINATION_TRANSFERT, transfert.getNom_compte_destination());
        long result = db.insert(TABLE_TRANSFERT, null, values);
        return result != -1;
    }

    public List<Transfert> getAllTransferts() {
        db = this.DBHelper.getWritableDatabase();
        List<Transfert> transfertList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_TRANSFERT, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Transfert transfertModel = new Transfert();
                transfertModel.setDate(cursor.getString(cursor.getColumnIndex(DATE_TRANSFERT)));
                transfertModel.setHeure(cursor.getString(cursor.getColumnIndex(HEURE_TRANSFERT)));
                transfertModel.setMontant(cursor.getInt(cursor.getColumnIndex(MONTANT_TRANSFERT)));
                transfertModel.setNom_compte_source(cursor.getString(cursor.getColumnIndex(NOM_COMPTE_SOURCE_TRANSFERT)));
                transfertModel.setNom_compte_destination(cursor.getString(cursor.getColumnIndex(NOM_COMPTE_DESTINATION_TRANSFERT)));
                transfertModel.setDesc(cursor.getString(cursor.getColumnIndex(DESC_TRANSFERT)));
                transfertList.add(transfertModel);
            }
        }
        return transfertList;
    }

}
