package com.example.expensio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.expensio.Model.Compte;
import com.example.expensio.Model.Depense;
import com.example.expensio.Utils.DBCompteAdapter;
import com.example.expensio.Utils.DBDepenseAdapter;

import java.util.Calendar;

import soup.neumorphism.NeumorphButton;

public class DepenseActivity extends AppCompatActivity {
    DBCompteAdapter myDBCompte = null;
    DBDepenseAdapter myDBDepense = null;
    EditText date, time, etn_montant, description;
    NeumorphButton IB_valider;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Spinner spinner_compte, spinner_categorie_depense;
    String selectedCompte, selectedCategorie, globaCompte = "Compte Global";
    int Solde = 0, Revenus = 0, Depenses = 0;
    int globaSolde = 0, globaRevenus = 0, globaDepenses = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);

        myDBCompte = new DBCompteAdapter(this);
        myDBDepense = new DBDepenseAdapter(this);

        // calender class's instance and get current date, month, year, hour and minute from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        int mHour = c.get(Calendar.HOUR_OF_DAY); // current hour
        int mMinute = c.get(Calendar.MINUTE); // current minute

        // initialiser l'EditText etn_montant
        etn_montant = findViewById(R.id.etn_montant);

        // initialiser l'EditText description
        description = findViewById(R.id.description);

        // initiate the date picker and a button
        date = findViewById(R.id.date);
        date.setText((mDay < 10 ? "0"+mDay : mDay) + "/" +
                ((mMonth + 1) < 10 ? "0"+(mMonth + 1) : (mMonth + 1) ) + "/" + mYear);
        date.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

        // initiate the hour picker and a button
        time = findViewById(R.id.time);
        time.setText((mHour < 10 ? "0"+mHour : mHour) + ":" +
                (mMinute < 10 ? "0"+mMinute: mMinute));

        // Initialisation du bouton Valider
        IB_valider = findViewById(R.id.btn_valider);

        // Initialisation du spinner des comptes
        spinner_compte = findViewById(R.id.spinner_compte);

        // Initialisation du spinner des categories de depenses
        spinner_categorie_depense = findViewById(R.id.spinner_categorie_depense);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_compte = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.compte_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_compte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_compte.setAdapter(adapter_compte);
        selectedCompte = spinner_compte.getItemAtPosition(spinner_compte.getFirstVisiblePosition()).toString();

        AdapterView.OnItemSelectedListener l_compte = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCompte = parent.getItemAtPosition(position).toString();
                Compte compte = myDBCompte.get_solde_compte(selectedCompte);
                if (compte == null)
                    return;
                Solde = compte.getSolde_compte();
                Revenus = compte.getRevenu_compte();
                Depenses = compte.getDepense_compte();
                Compte compteGlobal = myDBCompte.get_solde_compte(globaCompte);
                if(compteGlobal == null)
                    return;
                globaSolde = compteGlobal.getSolde_compte();
                globaRevenus = compteGlobal.getRevenu_compte();
                globaDepenses = compteGlobal.getDepense_compte();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_compte.setOnItemSelectedListener(l_compte);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_categorie = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.categorie_array_depense, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_categorie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_categorie_depense.setAdapter(adapter_categorie);
        AdapterView.OnItemSelectedListener l_categorie = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategorie = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_categorie_depense.setOnItemSelectedListener(l_categorie);


        // perform click event on edit text
        date.setOnClickListener(v -> {

            // date picker dialog
            datePickerDialog = new DatePickerDialog(DepenseActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        date.setText((dayOfMonth < 10 ? "0"+dayOfMonth : dayOfMonth)
                                + "/" + ((monthOfYear + 1) < 10 ? "0"+(monthOfYear + 1) : (monthOfYear + 1))
                                + "/" + year);
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });


        // perform click event on edit text
        time.setOnClickListener(v -> {

            // time picker dialog
            timePickerDialog = new TimePickerDialog(DepenseActivity.this,
                    (view, hourOfDay, minute) -> {
                        // set hour of day and minute value in the edit text
                        time.setText((hourOfDay < 10 ? "0"+hourOfDay : hourOfDay)
                                + ":" + (minute < 10 ? "0"+minute : minute));
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });


        // Lorsqu'on clique sur le bouton Valider
        IB_valider.setOnClickListener(v -> {
            String montant_depense = etn_montant.getText().toString();

            if (montant_depense.equals("") || montant_depense.equals("0")){
                Toast toast = Toast.makeText(getApplicationContext(), "Entrez un montant valide", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
            else{
                int depense = Integer.parseInt(montant_depense);
                if (depense > Solde){
                    Toast toast = Toast.makeText(getApplicationContext(), "Votre solde est insuffisant", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                    etn_montant.setText("0");
                    return;
                }
                Depenses += depense;
                Solde -= depense;
                globaDepenses += depense;
                globaSolde -= depense;

                Depense depense1 = new Depense();
                depense1.setMontant(depense);
                depense1.setCategorie(selectedCategorie);
                depense1.setDate(date.getText().toString());
                depense1.setHeure(time.getText().toString());
                depense1.setDesc(description.getText().toString());
                depense1.setNom_compte(selectedCompte);

                Intent intent_send = new Intent(getApplicationContext(), HomeActivity.class);
                intent_send.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                myDBCompte.update_solde_compte(selectedCompte, Solde, Revenus, Depenses);
                myDBCompte.update_solde_compte(globaCompte, globaSolde, globaRevenus, globaDepenses);
                myDBDepense.insert_depense(depense1);

                Toast toast = Toast.makeText(getApplicationContext(), "Données sauvegardées avec succès !", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();

                startActivity(intent_send);
                finish();
            }
        });

    }
}