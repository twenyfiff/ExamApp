package com.example.examapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class guitarAdapter extends RecyclerView.Adapter<guitarAdapter.MyViewHolder>{
    Context context;
    ArrayList<guitarData> list;

    public guitarAdapter(Context context, ArrayList<guitarData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public guitarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pianoitem, parent, false);
        return new guitarAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull guitarAdapter.MyViewHolder holder, int position) {
        guitarData guitar = list.get(position);
        holder.title.setText(guitar.getTitle());
        holder.color.setText(guitar.getColor());
        holder.price.setText(guitar.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, color, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.firstname);
            color = itemView.findViewById(R.id.lastname);
            price = itemView.findViewById(R.id.age);
        }
    }
}
