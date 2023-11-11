package com.example.cyclinggroupapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;

public class EditEventActivity extends AppCompatActivity {

    private EditText editEventName, editEventRegion, editEventType;
    private CollectionReference db;
    private String editEventId; // To store the original event name
    private String eventName,eventRegion,eventType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        db = FirebaseFirestore.getInstance().collection("Events");


        editEventName = findViewById(R.id.editEventName);
        editEventRegion = findViewById(R.id.editEventRegion);
        editEventType = findViewById(R.id.editEventType);

        // Retrieve the passed data
        editEventId = getIntent().getStringExtra("EVENT_ID");
        eventName = getIntent().getStringExtra("EVENT_NAME");
        eventRegion = getIntent().getStringExtra("EVENT_REGION");
        eventType = getIntent().getStringExtra("EVENT_TYPE");

        // Set the hints
        editEventName.setHint(eventName);
        editEventRegion.setHint(eventRegion);
        editEventType.setHint(eventType);

        findViewById(R.id.cancelEdit).setOnClickListener(view ->cancelEditPage());
        findViewById(R.id.saveEventButton).setOnClickListener(view -> saveEventChanges());
    }

    private void fetchEventDetails(String eventName) {
                db.whereEqualTo("EventName", eventName)
                .limit(1)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        // Assuming you have the event's ID
                        String eventId = documentSnapshot.getId();
                        editEventName.setText(documentSnapshot.getString("EventName"));
                        editEventRegion.setText(documentSnapshot.getString("EventRegion"));
                        editEventType.setText(documentSnapshot.getString("EventType"));
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
    }

    private void saveEventChanges() {
        String newEventName = editEventName.getText().toString().trim();
        String newEventRegion = editEventRegion.getText().toString().trim();
        String newEventType = editEventType.getText().toString().trim();

        if (newEventName == null){
            newEventName = eventName;
        }
        if (newEventRegion == null){
            newEventRegion = eventRegion;
        }
        if (newEventType == null){
            newEventType = eventType;
        }

        // Proceed with updating the event
        updateEventInFirestore(newEventName, newEventRegion, newEventType);
    }

    private void updateEventInFirestore(String eventName, String eventRegion, String eventType) {

        db.document(editEventId)
                .update(
                        "EventName", eventName,
                        "EventRegion", eventRegion,
                        "EventType", eventType
                )
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditEventActivity.this, "Event updated successfully", Toast.LENGTH_SHORT).show();
                    // Optionally, navigate back to the previous Activity or perform other actions
                })
                .addOnFailureListener(e -> Toast.makeText(EditEventActivity.this, "Error updating event", Toast.LENGTH_SHORT).show());
    }

    private void cancelEditPage() {
        startActivity(new Intent(this, AdminEventListActivity.class));
    }


}
