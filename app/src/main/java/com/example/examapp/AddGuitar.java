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

public class AddGuitar extends AppCompatActivity {

    Button addGuitarBtn;
    EditText gTitle, gColor, gPrice;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guitar);

        addGuitarBtn = findViewById(R.id.button5);
        gTitle = findViewById(R.id.editGuitarTitle);
        gColor = findViewById(R.id.editGuitarColor);
        gPrice = findViewById(R.id.editGuitarPrice);

        database = FirebaseDatabase.getInstance("https://examapp-631d0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("guitar");

        addGuitarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title_string = gTitle.getText().toString().trim();
                String Color_string = gColor.getText().toString().trim();
                String Price_string = gPrice.getText().toString().trim();

                addNewGuitar(Title_string, Color_string, Price_string);
                startActivity(new Intent(getApplicationContext(), Guitar.class));
            }
        });
    }

    @IgnoreExtraProperties
    public class NewGuitar{
        public String title;
        public String color;
        public String price;

        public NewGuitar(){

        }

        public NewGuitar(String title, String color, String price){
            this.title = title;
            this.color = color;
            this.price = price;
        }
    }

    private void addNewGuitar(String title, String color, String price) {
        AddGuitar.NewGuitar guitar = new AddGuitar.NewGuitar(title, color, price);
        database.push().setValue(guitar);
    }
}