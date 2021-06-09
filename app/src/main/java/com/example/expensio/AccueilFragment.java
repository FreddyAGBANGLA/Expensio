package com.example.expensio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensio.Model.Compte;
import com.example.expensio.Model.Revenu;
import com.example.expensio.Utils.DBCompteAdapter;
import com.example.expensio.Utils.DBRevenuAdapter;

import java.util.ArrayList;
import java.util.List;

import soup.neumorphism.NeumorphImageButton;

public class AccueilFragment extends Fragment {
    DBCompteAdapter myDBCompte = null;
    NeumorphImageButton IB_revenu, IB_depense, IB_transfert;
    TextView tv_revenu, tv_depense, tv_solde;
    Spinner spinner;
    String selectedCompte;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accueil, container, false);

        myDBCompte = new DBCompteAdapter(view.getContext());

        tv_revenu = view.findViewById(R.id.revenus);
        tv_depense = view.findViewById(R.id.depenses);
        tv_solde = view.findViewById(R.id.solde);

        spinner = (Spinner) view.findViewById(R.id.spinner_compte);

        //variables neumorphiques

        IB_revenu = view.findViewById(R.id.button_revenus);
        IB_depense = view.findViewById(R.id.button_depense);
        IB_transfert = view.findViewById(R.id.button_transfert);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.compte_array1, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        AdapterView.OnItemSelectedListener l = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCompte = parent.getItemAtPosition(position).toString();
                Compte compte = myDBCompte.get_solde_compte(selectedCompte);
                if (compte == null)
                    return;
                tv_solde.setText(String.valueOf(compte.getSolde_compte()));
                tv_revenu.setText(String.valueOf(compte.getRevenu_compte()));
                tv_depense.setText(String.valueOf(compte.getDepense_compte()));
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
                Intent intent_send = new Intent(view.getContext(), TransfertActivity.class);
                startActivity(intent_send);
            }
        });

        return view;
    }
}