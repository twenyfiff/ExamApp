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

public class Guitar extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    guitarAdapter guitarAdapter;
    ArrayList<guitarData> list;
    Button addGuitar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar);

        recyclerView = findViewById(R.id.guitarlist);
        addGuitar = findViewById(R.id.buttonGuitarAdd);
        database = FirebaseDatabase.getInstance("https://examapp-631d0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("guitar");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() == null){
            addGuitar.setVisibility(View.INVISIBLE);
            //finish();
        }

        list = new ArrayList<>();
        guitarAdapter = new guitarAdapter(this, list);
        recyclerView.setAdapter(guitarAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    guitarData guitar = dataSnapshot.getValue(guitarData.class);
                    list.add(guitar);
                }
                guitarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void AddGuitarBtn(View view) {
        startActivity(new Intent(getApplicationContext(), AddGuitar.class));
    }
}