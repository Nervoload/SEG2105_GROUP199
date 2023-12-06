package com.example.cyclinggroupapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchBar;
    private FirebaseAuth firebaseAuth;
    private String username;

    private ArrayList<Event> searchlist = new ArrayList<Event>();
    RecyclerView recyclerView;
    CollectionReference database;
    EventListAdapter adapter;
    ArrayList<Event> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchconfig);

        // Get Event list data
        loadDatabase();

        // Get List View, add data to the view
        recyclerView = findViewById(R.id.searcheventlist);
        searchBar = (SearchView) findViewById(R.id.search);
        searchBar.setOnQueryTextListener(this);
        adapter = new EventListAdapter(this,searchlist);
        recyclerView.setAdapter(adapter);
    }

    private void loadDatabase(){


        database = FirebaseFirestore.getInstance().collection("Events");

        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new EventListAdapter(this, list);
        recyclerView.setAdapter(adapter);

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
                        username = (String) document.get("username");
                        Query query = database.whereEqualTo("EventOwner", username);

                        adapter.setOnClickListener(new EventListAdapter.OnClickListener() {
                            @Override
                            public void onClick(int position, Event event) {

                                Intent intent = new Intent(SearchActivity.this, EditEventActivity.class);
                                intent.putExtra("ACTIVITY_ORIGIN", "Club");
                                //intent.putExtra("EVENT_ID", )
                                intent.putExtra("EVENT_NAME", event.EventName);
                                intent.putExtra("EVENT_REGION", event.EventRegion);
                                intent.putExtra("EVENT_TYPE", event.EventType);
                                intent.putExtra("EVENT_ID",event.EventId);
                                startActivity(intent);
                            }
                        });

                        query.addSnapshotListener(new EventListener<QuerySnapshot>() {

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
                                adapter.notifyDataSetChanged();

                            }
                        });

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }

            }
        });
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    public boolean onQueryTextChange(String text) {
        String searchtext;
        searchtext = text;
        searchtext = searchtext.toLowerCase(Locale.getDefault());
        if (text.length() == 0){
            searchlist.addAll(list);
        }
        else {
            for (Event ev : list) {
                if (ev.getEventName().toLowerCase(Locale.getDefault()).contains(searchtext)) {
                    searchlist.add(ev);
                }
                else if (ev.getEventOwner().toLowerCase(Locale.getDefault()).contains(searchtext)) {
                    searchlist.add(ev);
                }
                else if (ev.getEventType().toLowerCase(Locale.getDefault()).contains(searchtext)) {
                    searchlist.add(ev);
                }
            }
        }
        return false;
    }

}
