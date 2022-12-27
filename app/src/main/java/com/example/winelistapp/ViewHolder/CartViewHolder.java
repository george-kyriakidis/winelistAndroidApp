package com.example.winelistapp.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winelistapp.Interface.ItenClickListener;
import com.example.winelistapp.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

    public TextView txt_name, txt_price,txt_total_price, txt_discount;

    public ImageView image_cart_count;

    public RelativeLayout view_background;

    public LinearLayout view_foreground;

    private ItenClickListener itenClickListener;

    public void setTxt_name(TextView txt_name) {
        this.txt_name = txt_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_name = (TextView) itemView.findViewById(R.id.cart_item_name);
        txt_price = (TextView) itemView.findViewById(R.id.cart_item_price);
        txt_discount = (TextView) itemView.findViewById(R.id.cart_item_discount);
        txt_total_price = (TextView) itemView.findViewById(R.id.cart_item_total_price);
        image_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);
        view_background = (RelativeLayout)itemView.findViewById(R.id.view_background);
        view_foreground = (LinearLayout)itemView.findViewById(R.id.view_foreground);

        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }
}
