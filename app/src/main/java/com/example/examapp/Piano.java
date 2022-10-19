package com.example.examapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Piano extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    pianoAdapter pianoAdapter;
    ArrayList<pianoData> list;
    Button addPiano;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        recyclerView = findViewById(R.id.pianolist);
        addPiano = findViewById(R.id.buttonPianoAdd);
        database = FirebaseDatabase.getInstance("https://examapp-631d0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("piano");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() == null){
            addPiano.setVisibility(View.INVISIBLE);
            //finish();
        }

        list = new ArrayList<>();
        pianoAdapter = new pianoAdapter(this, list);
        recyclerView.setAdapter(pianoAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    pianoData piano = dataSnapshot.getValue(pianoData.class);
                    list.add(piano);
                }
                pianoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void AddPianoBtn(View view) {
        startActivity(new Intent(getApplicationContext(), AddPiano.class));
    }
}