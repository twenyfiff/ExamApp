package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class AddPiano extends AppCompatActivity {

    Button addPianoBtn;
    EditText pTitle, pColor, pPrice;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piano);

        addPianoBtn = findViewById(R.id.button4);
        pTitle = findViewById(R.id.editPianoTitle);
        pColor = findViewById(R.id.editPianoColor);
        pPrice = findViewById(R.id.editPianoPrice);

        database = FirebaseDatabase.getInstance("https://examapp-631d0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("piano");

        addPianoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title_string = pTitle.getText().toString().trim();
                String Color_string = pColor.getText().toString().trim();
                String Price_string = pPrice.getText().toString().trim();

                addNewPiano(Title_string, Color_string, Price_string);
                startActivity(new Intent(getApplicationContext(), Piano.class));
            }
        });
    }

    @IgnoreExtraProperties
    public class NewPiano{
        public String title;
        public String color;
        public String price;

        public NewPiano(){

        }

        public NewPiano(String title, String color, String price){
            this.title = title;
            this.color = color;
            this.price = price;
        }
    }

    private void addNewPiano(String title, String color, String price) {
        NewPiano piano = new NewPiano(title, color, price);
        database.push().setValue(piano);
    }
}