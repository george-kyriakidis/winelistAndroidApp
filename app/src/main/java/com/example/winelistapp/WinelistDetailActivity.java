package com.example.winelistapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Common.Common;
import com.example.winelistapp.ViewHolder.WineDetailAdapter;

public class WinelistDetailActivity extends AppCompatActivity {

    TextView customer_name;
    String requestId = "";
    RecyclerView winelistDetail;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winelist_detail);

        customer_name = findViewById(R.id.customer_name);

        winelistDetail = (RecyclerView)findViewById(R.id.recycler_winelist_detail);
        winelistDetail.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        winelistDetail.setLayoutManager(layoutManager);

        if (getIntent()!=null)
            requestId = getIntent().getStringExtra("RequestsId");

        customer_name.setText(Common.currentRequest.getName());

        WineDetailAdapter adapter = new WineDetailAdapter(Common.currentRequest.getWinelists());
        adapter.notifyDataSetChanged();
        winelistDetail.setAdapter(adapter);
    }
}
