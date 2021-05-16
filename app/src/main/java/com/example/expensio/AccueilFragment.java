package com.example.expensio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphImageButton;

public class AccueilFragment extends Fragment {
    NeumorphImageButton IB_revenu, IB_depense, IB_transfert;
    TextView tv_revenu, tv_depense, tv_solde;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accueil, container, false);

        tv_revenu = view.findViewById(R.id.revenus);
        tv_depense = view.findViewById(R.id.depenses);
        tv_solde = view.findViewById(R.id.solde);

        Intent intent_reveive = getActivity().getIntent();
        if(intent_reveive != null){
            if(intent_reveive.hasExtra("solde")
                    && intent_reveive.hasExtra("revenu")
                    && intent_reveive.hasExtra("depense")){
                tv_solde.setText(intent_reveive.getStringExtra("solde"));
                tv_revenu.setText(intent_reveive.getStringExtra("revenu"));
                tv_depense.setText(intent_reveive.getStringExtra("depense"));
            }
        }


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_compte);

        //variables neumorphiques

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

                Bundle bundle = new Bundle();
                bundle.putString("solde", tv_solde.getText().toString());
                bundle.putString("revenu", tv_revenu.getText().toString());
                bundle.putString("depense", tv_depense.getText().toString());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        IB_depense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), DepenseActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("solde", tv_solde.getText().toString());
                bundle.putString("revenu", tv_revenu.getText().toString());
                bundle.putString("depense", tv_depense.getText().toString());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        IB_transfert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_send = new Intent(view.getContext(), TransfertActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("solde", tv_solde.getText().toString());
                bundle.putString("revenu", tv_revenu.getText().toString());
                bundle.putString("depense", tv_depense.getText().toString());
                intent_send.putExtras(bundle);

                startActivity(intent_send);
            }
        });

        return view;
    }
}