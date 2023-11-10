package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class CreateEventForm extends AppCompatActivity {
    EditText ageEditText, paceEditText, levelEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_form);

        ageEditText = findViewById(R.id.ageEditText);
        paceEditText = findViewById(R.id.paceEditText);
        levelEditText = findViewById(R.id.levelEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered information
                String age = ageEditText.getText().toString();
                String pace = paceEditText.getText().toString();
                String level = levelEditText.getText().toString();

                // Display the information using Toast (you can customize this part)
                String message = "Age: " + age + "\nPace: " + pace + "\nLevel: " + level;
                Toast.makeText(CreateEventForm.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}