package com.example.expensio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageButton;

public class AccueilFragment extends Fragment {
    NeumorphImageButton IB_revenu, IB_depense, IB_transfert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accueil, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_compte);
        //variable neumorphiques

        IB_revenu = view.findViewById(R.id.button_revenus);
        IB_depense = view.findViewById(R.id.button_depense);
        IB_transfert = view.findViewById(R.id.button_transfert);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.compte_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener l = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner.setOnItemSelectedListener(l);


       IB_revenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), RevenusActivity.class);
                startActivity(intent);
            }
        });

        IB_depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DepenseActivity.class);
                startActivity(intent);
            }
        });

        IB_transfert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), TransfertActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}