package com.example.expensio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DepenseActivity extends AppCompatActivity {

    EditText etn_montant;
    Button btn_valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);

        // SPINNER DU COMPTE
        Spinner spinner_compte = (Spinner) findViewById(R.id.spinner_compte_depense);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_compte = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.compte_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter_compte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_compte.setAdapter(adapter_compte);
        AdapterView.OnItemSelectedListener l = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_compte.setOnItemSelectedListener(l);


        // SPINNER DES CATEGORIES
        Spinner spinner_categorie = (Spinner) findViewById(R.id.spinner_categorie);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_categorie = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.categorie_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter_categorie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_categorie.setAdapter(adapter_categorie);
        AdapterView.OnItemSelectedListener l2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner_categorie.setOnItemSelectedListener(l2);

        etn_montant = (EditText) findViewById(R.id.etn_montant);
        btn_valider = (Button) findViewById(R.id.valider);
        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double montant = Double.parseDouble(etn_montant.getText().toString());
                Intent intent = new Intent(getApplicationContext(), AccueilFragment.class);
                intent.getDoubleExtra("montant", montant);
                startActivity(intent);
            }
        });

    }
}