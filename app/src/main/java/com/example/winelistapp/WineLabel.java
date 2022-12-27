package com.example.winelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Common.Common;
import com.example.winelistapp.Interface.ItenClickListener;
import com.example.winelistapp.Model.Wine;
import com.example.winelistapp.ViewHolder.WineViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WineLabel extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference wineLabel;

    String categoryId = "";

    FirebaseRecyclerAdapter<Wine, WineViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_label);

        database = FirebaseDatabase.getInstance();
        wineLabel = database.getReference("Wines");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_wine);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null) {

            if(Common.isConnectedToTheInternet(getBaseContext()))
                loadListWine(categoryId);
            else{
                Toast.makeText(WineLabel.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }

    private void loadListWine(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Wine, WineViewHolder>(Wine.class,
                R.layout.wine_item,
                WineViewHolder.class,
                wineLabel.orderByChild("WineryId").equalTo(categoryId)
        ) {
            @Override
            protected void populateViewHolder(WineViewHolder wineViewHolder, Wine wine, int i) {
                wineViewHolder.wine_name.setText(wine.getName());

                final Wine local = wine;
                wineViewHolder.setItemClickListener(new ItenClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new Activity
                        Intent wineDetail = new Intent(WineLabel.this, WineDetail.class);
                        wineDetail.putExtra("WineId", adapter.getRef(position).getKey());
                        startActivity(wineDetail);
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }
}
