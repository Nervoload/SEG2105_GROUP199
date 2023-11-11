package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class HillClimbEditInformationPage extends AppCompatActivity {
    private EditText heightEditText, ageEditText, levelEditText, paceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_climb_edit_information_page);

        // Initialize EditText fields
        heightEditText = findViewById(R.id.editTextHeight);
        ageEditText = findViewById(R.id.editTextAge);
        levelEditText = findViewById(R.id.editTextLevel);
        paceEditText = findViewById(R.id.editTextPace);

        // Initialize buttons
        Button backButton = findViewById(R.id.buttonBack);
        Button saveButton = findViewById(R.id.buttonSave);
        Button deleteButton = findViewById(R.id.buttonDelete);

        // Set click listeners for buttons
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back button click
                onBackPressed();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle save button click
                // Add your save logic here
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                // set the instance variables to the values in the edit text fields
                int height = heightEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String level = levelEditText.getText().toString();
                String pace = paceEditText.getText().toString();
                
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle delete button click
                // Add your delete logic here
            }
        });
    }
}