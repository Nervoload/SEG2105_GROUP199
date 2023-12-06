package com.example.cyclinggroupapp;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ViewEventActivity extends AppCompatActivity {

    private TextView editEventName, editEventRegion, editEventType, viewEventRating, viewEventDescription;
    private EditText editRating;
    private CollectionReference db;
    private String editEventId; // To store the original event name
    private String eventName,eventRegion,eventType;
    private ArrayList<Long> ratings;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        db = FirebaseFirestore.getInstance().collection("Events");

        //setting origin of activity starting

        editEventName = findViewById(R.id.viewEventName);
        editEventRegion = findViewById(R.id.viewEventRegion);
        editEventType = findViewById(R.id.viewEventType);
        viewEventRating = findViewById(R.id.viewEventRating);
        viewEventDescription = findViewById(R.id.viewEventDescription);

        editRating = findViewById(R.id.editEventRating);

        // Retrieve the passed data
        editEventId = getIntent().getStringExtra("EVENT_ID");
        eventName = getIntent().getStringExtra("EVENT_NAME");
        eventRegion = getIntent().getStringExtra("EVENT_REGION");
        eventType = getIntent().getStringExtra("EVENT_TYPE");

        db.document(editEventId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ratings = (ArrayList<Long>) value.get("EventRatings");
                long rate = 0;
                for (int i = 0; i < ratings.size(); i++) {
                    rate += ratings.get(i);
                }
                try {
                    rate = rate / ratings.size();
                } catch (Exception e) {}
                viewEventRating.setText(String.valueOf(rate));
            }
        });

        FirebaseFirestore.getInstance().collection("Event_Type").document(eventType).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                viewEventDescription.setText((String) value.get("Description"));
            }
        });

        // Set the hints
        editEventName.setText(eventName);
        editEventRegion.setText(eventRegion);
        editEventType.setText(eventType);

        findViewById(R.id.cancelEdit).setOnClickListener(view ->cancelEditPage());
        findViewById(R.id.saveEventButton).setOnClickListener(view -> saveEventChanges());
    }

    private void saveEventChanges() {
        try {
            int rating = Integer.parseInt(editRating.getText().toString());
            if (0 <= rating && rating <= 5) {
                updateEventInFirestore(eventName, eventRegion, eventType, rating);
            } else {
                Toast.makeText(ViewEventActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(ViewEventActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
        }


    }

    protected void updateEventInFirestore(String eventName, String eventRegion, String eventType, int rating) {

        Map<String, Object> event = new HashMap<>();
        event.put("EventName", eventName);
        event.put("EventRegion", eventRegion);
        event.put("EventType", eventType);
        ratings.add((long) rating);
        event.put("EventRatings", ratings);
        db.document(editEventId)
                .update(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(ViewEventActivity.this, ProfileActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(ViewEventActivity.this, "Error updating event", Toast.LENGTH_SHORT).show());
    }

    private void cancelEditPage() {
        startActivity(new Intent(this, ParticipantEventListActivity.class));
        finish();
    }


}
