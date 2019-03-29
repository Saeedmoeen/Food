package com.saeedmoeen.food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saeedmoeen.food.Common.Common;
import com.saeedmoeen.food.Model.Request;
import com.saeedmoeen.food.ViewHolder.OrderViewHolder;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getPhone());


    }

    private void loadOrders(String phone) {

        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.tv_orderId.setText(adapter.getRef(position).getKey());
                viewHolder.tv_orderStatus.setText(convertCodeToStatus(model.getStatus()));
                viewHolder.tv_orderPhone.setText(model.getPhone());
                viewHolder.tv_orderAddress.setText(model.getAddress());
            }
        };
        recyclerView.setAdapter(adapter);

    }

    private String convertCodeToStatus(String status) {
        if (status.equals("0"))
            return "Placed";
        else if (status.equals("1"))
            return "on my way";
        else
            return "Shipped";
    }
}
