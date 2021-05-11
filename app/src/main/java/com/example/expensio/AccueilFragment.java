package com.example.expensio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AccueilFragment extends Fragment {
    ImageButton B;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.fragment_accueil, container, false);
            B = view.findViewById(R.id.button_revenu);

            B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),RevenusActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }
}