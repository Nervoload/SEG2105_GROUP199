package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Bundle;

public class RoadRaceEventInformationPage extends AppCompatActivity {
    private EditText distanceEditText;
    private EditText groupNumberEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_race_event_information_page);

        distanceEditText = findViewById(R.id.distanceEditText);
        groupNumberEditText = findViewById(R.id.groupNumberEditText);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitButtonClick();
            }
        });
    }
    private void onSubmitButtonClick() {
        String distance = distanceEditText.getText().toString();
        String groupNumber = groupNumberEditText.getText().toString();

        // You can perform further actions with the entered data here
        // For now, let's just display a Toast message with the entered data
        String message = "Distance: " + distance + " km\nGroup Number: " + groupNumber;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}