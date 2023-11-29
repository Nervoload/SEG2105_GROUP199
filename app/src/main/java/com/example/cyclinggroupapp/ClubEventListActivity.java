package com.example.cyclinggroupapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ClubEventListActivity extends Activity {

    RecyclerView recyclerView;
    CollectionReference database;
    EventListAdapter myAdapter;
    ArrayList<Event> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_owner);

        recyclerView = findViewById(R.id.clubeventList);
        database = FirebaseFirestore.getInstance().collection("Events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new EventListAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnClickListener(new EventListAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Event event) {

                Intent intent = new Intent(ClubEventListActivity.this, EditEventActivity.class);
                intent.putExtra(NEXT_SCREEN,event);
                //intent.putExtra("EVENT_ID", )
                intent.putExtra("EVENT_NAME", event.EventName);
                intent.putExtra("EVENT_REGION", event.EventRegion);
                intent.putExtra("EVENT_TYPE", event.EventType);
                intent.putExtra("EVENT_ID",event.EventId);
                startActivity(intent);
            }
        });


        database.addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for(DocumentSnapshot dataSnapshot: value.getDocuments()){

                    Event event = new Event();
                    event.EventName = (String) dataSnapshot.get("EventName");
                    event.EventRegion = (String) dataSnapshot.get("EventRegion");
                    event.EventType = (String) dataSnapshot.get("EventType");
                    event.EventId = (String) dataSnapshot.getId();
                    list.add(event);

                }
                myAdapter.notifyDataSetChanged();

            }
        });


        Button createButton = findViewById(R.id.ClubEventCreateBtn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace this with your actual logic for the button click
                create();
            }


        });

    }

    private void create() {startActivity(new Intent(this, CreateEventForm.class)); finish();}

    private void back() {
        startActivity(new Intent(this, ProfileActivity.class)); finish();
    }
    public static final String NEXT_SCREEN = "EventDetails";
}