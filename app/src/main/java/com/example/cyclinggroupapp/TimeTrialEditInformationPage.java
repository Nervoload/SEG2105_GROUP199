package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;

public class TimeTrialEditInformationPage extends AppCompatActivity {
    private EditText timeEditText, ageEditText, levelEditText, paceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trial_edit_information_page);

        timeEditText = findViewById(R.id.timeEditText);
        ageEditText = findViewById(R.id.eventNameEditText);
        levelEditText = findViewById(R.id.regionEditText);
        paceEditText = findViewById(R.id.paceEditText);

        Button backButton = findViewById(R.id.backButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Back button click
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Save button click
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Delete button click
            }
        });
    }
}