package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class HillClimbEventInformationPage extends AppCompatActivity {

    private EditText heightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill_climb_event_information_page);

        heightEditText = findViewById(R.id.editTextHeight);
        Button submitButton = findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entered height
                String heightText = heightEditText.getText().toString();

                // Perform validation if needed

                // Display a toast message with the entered height
                Toast.makeText(HillClimbEventInformationPage.this, "Height Entered: " + heightText + " km", Toast.LENGTH_SHORT).show();
            }
        });
    }
}