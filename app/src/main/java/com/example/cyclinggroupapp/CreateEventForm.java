package com.example.cyclinggroupapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateEventForm extends Activity {
    String eventType;

    EditText nameET, regionET;
    private FirebaseFirestore fstore;
    Button submitButton, backButton, ttBtn, rrBtn, hcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_form);

        nameET = findViewById(R.id.eventNameEditText);
        regionET = findViewById(R.id.regionEditText);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton2);
        rrBtn = findViewById(R.id.RoadRaceBtn);
        ttBtn = findViewById(R.id.TimeTrialBtn);
        hcBtn = findViewById(R.id.HillClimbBtn);

        /*
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the entered information
                String age = ageEditText.getText().toString();
                String pace = paceEditText.getText().toString();
                String level = levelEditText.getText().toString();


                if (eventType == null || age.equals("") || pace.equals("") || level.equals("")) {
                    Toast.makeText(CreateEventForm.this, "choose type", Toast.LENGTH_LONG);
                } else {
                    Intent i = new Intent();
                    if (eventType.equals("Time-Trail")) {
                        i = new Intent(CreateEventForm.this, TimeTrialEventInformationPage.class);
                    } else if (eventType.equals("Hill-Climb")) {
                        i = new Intent(CreateEventForm.this, HillClimbEditInformationPage.class);
                    } else if (eventType.equals("Road-Race")) {
                        i = new Intent(CreateEventForm.this, RoadRaceEditInformationPage.class);
                    }

                    i.putExtra("age", age);
                    i.putExtra("pace", pace);
                    i.putExtra("level", level);
                    i.putExtra("type", eventType);

                    startActivity(i);
                }
            }
        });
         */

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String region = regionET.getText().toString();
                if (eventType == null || name.equals("") || region.equals("")) {
                    Toast.makeText(CreateEventForm.this, "choose type", Toast.LENGTH_LONG);
                } else {
                    updatedDB(name, region, eventType);
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                back();
            }
        });

        rrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventType = "Road-Race";
                Toast.makeText(CreateEventForm.this, "Road Race", Toast.LENGTH_LONG);
            }
        });

        ttBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventType = "Time-Trail";
                Toast.makeText(CreateEventForm.this, "Time Trail", Toast.LENGTH_LONG);
            }
        });

        hcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventType = "Hill-Climb";
                Toast.makeText(CreateEventForm.this, "Hill Climb", Toast.LENGTH_LONG);
            }
        });

    }

    private void back() {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void updatedDB(String name, String region, String type) {
        fstore = FirebaseFirestore.getInstance();

        long time = System.currentTimeMillis();
        String id = time + "";

        Map<String, Object> event = new HashMap<>();
        event.put("EventName", name);
        event.put("EventRegion", region);
        event.put("EventType", type);

        fstore.collection("Events").document(id).set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(CreateEventForm.this, AdminEventListActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateEventForm.this, "did not work", Toast.LENGTH_SHORT).show();
            }
        });



    }
}