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
    Button submitButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_form);

        ageEditText = findViewById(R.id.ageEditText);
        paceEditText = findViewById(R.id.paceEditText);
        levelEditText = findViewById(R.id.levelEditText);
        submitButton = findViewById(R.id.submitButton);
        backButton = findViewById(R.id.backButton2);

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
                if (eventType == null || age.equals("") || pace.equals("") || level.equals("")) {
                    Toast.makeText(CreateEventForm.this , "choose type", Toast.LENGTH_LONG);
                } else {

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
}