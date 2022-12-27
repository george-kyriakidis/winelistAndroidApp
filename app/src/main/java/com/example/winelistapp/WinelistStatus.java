package com.example.winelistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Common.Common;
import com.example.winelistapp.Interface.ItenClickListener;
import com.example.winelistapp.Model.Request;
import com.example.winelistapp.ViewHolder.WinelistViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WinelistStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    FirebaseRecyclerAdapter<Request, WinelistViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winelist_status);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = (RecyclerView) findViewById(R.id.list_winelists);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadWinelists(Common.currentUser.getPhone());
    }

    private void loadWinelists(final String phone) {
        adapter = new FirebaseRecyclerAdapter<Request, WinelistViewHolder>(
                Request.class,
                R.layout.winelist_layout,
                WinelistViewHolder.class,
                requests.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(WinelistViewHolder winelistViewHolder, final Request request, int i) {
                winelistViewHolder.txtWinelistId.setText(adapter.getRef(i).getKey());
                winelistViewHolder.txtCustomerName.setText(request.getName());
                winelistViewHolder.setItenClickListener(new ItenClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent winelistDetails = new Intent(WinelistStatus.this, WinelistDetailActivity.class);
                        Common.currentRequest = request;
                        winelistDetails.putExtra("RequestsId", adapter.getRef(position).getKey());
                        startActivity(winelistDetails);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
