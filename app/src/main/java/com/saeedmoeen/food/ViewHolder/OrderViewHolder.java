package com.saeedmoeen.food.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.saeedmoeen.food.Interface.ItemClickListener;
import com.saeedmoeen.food.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_orderId, tv_orderStatus, tv_orderPhone, tv_orderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_orderId = itemView.findViewById(R.id.order_id);
        tv_orderStatus = itemView.findViewById(R.id.order_status);
        tv_orderPhone = itemView.findViewById(R.id.order_phone);
        tv_orderAddress = itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
