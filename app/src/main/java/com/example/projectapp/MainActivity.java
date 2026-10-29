package com.example.projectapp;

import static android.view.Gravity.LEFT;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> Items;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Items = new ArrayList<Item>();
        Item item = new Item("Item 1", "Description 1", 1);
        Items.add(item);
        Item item2 = new Item("Item 2", "Description 2", 2);
        Items.add(item2);
        Item item3 = new Item("Item 3", "Description 3", 3);
        Items.add(item3);
        Item item4 = new Item("Item 4", "Description 4", 4);
        Items.add(item4);
        Item item5 = new Item("Item 5", "Description 5", 5);
        Items.add(item5);
        Item item6 = new Item("Item 6", "Description 6", 6);
        Items.add(item6);
        Item item7 = new Item("Item 7", "Description 7", 7);
        Items.add(item7);
        Item item8 = new Item("Item 8", "Description 8", 8);
        Items.add(item8);
        Item item9 = new Item("Item 9", "Description 9", 9);
        Items.add(item9);
        Item item10 = new Item("Item 10", "Description 10", 10);
        Items.add(item10);

        recyclerView = findViewById(R.id.recycler);

        MyAdapter adapter = new MyAdapter(Items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,  @NonNull RecyclerView.ViewHolder target) {
            int fromPos = viewHolder.getAdapterPosition();
            int toPos = target.getAdapterPosition();

            Collections.swap(Items, fromPos, toPos); // ArrayList ב שינוי
            adapter.notifyItemMoved(fromPos, toPos);
            return true;
        }
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (direction == ItemTouchHelper.RIGHT) {
                Items.remove(position);
                adapter.notifyDataSetChanged();
            }
        }
    };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        Button btnAdd = findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random rand = new Random();
                int num = rand.nextInt(100);
                Item item = new Item("Item 1", "Description 1", 1);
                Items.add(item);
                adapter.notifyDataSetChanged();
            }
        });
    }
}