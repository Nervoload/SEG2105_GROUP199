package com.example.cyclinggroupapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyclinggroupapp.databinding.ActivityAdminEventListBinding;
import com.example.cyclinggroupapp.databinding.ActivityCreateEventFormBinding;
import com.example.cyclinggroupapp.databinding.ActivityLoginBinding;
import com.example.cyclinggroupapp.databinding.ActivitySignUpBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class CreateEventForm extends Activity {
    String eventType;

    EditText ageEditText, paceEditText, levelEditText;
    Button submitButton, backButton, ttBtn, rrBtn, hcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_form);

        ageEditText = findViewById(R.id.ageEditText);
        paceEditText = findViewById(R.id.paceEditText);
        levelEditText = findViewById(R.id.levelEditText);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton2);
        rrBtn = findViewById(R.id.RoadRaceBtn);
        ttBtn = findViewById(R.id.TimeTrialBtn);
        hcBtn = findViewById(R.id.HillClimbBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the entered information
                String age = ageEditText.getText().toString();
                String pace = paceEditText.getText().toString();
                String level = levelEditText.getText().toString();


                if (eventType == null || age.equals("") || pace.equals("") || level.equals("")) {
                    Toast.makeText(CreateEventForm.this , "choose type", Toast.LENGTH_LONG);
                } else {
                    if (eventType.equals("Time-Trail")) {
                        startActivity(new Intent(CreateEventForm.this, TimeTrialEventInformationPage.class));
                    } else if (eventType.equals("Hill-Climb")) {
                        startActivity(new Intent(CreateEventForm.this, HillClimbEditInformationPage.class));
                    } else if (eventType.equals("Road-Race")) {
                        startActivity(new Intent(CreateEventForm.this, RoadRaceEditInformationPage.class));
                    }
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
            }
        });

        ttBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventType = "Time-Trail";
            }
        });

        hcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventType = "Hill-Climb";
            }
        });

    }
    private void back() {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}