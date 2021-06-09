package com.example.expensio.Utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.expensio.Utils.DBCompteAdapter.TABLE_COMPTE;
import static com.example.expensio.Utils.DBDepenseAdapter.CATEGORIE_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.DATE_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.DESC_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.HEURE_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.ID_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.MONTANT_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.NOM_COMPTE_DEPENSE;
import static com.example.expensio.Utils.DBDepenseAdapter.TABLE_DEPENSE;
import static com.example.expensio.Utils.DBRevenuAdapter.CATEGORIE_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.DATE_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.DESC_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.HEURE_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.ID_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.MONTANT_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.NOM_COMPTE_REVENU;
import static com.example.expensio.Utils.DBRevenuAdapter.TABLE_REVENU;
import static com.example.expensio.Utils.DBTransfertAdapter.DATE_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.DESC_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.HEURE_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.ID_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.MONTANT_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.NOM_COMPTE_DESTINATION_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.NOM_COMPTE_SOURCE_TRANSFERT;
import static com.example.expensio.Utils.DBTransfertAdapter.TABLE_TRANSFERT;

public abstract class GestDataBaseAdapter{

    private Context context;
    protected GestDataBaseHelper DBHelper = null;
    protected SQLiteDatabase db = null;

    private static final String DATABASE_NAME = "EXPENSIO_DATABASE.db";
    private static final int DATABASE_VERSION = 6;

    private static final String CREATETABLECOMPTE = "CREATE TABLE IF NOT EXISTS "+ TABLE_COMPTE
            +"(NOM_COMPTE TEXT PRIMARY KEY, SOLDE_COMPTE INTEGER, " +
            "REVENU_COMPTE INTEGER, DEPENSE_COMPTE INTEGER)";
    private static final String CREATETABLEREVENU = "CREATE TABLE IF NOT EXISTS "+ TABLE_REVENU
            +"("+ID_REVENU+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MONTANT_REVENU+" INTEGER, "
            +CATEGORIE_REVENU+" TEXT, "
            +DATE_REVENU+" TEXT, "
            +HEURE_REVENU+" TEXT, "
            +DESC_REVENU+" TEXT, "
            +NOM_COMPTE_REVENU+" TEXT)";
    private static final String CREATETABLEDEPENSE = "CREATE TABLE IF NOT EXISTS "+ TABLE_DEPENSE
            +"("+ID_DEPENSE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +MONTANT_DEPENSE+" INTEGER, "
            +CATEGORIE_DEPENSE+" TEXT, "
            +DATE_DEPENSE+" TEXT, "
            +HEURE_DEPENSE+" TEXT, "
            +DESC_DEPENSE+" TEXT, "
            +NOM_COMPTE_DEPENSE+" TEXT)";
    private static final String CREATETABLETRANSFERT = "CREATE TABLE IF NOT EXISTS "+ TABLE_TRANSFERT
            +"("+ID_TRANSFERT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +MONTANT_TRANSFERT+" INTEGER,  "
            +DATE_TRANSFERT+" TEXT, "
            +HEURE_TRANSFERT+" TEXT, "
            +DESC_TRANSFERT+" TEXT, "
            +NOM_COMPTE_SOURCE_TRANSFERT+" TEXT, "
            +NOM_COMPTE_DESTINATION_TRANSFERT+" TEXT )";

    public GestDataBaseAdapter(Context context){
        this.context = context;
        this.DBHelper = new GestDataBaseHelper(this.context);
    }

    public static class GestDataBaseHelper extends SQLiteOpenHelper {

        public GestDataBaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATETABLECOMPTE);
            db.execSQL(CREATETABLEREVENU);
            db.execSQL(CREATETABLEDEPENSE);
            db.execSQL(CREATETABLETRANSFERT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_COMPTE);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_REVENU);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DEPENSE);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRANSFERT);
            onCreate(db);
        }
    }

    public GestDataBaseAdapter open() throws SQLException{
        this.DBHelper = new GestDataBaseHelper(context);
        this.db = this.DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        if (this.db.isOpen())
            this.DBHelper.close();
    }

}

