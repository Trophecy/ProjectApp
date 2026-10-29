package com.example.projectapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Item> mData;

    public MyAdapter(ArrayList<Item> data) {
        mData = data;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imageView;

        public final TextView textView;

        public final TextView textView2;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Item data = mData.get(position);
                Toast.makeText(parent.getContext(), data.getDescription(),
                        Toast.LENGTH_LONG).show();

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Item item = mData.get(position);
        holder.textView.setText(item.getName());
        holder.textView2.setText(item.getDescription());
        holder.imageView.setImageResource(R.drawable.cat);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
