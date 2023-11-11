package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class TimeTrialEventInformationPage extends AppCompatActivity {
    EditText timeEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trial_event_information_page);

        timeEditText = findViewById(R.id.timeEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered time
                String time = timeEditText.getText().toString();

                // Validate and process the entered time (you can add your logic here)

                // For demonstration purposes, show a toast with the entered time
                Toast.makeText(TimeTrialEventInformationPage.this, "Time entered: " + time + " seconds", Toast.LENGTH_SHORT).show();
            }
        });
    }
}