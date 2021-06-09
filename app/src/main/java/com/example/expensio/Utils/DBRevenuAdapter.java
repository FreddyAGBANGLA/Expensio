package com.example.expensio.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.expensio.Model.Revenu;

import java.util.ArrayList;
import java.util.List;

public class DBRevenuAdapter extends GestDataBaseAdapter{

    protected static final String TABLE_REVENU = "REVENU";
    protected static final String ID_REVENU = "ID_REVENU";
    protected static final String MONTANT_REVENU = "MONTANT_REVENU";
    protected static final String CATEGORIE_REVENU = "CATEGORIE_REVENU";
    protected static final String DATE_REVENU = "DATE_REVENU";
    protected static final String HEURE_REVENU = "HEURE_REVENU";
    protected static final String DESC_REVENU = "DESC_REVENU";
    protected static final String NOM_COMPTE_REVENU = "NOM_COMPTE_REVENU";

    public DBRevenuAdapter(Context context) {
        super(context);
    }

    public Boolean insert_revenu(Revenu revenu){
        db = this.DBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MONTANT_REVENU, revenu.getMontant());
        values.put(CATEGORIE_REVENU, revenu.getCategorie());
        values.put(DATE_REVENU, revenu.getDate());
        values.put(HEURE_REVENU, revenu.getHeure());
        values.put(DESC_REVENU, revenu.getDesc());
        values.put(NOM_COMPTE_REVENU, revenu.getNom_compte());
        long result = db.insert(TABLE_REVENU, null, values);
        return result != -1;
    }

    public List<Revenu> getRevenusByPeriod(String date_debut, String date_fin){
        db = this.DBHelper.getWritableDatabase();
        List<Revenu> revenuList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_REVENU +" WHERE "+ DATE_REVENU +" BETWEEN ? AND ? ORDER BY "+ DATE_REVENU +" DESC", new String[]{date_debut, date_fin});
        if (cursor != null){
            while (cursor.moveToNext()){
                Revenu revenuModel = new Revenu();
                revenuModel.setDate(cursor.getString(cursor.getColumnIndex(DATE_REVENU)));
                revenuModel.setHeure(cursor.getString(cursor.getColumnIndex(HEURE_REVENU)));
                revenuModel.setNom_compte(cursor.getString(cursor.getColumnIndex(NOM_COMPTE_REVENU)));
                revenuModel.setMontant(cursor.getInt(cursor.getColumnIndex(MONTANT_REVENU)));
                revenuModel.setCategorie(cursor.getString(cursor.getColumnIndex(CATEGORIE_REVENU)));
                revenuModel.setDesc(cursor.getString(cursor.getColumnIndex(DESC_REVENU)));
                revenuList.add(revenuModel);
            }
        }
        return revenuList;
    }

    public String inverseDate(String date){
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6);
        String inversedDate = year +"/"+ month +"/"+ day;
        return inversedDate;
    }

    public Boolean EstInferieur(String dateToCompare, String date){
        String yearToCompare = dateToCompare.substring(6);
        String year = date.substring(6);
        if (Integer.parseInt(yearToCompare) < Integer.parseInt(year))
            return true;
        else if (Integer.parseInt(yearToCompare) > Integer.parseInt(year))
            return false;

        String monthToCompare = dateToCompare.substring(3, 5);
        String month = date.substring(3, 5);
        if (Integer.parseInt(monthToCompare) < Integer.parseInt(month))
            return true;
        else if (Integer.parseInt(monthToCompare) > Integer.parseInt(month))
            return false;

        String dayToCompare = dateToCompare.substring(0, 2);
        String day = date.substring(0, 2);
        if (Integer.parseInt(dayToCompare) < Integer.parseInt(day))
            return true;
        else if (Integer.parseInt(dayToCompare) > Integer.parseInt(day))
            return false;
        else
            return false;
    }

    public Boolean EstSuperieur(String dateToCompare, String date){
        String yearToCompare = dateToCompare.substring(6);
        String year = date.substring(6);
        if (Integer.parseInt(yearToCompare) > Integer.parseInt(year))
            return true;
        else if (Integer.parseInt(yearToCompare) < Integer.parseInt(year))
            return false;

        String monthToCompare = dateToCompare.substring(3, 5);
        String month = date.substring(3, 5);
        if (Integer.parseInt(monthToCompare) > Integer.parseInt(month))
            return true;
        else if (Integer.parseInt(monthToCompare) < Integer.parseInt(month))
            return false;

        String dayToCompare = dateToCompare.substring(0, 2);
        String day = date.substring(0, 2);
        if (Integer.parseInt(dayToCompare) > Integer.parseInt(day))
            return true;
        else if (Integer.parseInt(dayToCompare) < Integer.parseInt(day))
            return false;
        else
            return false;
    }

    public List<Revenu> getAllRevenus() {
        db = this.DBHelper.getWritableDatabase();
        List<Revenu> revenuList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_REVENU, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                Revenu revenuModel = new Revenu();
                revenuModel.setDate(cursor.getString(cursor.getColumnIndex(DATE_REVENU)));
                revenuModel.setHeure(cursor.getString(cursor.getColumnIndex(HEURE_REVENU)));
                revenuModel.setNom_compte(cursor.getString(cursor.getColumnIndex(NOM_COMPTE_REVENU)));
                revenuModel.setMontant(cursor.getInt(cursor.getColumnIndex(MONTANT_REVENU)));
                revenuModel.setCategorie(cursor.getString(cursor.getColumnIndex(CATEGORIE_REVENU)));
                revenuModel.setDesc(cursor.getString(cursor.getColumnIndex(DESC_REVENU)));
                revenuList.add(revenuModel);
            }
        }
        return revenuList;
    }

}
