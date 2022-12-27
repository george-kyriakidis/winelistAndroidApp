package com.example.winelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Interface.ItenClickListener;
import com.example.winelistapp.Model.Wine;
import com.example.winelistapp.ViewHolder.WineViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchActivity extends AppCompatActivity {

    EditText searchEditText;
    ImageButton imageButtonSearch;

    //Search Functionality
    FirebaseRecyclerAdapter<Wine, WineViewHolder> searchAdapter;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Wine, WineViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference wineLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = FirebaseDatabase.getInstance();
        wineLabel = database.getReference("Wines");

        searchEditText = (EditText) findViewById(R.id.searchBar);
        imageButtonSearch = (ImageButton) findViewById(R.id.imageButton);
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchEditText.getText().toString();
                startSearch(searchText);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       // loadListWine();
    }

    private void loadListWine() {
        adapter = new FirebaseRecyclerAdapter<Wine, WineViewHolder>(Wine.class,
                R.layout.wine_item_search,
                WineViewHolder.class,
                wineLabel
        ) {
            @Override
            protected void populateViewHolder(WineViewHolder wineViewHolder, Wine wine, int i) {
                wineViewHolder.wine_name.setText(wine.getName());
                wineViewHolder.wine_desc.setText(wine.getDescription());

                final Wine local = wine;
                wineViewHolder.setItemClickListener(new ItenClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new Activity
                        Intent wineDetail = new Intent(SearchActivity.this, WineDetail.class);
                        wineDetail.putExtra("WineId", adapter.getRef(position).getKey());
                        startActivity(wineDetail);
                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }

    private void startSearch(String searchText) {

        Query firebaseQuery = wineLabel.orderByChild("Name").startAt(searchText).endAt(searchText + "\uf8ff");
        searchAdapter = new FirebaseRecyclerAdapter<Wine, WineViewHolder>(
                Wine.class,
                R.layout.wine_item_search,
                WineViewHolder.class,
                firebaseQuery
        ) {
            @Override
            protected void populateViewHolder(WineViewHolder wineViewHolder, Wine wine, int i) {
                wineViewHolder.wine_name.setText(wine.getName());
                wineViewHolder.wine_desc.setText(wine.getDescription());
                wineViewHolder.setItemClickListener(new ItenClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new Activity
                        Intent wineDetail = new Intent(SearchActivity.this, WineDetail.class);
                        wineDetail.putExtra("WineId", searchAdapter.getRef(position).getKey());
                        startActivity(wineDetail);
                    }
                });
            }
        };
        recyclerView.setAdapter(searchAdapter); //Set adapter for Recycler View is Search result
    }

    @Override
    protected void onStop() {
        if (adapter != null)
            adapter.cleanup();
        if (searchAdapter != null)
            searchAdapter.cleanup();
        super.onStop();
    }
}
