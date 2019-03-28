package com.saeedmoeen.food.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.saeedmoeen.food.Interface.ItemClickListener;
import com.saeedmoeen.food.Model.Order;
import com.saeedmoeen.food.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tv_cart_name, tv_price;
    public ImageView img_cart_count;

    private ItemClickListener itemClickListener;

    public void setTv_cart_name(TextView tv_cart_name) {
        this.tv_cart_name = tv_cart_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_cart_name  = (TextView) itemView.findViewById(R.id.cart_item_name);
        tv_price = (TextView) itemView.findViewById(R.id.cart_item_price);
        img_cart_count = (ImageView) itemView.findViewById(R.id.cart_item_count);
    }

    @Override
    public void onClick(View v) {

    }
}

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<Order> listData = new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_layout, viewGroup, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int i) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + listData.get(i).getQuantity(), Color.RED);
        holder.img_cart_count.setImageDrawable(drawable);

        Locale locale = new Locale("fr", "IR");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(i).getPrice())) * (Integer.parseInt(listData.get(i).getQuantity()));
        holder.tv_price.setText(fmt.format(price));
        holder.tv_cart_name.setText(listData.get(i).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
