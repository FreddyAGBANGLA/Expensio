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
import com.example.expensio.Model.Transfert;
import com.example.expensio.Utils.DBCompteAdapter;
import com.example.expensio.Utils.DBTransfertAdapter;
import com.example.expensio.Utils.GestDataBaseAdapter;

import java.util.Calendar;

import soup.neumorphism.NeumorphButton;

public class TransfertActivity extends AppCompatActivity {
    DBCompteAdapter myDBCompte = null;
    DBTransfertAdapter myDBTransfert = null;
    EditText date, time, etn_montant, description;
    NeumorphButton IB_valider;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Spinner spinner_compte_source, spinner_compte_destination;
    String selectedCompteSource, selectedCompteDestination;
    int SoldeSource = 0, RevenusSource = 0, DepensesSource = 0;
    int SoldeDestination = 0, RevenusDestination = 0, DepensesDestination = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfert);

        myDBCompte = new DBCompteAdapter(this);
        myDBTransfert = new DBTransfertAdapter(this);

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

        // Initialisation du spinner du compte source
        spinner_compte_source = findViewById(R.id.spinner_compte_source);

        // Initialisation du spinner du compte destination
        spinner_compte_destination = findViewById(R.id.spinner_compte_destination);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_compte_source = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.compte_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_compte_source.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_compte_source.setAdapter(adapter_compte_source);
        AdapterView.OnItemSelectedListener l_compte_source = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCompteSource = parent.getItemAtPosition(position).toString();
                Compte compte = myDBCompte.get_solde_compte(selectedCompteSource);
                if (compte == null)
                    return;
                SoldeSource = compte.getSolde_compte();
                RevenusSource = compte.getRevenu_compte();
                DepensesSource = compte.getDepense_compte();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_compte_source.setOnItemSelectedListener(l_compte_source);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_compte_destination = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.compte_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter_compte_destination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_compte_destination.setAdapter(adapter_compte_destination);
        AdapterView.OnItemSelectedListener l_compte_destination = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCompteDestination = parent.getItemAtPosition(position).toString();
                Compte compte = myDBCompte.get_solde_compte(selectedCompteDestination);
                if (compte == null)
                    return;
                SoldeDestination = compte.getSolde_compte();
                RevenusDestination = compte.getRevenu_compte();
                DepensesDestination = compte.getDepense_compte();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_compte_destination.setOnItemSelectedListener(l_compte_destination);


        // perform click event on edit text
        date.setOnClickListener(v -> {

            // date picker dialog
            datePickerDialog = new DatePickerDialog(TransfertActivity.this,
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
            timePickerDialog = new TimePickerDialog(TransfertActivity.this,
                    (view, hourOfDay, minute) -> {
                        // set hour of day and minute value in the edit text
                        time.setText((hourOfDay < 10 ? "0"+hourOfDay : hourOfDay)
                                + ":" + (minute < 10 ? "0"+minute : minute));
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        });


        // Lorsqu'on clique sur le bouton Valider
        IB_valider.setOnClickListener(v -> {
            String montant_transfert = etn_montant.getText().toString();

            if (montant_transfert.equals("") || montant_transfert.equals("0")){
                Toast toast = Toast.makeText(getApplicationContext(), "Entrez un montant valide", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
            else if(description.getText().toString().equals("")){
                Toast toast = Toast.makeText(getApplicationContext(), "Entrez une brève description", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
            else{
                int transfert = Integer.parseInt(montant_transfert);
                if (transfert > SoldeSource){
                    Toast toast = Toast.makeText(getApplicationContext(), "Votre solde est insuffisant", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                    etn_montant.setText("0");
                    return;
                }
                DepensesSource += transfert;
                SoldeSource -= transfert;

                RevenusDestination += transfert;
                SoldeDestination += transfert;

                Transfert transfert1 = new Transfert();
                transfert1.setMontant(transfert);
                transfert1.setDate(date.getText().toString());
                transfert1.setHeure(time.getText().toString());
                transfert1.setNom_compte_source(selectedCompteSource);
                transfert1.setNom_compte_destination(selectedCompteDestination);
                transfert1.setDesc(description.getText().toString());

                Intent intent_send = new Intent(getApplicationContext(), HomeActivity.class);
                intent_send.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                myDBCompte.update_solde_compte(selectedCompteSource, SoldeSource, RevenusSource, DepensesSource);
                myDBCompte.update_solde_compte(selectedCompteDestination, SoldeDestination, RevenusDestination, DepensesDestination);
                myDBTransfert.insert_transfert(transfert1);

                startActivity(intent_send);
                finish();
            }
        });

    }
}