package com.example.winelistapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Interface.ItenClickListener;
import com.example.winelistapp.R;

public class WineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView wine_name, wine_desc;

    private ItenClickListener itemClickListener;

    public void setItemClickListener(ItenClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public WineViewHolder(@NonNull View itemView) {
        super(itemView);
        wine_name = (TextView) itemView.findViewById(R.id.wine_item);
        wine_desc = (TextView) itemView.findViewById(R.id.wine_description);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
}
