package com.example.cyclinggroupapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cyclinggroupapp.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    public static String username;
    public static String role;

    private FirebaseAuth firebaseAuth;

    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Login");

        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        //logout user by clicking

        binding.logoutBtn.setOnClickListener(view -> {
            firebaseAuth.signOut();
            checkUser();
        });

        binding.nextButton.setOnClickListener(view -> {
            if (role.equals("Admin")) {
                startActivity(new Intent(ProfileActivity.this, AdminEventListActivity.class));
            } else {
                Toast.makeText(ProfileActivity.this, "Not Implemented yet", Toast.LENGTH_LONG).show();
            }
        });



    }

    private void checkUser() {
//check if user is already logged in
        //if already logged in then open profile activity

        //get current user

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            //not logged in

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String document = firebaseAuth.getCurrentUser().getUid();
            DocumentReference docRef = db.collection("users").document(document);


            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    String TAG= "TAG";
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            String username = (String) document.get("username");
                            String role = (String) document.get("role");
                            binding.emailTv.setText(username);

                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }

                }
            });


        }
    }
    public void logIn(View view) {
        // Assume authentication is done and you have the user's ID
        String userId = username; // get the user's ID from authentication

        // Get user's role from Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String role = task.getResult().getString("role");

                // Direct user based on their role
                if ("admin".equals(role)) {
                    // Open Admin Activity
                    Intent adminIntent = new Intent(this, AdminEventListActivity.class);
                    startActivity(adminIntent);
                } else {
                    // Open Landing Page for other roles
                    Intent landingIntent = new Intent(this, LandingPageActivity.class);
                    startActivity(landingIntent);
                }

                finish(); // Close the login activity
            } else {
                // Handle the error
            }
        });

    }
    public void logOff(View view) {
        // Log out from Firebase Auth
        FirebaseAuth.getInstance().signOut();

        // Redirect to the Login Screen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }

}