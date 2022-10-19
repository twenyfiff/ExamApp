package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //if user = null
    //logoutbtn visibility
    FirebaseAuth fAuth;
    Button mLogoutBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogoutBtn = findViewById(R.id.button);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() == null){
            mLogoutBtn.setVisibility(View.INVISIBLE);
            //finish();
        }
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    public void openPiano(View view) {
        startActivity(new Intent(getApplicationContext(), Piano.class));
    }

    public void openGuitar(View view) {
        startActivity(new Intent(getApplicationContext(), Guitar.class));
    }
}