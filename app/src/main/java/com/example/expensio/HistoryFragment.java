package com.example.expensio;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.expensio.Model.Depense;
import com.example.expensio.Model.Revenu;
import com.example.expensio.Model.Transfert;
import com.example.expensio.Utils.DBDepenseAdapter;
import com.example.expensio.Utils.DBRevenuAdapter;
import com.example.expensio.Utils.DBTransfertAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryFragment extends Fragment {
    DBRevenuAdapter myDBRevenu = null;
    DBDepenseAdapter myDBDepense = null;
    DBTransfertAdapter myDBTransfert = null;

    RevenuAdapter revenuAdapter;
    DepenseAdapter depenseAdapter;
    TransfertAdapter transfertAdapter;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    List<Revenu> revenuList = new ArrayList<>();
    List<Depense> depenseList = new ArrayList<>();
    List<Transfert> transfertList = new ArrayList<>();

    DatePickerDialog datePickerDialog;
    Spinner type_spinner;
    EditText date_debut, date_fin;
    String typeSelected, selectedDateDebut = "", selectedDateFin = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        myDBRevenu = new DBRevenuAdapter(view.getContext());
        myDBDepense = new DBDepenseAdapter(view.getContext());
        myDBTransfert = new DBTransfertAdapter(view.getContext());

        // calender class's instance and get current date, month, year, hour and minute from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        type_spinner = view.findViewById(R.id.spinner_type);
        date_debut = view.findViewById(R.id.edit_date_debut);
        date_fin = view.findViewById(R.id.edit_date_fin);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(view.getContext(),
                R.array.type_transtaction, android.R.layout.select_dialog_item);
        // Specify the layout to use when the list of choices appears
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        type_spinner.setAdapter(adapter_type);
        AdapterView.OnItemSelectedListener l_type = new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSelected = parent.getItemAtPosition(position).toString();
                date_debut.setText("");
                date_fin.setText("");
                selectedDateDebut = "";
                selectedDateFin = "";
                switch (typeSelected) {
                    case "Revenus":
                        revenuList = myDBRevenu.getAllRevenus();
                        revenuAdapter = new RevenuAdapter(view.getContext(), revenuList, recyclerView);
                        recyclerView.setAdapter(revenuAdapter);
                        break;
                    case "Dépenses":
                        depenseList = myDBDepense.getAllDepenses();
                        depenseAdapter = new DepenseAdapter(view.getContext(), depenseList, recyclerView);
                        recyclerView.setAdapter(depenseAdapter);
                        break;
                    case "Transferts":
                        transfertList = myDBTransfert.getAllTransferts();
                        transfertAdapter = new TransfertAdapter(view.getContext(), transfertList, recyclerView);
                        recyclerView.setAdapter(transfertAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        type_spinner.setOnItemSelectedListener(l_type);

        date_debut.setOnClickListener(v -> {

            // date picker dialog
            datePickerDialog = new DatePickerDialog(view.getContext(),
                    (viewDatePicker, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        date_debut.setText(String.format("%s/%s/%d", (dayOfMonth < 10) ? ("0" + dayOfMonth) : dayOfMonth, ((monthOfYear + 1) < 10) ? ("0" + (monthOfYear + 1)) : (monthOfYear + 1), year));
                        selectedDateDebut = date_debut.getText().toString();
                        showRegister(view);
                    },
                    mYear,
                    mMonth,
                    mDay);
            datePickerDialog.show();
        });


        date_fin.setOnClickListener(v -> {

            // date picker dialog
            datePickerDialog = new DatePickerDialog(view.getContext(),
                    (viewDatePicker, year, monthOfYear, dayOfMonth) -> {
                        // set day of month , month and year value in the edit text
                        date_fin.setText(String.format("%s/%s/%d", (dayOfMonth < 10) ? ("0" + dayOfMonth) : dayOfMonth, ((monthOfYear + 1) < 10) ? ("0" + (monthOfYear + 1)) : (monthOfYear + 1), year));
                        selectedDateFin = date_fin.getText().toString();
                        showRegister(view);
                    },
                    mYear,
                    mMonth,
                    mDay);
            datePickerDialog.show();
        });


        return view;
    }

    public void showRegister(View view){
        if(!selectedDateDebut.isEmpty() && !selectedDateFin.isEmpty()) {
            switch (typeSelected) {
                case "Revenus":
                    revenuList = myDBRevenu.getRevenusByPeriod(selectedDateDebut, selectedDateFin);
                    revenuAdapter = new RevenuAdapter(view.getContext(), revenuList, recyclerView);
                    recyclerView.setAdapter(revenuAdapter);
                    break;
                case "Dépenses":
                    depenseList = myDBDepense.getAllDepenses();
                    depenseAdapter = new DepenseAdapter(view.getContext(), depenseList, recyclerView);
                    recyclerView.setAdapter(depenseAdapter);
                    break;
                case "Transferts":
                    transfertList = myDBTransfert.getAllTransferts();
                    transfertAdapter = new TransfertAdapter(view.getContext(), transfertList, recyclerView);
                    recyclerView.setAdapter(transfertAdapter);
                    break;
            }
        }
    }
}