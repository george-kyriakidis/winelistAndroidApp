package com.example.winelistapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Interface.ItenClickListener;
import com.example.winelistapp.R;

public class WinelistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public TextView txtWinelistId, txtCustomerName;

    private ItenClickListener itenClickListener;

    public WinelistViewHolder(View itemView) {
        super(itemView);

        txtWinelistId = (TextView) itemView.findViewById(R.id.winelist_id);
        txtCustomerName = (TextView) itemView.findViewById(R.id.customer_name);

        itemView.setOnClickListener(this);
    }

    public void setItenClickListener(ItenClickListener itenClickListener) {
        this.itenClickListener = itenClickListener;
    }

    @Override
    public void onClick(View v) {
        itenClickListener.onClick(v,getAdapterPosition(),false);
    }
}
