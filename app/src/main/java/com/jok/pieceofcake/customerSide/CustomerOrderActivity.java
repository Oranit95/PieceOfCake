package com.jok.pieceofcake.customerSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderActivity extends AppCompatActivity {

    private FirebaseAuth FireLog;// fire base authentication
    ListView listOrders;
    String userID;
    DatabaseReference databaseOrdersC;
    FirebaseDatabase DB;

    List<OrderC> ordersC_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        listOrders = (ListView)findViewById(R.id.listOrdersCustomer);

        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();

        ordersC_list = new ArrayList<OrderC>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        databaseOrdersC = DB.getReference("Customer Orders").child(userID);

        databaseOrdersC.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ordersC_list.clear();
                for (DataSnapshot orderSnapShot : dataSnapshot.getChildren()) {
                    OrderC order = orderSnapShot.getValue(OrderC.class);
                    ordersC_list.add(order);
                }
                if (ordersC_list.isEmpty()) {
                    Toast.makeText(CustomerOrderActivity.this, "אין הזמנות בתור", Toast.LENGTH_LONG).show();
                    return;
                }
                OrderCustomerAdpter orderC_Adapter = new OrderCustomerAdpter(CustomerOrderActivity.this, ordersC_list);
                listOrders.setAdapter(orderC_Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
