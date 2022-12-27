package com.example.winelistapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.andremion.counterfab.CounterFab;
import com.example.winelistapp.Common.Common;
import com.example.winelistapp.Database.Database;
import com.example.winelistapp.Model.Wine;
import com.example.winelistapp.Model.Winelist;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WineDetail extends AppCompatActivity {

    TextView wine_name;
    TextView wine_price;
    TextView wine_description;
    TextView total_price;
    EditText wine_discount;
    ImageButton check_price;
    CollapsingToolbarLayout collapsingToolbarLayout;
    CounterFab btnAdd;

    String wineId="";
    FirebaseDatabase database;
    DatabaseReference wines;
    Wine currentWine;
    String quantity = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        wines = database.getReference("Wines");

        //Init View
        btnAdd = (CounterFab) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addCart(new Winelist(
                        wineId,
                        currentWine.getName(),
                        quantity,
                        currentWine.getPrice(),
                        wine_discount.getText().toString(),
                        total_price.getText().toString()
                ));

                Toast.makeText(WineDetail.this, "Added to Winelist", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setCount(new Database(this).getCountCart());

        wine_description = (TextView)findViewById(R.id.wine_description);
        wine_name = (TextView)findViewById(R.id.wine_name);
        wine_price = (TextView)findViewById(R.id.wine_price);
        wine_discount = (EditText) findViewById(R.id.wine_discount);
        total_price = (TextView)findViewById(R.id.wine_total_price);
        check_price = (ImageButton)findViewById(R.id.check_price);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppbar);

        //Get WineId from Intent
        if(getIntent() !=null)
            wineId = getIntent().getStringExtra("WineId");
        if(!wineId.isEmpty()){

            if(Common.isConnectedToTheInternet(getBaseContext()))
                getDetailWine(wineId);
            else{
                Toast.makeText(WineDetail.this, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        check_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float percentage = Float.parseFloat(wine_discount.getText().toString());
                float dec = percentage / 100;
                float total = Float.parseFloat(wine_price.getText().toString()) - (dec * Float.parseFloat(wine_price.getText().toString()));
                total_price.setText(Float.toString(total));
            }
        });

    }

    private void getDetailWine(String wineId) {
        wines.child(wineId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentWine = dataSnapshot.getValue(Wine.class);

                collapsingToolbarLayout.setTitle(currentWine.getName());
                wine_price.setText(currentWine.getPrice());
                wine_name.setText(currentWine.getName());
                wine_description.setText(currentWine.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
