package com.example.expensio.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.expensio.Model.Compte;

import java.util.ArrayList;
import java.util.List;

public class GestDataBase extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "EXPENSIO_DATABASE.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "COMPTE";
    private static final String COL_1 = "NOM_COMPTE";
    private static final String COL_2 = "SOLDE_COMPTE";
    private static final String COL_3 = "REVENU_COMPTE";
    private static final String COL_4 = "DEPENSE_COMPTE";

    public GestDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +"(NOM_COMPTE TEXT PRIMARY KEY, SOLDE_COMPTE INTEGER, REVENU_COMPTE INTEGER, DEPENSE_COMPTE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public Boolean insert_compte(Compte compte){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, compte.getNom_compte());
        values.put(COL_2, 0);
        values.put(COL_3, 0);
        values.put(COL_4, 0);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean update_nom_compte(String nom){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, nom);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0){
            long result = db.update(TABLE_NAME, values, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
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
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0) {
            long result = db.update(TABLE_NAME, values, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
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
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
        if (cursor.getCount() > 0){
            long result = db.delete(TABLE_NAME, "NOM_COMPTE=?", new String[]{String.valueOf(nom)});
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
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME +" WHERE NOM_COMPTE = ?", new String[]{String.valueOf(nom)});
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
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
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
