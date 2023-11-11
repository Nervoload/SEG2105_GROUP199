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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cyclinggroupapp.databinding.ActivityAdminEventListBinding;
import com.example.cyclinggroupapp.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AdminEventListActivity extends Activity {

    RecyclerView recyclerView;
    DatabaseReference database;
    EventListAdapter myAdapter;
    ArrayList<Event> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_list);

        recyclerView = findViewById(R.id.eventList);
        database = FirebaseDatabase.getInstance().getReference("Events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new EventListAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnClickListener(new EventListAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Event event) {
                Intent intent = new Intent(AdminEventListActivity.this, EventDetails.class);
                intent.putExtra(NEXT_SCREEN,event);
                startActivity(intent);
            }
        });





        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Event event = dataSnapshot.getValue(Event.class);
                    list.add(event);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Button createButton = findViewById(R.id.EventCreatebtn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Replace this with your actual logic for the button click
                Toast.makeText(AdminEventListActivity.this, "Create button clicked!", Toast.LENGTH_SHORT).show();
            }


        });

        Button backButton = findViewById(R.id.BackToProfile);
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
    public static final String NEXT_SCREEN = "EventDetails";
}