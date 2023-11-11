package com.example.cyclinggroupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

import android.os.Bundle;

import com.example.cyclinggroupapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TimeTrialEventInformationPage extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
    private ProgressDialog progressDialog;
    private String level = "", age = "", pace = "", type = "", time = "", userId = "";
    private static final String TAG = "SignUpActivity";
    EditText timeEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_trial_event_information_page);

        timeEditText = findViewById(R.id.timeEditText);
        submitButton = findViewById(R.id.submitButton);

        Intent intent = getIntent();
        age = intent.getStringExtra("age");
        pace = intent.getStringExtra("pace");
        level = intent.getStringExtra("level");
        type = intent.getStringExtra("type");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered time
                String time = timeEditText.getText().toString();

                updatedDB(time);

                // Validate and process the entered time (you can add your logic here)

                // For demonstration purposes, show a toast with the entered time

            }
        });


    }

    private void updatedDB(String time) {
        fstore = FirebaseFirestore.getInstance();

        //long time = System.currentTimeMillis();
        String id = time + "";

        Map<String, Object> event = new HashMap<>();
        event.put("age", age);
        event.put("pace", pace);
        event.put("level", level);
        event.put("type", type);
        event.put("time", time);

        fstore.collection("Events").document(id).set(event).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        Toast.makeText(TimeTrialEventInformationPage.this, "Time entered: " + time + " seconds", Toast.LENGTH_SHORT).show();
    }
}