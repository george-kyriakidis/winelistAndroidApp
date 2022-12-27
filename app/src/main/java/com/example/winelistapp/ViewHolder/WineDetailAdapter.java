package com.example.winelistapp.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Model.Winelist;
import com.example.winelistapp.R;

import java.util.List;

class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView name, total;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.wine_name);
        total = (TextView) itemView.findViewById(R.id.wine_total_price);

    }
}

public class WineDetailAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Winelist> myWinelists;

    public WineDetailAdapter(List<Winelist> myWinelists) {
        this.myWinelists = myWinelists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.winelist_detail_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Winelist winelist = myWinelists.get(position);
        holder.name.setText(String.format(winelist.getWineLabel()));
        holder.total.setText(String.format(winelist.getTotal()));
    }

    @Override
    public int getItemCount() {
        return myWinelists.size();
    }
}
