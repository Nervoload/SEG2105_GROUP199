package com.example.cyclinggroupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EventDetails extends AppCompatActivity {





    String [] item = {"Event01", "Event02","Event03","Event04"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_options);
    }
}