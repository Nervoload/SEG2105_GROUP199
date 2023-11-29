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

public class CreateTypeActivity extends Activity {

    EditText nameET, regionET;
    private FirebaseFirestore fstore;
    Button submitButton, backButton;
    ArrayList<String> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_type);

        nameET = findViewById(R.id.eventTypeNameEditText);
        regionET = findViewById(R.id.descriptionEditText);
        submitButton = findViewById(R.id.submitButton2);
        backButton = findViewById(R.id.backButton3);

        // set simple layout resource file
        // for each item of spinner

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameET.getText().toString();
                String region = regionET.getText().toString();
                    Toast.makeText(CreateTypeActivity.this, "Success", Toast.LENGTH_LONG);
                    updatedDB(name, region);
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

    private void updatedDB(String name, String region) {
        fstore = FirebaseFirestore.getInstance();

        String id = name;

        Map<String, Object> event = new HashMap<>();
        event.put("Name", name);
        event.put("Description", region);

        fstore.collection("Event_Type").document(id).set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(CreateTypeActivity.this, AdminEventListActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CreateTypeActivity.this, "did not work", Toast.LENGTH_SHORT).show();
            }
        });



    }


}