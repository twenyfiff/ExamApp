package com.example.examapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class pianoAdapter extends RecyclerView.Adapter<pianoAdapter.MyViewHolder> {

    Context context;
    ArrayList<pianoData> list;
    DatabaseReference database;
    FirebaseAuth fAuth;


    public pianoAdapter(Context context, ArrayList<pianoData> list) {
        this.context = context;
        this.list = list;
        this.database = database;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pianoitem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pianoData piano = list.get(position);
        holder.title.setText(piano.getTitle());
        holder.color.setText(piano.getColor());
        holder.price.setText(piano.getPrice());

        /*holder.deleteBtn.setOnClickListener(view -> {
            DatabaseReference ref = database;
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, color, price;
        Button deleteBtn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.firstname);
            color = itemView.findViewById(R.id.lastname);
            price = itemView.findViewById(R.id.age);
            deleteBtn = itemView.findViewById(R.id.buttonDELETE);
        }
    }
}
