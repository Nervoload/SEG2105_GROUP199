package com.example.cyclinggroupapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateEventForm extends Activity implements AdapterView.OnItemSelectedListener {
    String eventType;
    CollectionReference database;

    EditText nameET, regionET;
    private FirebaseFirestore fstore;
    Button submitButton, backButton;
    Spinner spinner;

    String activityOrigin;

    ArrayList<String> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_form);

        nameET = findViewById(R.id.eventNameEditText);
        regionET = findViewById(R.id.regionEditText);
        submitButton = findViewById(R.id.submitButton2);
        backButton = findViewById(R.id.backButton3);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // set simple layout resource file
        // for each item of spinner


        database = FirebaseFirestore.getInstance().collection("Event_Type");
        try {
            activityOrigin = getIntent().getStringExtra("ACTIVITY_ORIGIN");
        } catch (java.lang.NullPointerException e) {}


        database.addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentSnapshot dataSnapshot: value.getDocuments()){

                    courses.add((String) dataSnapshot.get("Name"));

                }

                // Create the instance of ArrayAdapter
                // having the list of courses
                ArrayAdapter ad = new ArrayAdapter(CreateEventForm.this, android.R.layout.simple_spinner_item, courses);
                // add the things to the snipper
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Set the ArrayAdapter (ad) data on the
                // Spinner which binds data to spinner
                spinner.setAdapter(ad);

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CreateEventForm.this, "Clicked", Toast.LENGTH_LONG);
                String name = nameET.getText().toString();
                String region = regionET.getText().toString();
                if (eventType == null || name.equals("") || region.equals("")) {
                    Toast.makeText(CreateEventForm.this, "choose type", Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(CreateEventForm.this, "Success", Toast.LENGTH_LONG);
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
                if (activityOrigin.equals("Club")) {
                    startActivity(new Intent(CreateEventForm.this, ClubEventListActivity.class));
                    finish();
                }
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // make toastof name of course
        // which is selected in spinner
        Toast.makeText(CreateEventForm.this, courses.get(i), Toast.LENGTH_LONG);
        eventType = courses.get(i);
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}