package com.example.cyclinggroupapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateTypeActivity extends Activity {
    CollectionReference database;

    EditText nameET, regionET;
    private FirebaseFirestore fstore;
    Button submitButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_type);

        nameET = findViewById(R.id.eventTypeNameEditText);
        regionET = findViewById(R.id.descriptionEditText);
        submitButton = findViewById(R.id.submitButton2);
        backButton = findViewById(R.id.backButton3);
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

    private void back() {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}