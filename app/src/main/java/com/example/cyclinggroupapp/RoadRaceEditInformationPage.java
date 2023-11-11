package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class RoadRaceEditInformationPage extends AppCompatActivity {
    private EditText distanceEditText, groupNumberEditText, ageEditText, levelEditText, paceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_race_edit_information_page);

        distanceEditText = findViewById(R.id.distanceEditText);
        groupNumberEditText = findViewById(R.id.groupNumberEditText);
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
                Toast.makeText(RoadRaceEditInformationPage.this, "Back button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Save button click
                Toast.makeText(RoadRaceEditInformationPage.this, "Save button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Delete button click
                Toast.makeText(RoadRaceEditInformationPage.this, "Delete button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}