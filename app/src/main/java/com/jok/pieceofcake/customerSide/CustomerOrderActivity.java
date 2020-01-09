package com.jok.pieceofcake.customerSide;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.Customer_Navigation;
import com.jok.pieceofcake.Order;
import com.jok.pieceofcake.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerOrderActivity extends Customer_Navigation {

    private FirebaseAuth FireLog;// fire base authentication
    ListView ordersListView;
    String userID;
    DatabaseReference databaseOrdersC;
    FirebaseDatabase DB;

    List<Order> ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        ordersListView = (ListView)findViewById(R.id.listOrdersCustomer);

        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();

        ordersList = new ArrayList<Order>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        databaseOrdersC = DB.getReference("Orders/Customers Orders").child(userID);

        databaseOrdersC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ordersList.clear();
                for (DataSnapshot orderSnapShot : dataSnapshot.getChildren()) {
                    Order order = orderSnapShot.getValue(Order.class);
                    ordersList.add(order);
                }
                if (ordersList.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "אין הזמנות בתור", Toast.LENGTH_LONG).show();
                    return;
                }
                OrderAdapter orderC_Adapter = new OrderAdapter(CustomerOrderActivity.this, ordersList);
                ordersListView.setAdapter(orderC_Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
