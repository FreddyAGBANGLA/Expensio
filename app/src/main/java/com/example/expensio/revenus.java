package com.example.expensio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;

public class revenus extends AppCompatActivity {
    DatePicker simpleDatePicker = (DatePicker)findViewById(R.id.simpleDatePicker);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenus);
    }
}