package com.jok.pieceofcake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BakerOrderActivity extends AppCompatActivity {

    private FirebaseAuth FireLog;// fire base authentication
    ListView listOrders;
    String userID;
    DatabaseReference databaseOrdersB;
    FirebaseDatabase DB;
    FirebaseUser currentUser;

    List<OrderB> ordersB_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_order);
        listOrders = (ListView)findViewById(R.id.listOrders);


        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        currentUser = FireLog.getCurrentUser();
        userID = currentUser.getUid();
        ordersB_list = new ArrayList<OrderB>();
        databaseOrdersB = DB.getReference("Orders").child(userID);

    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        databaseOrdersB = DB.getReference("Orders").child(userID);
        databaseOrdersB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ordersB_list.clear();
                for (DataSnapshot orderSnapShot : dataSnapshot.getChildren()) {
                    OrderB order = orderSnapShot.getValue(OrderB.class);
                    ordersB_list.add(order);
                }
                if (ordersB_list.isEmpty()) {
                    Toast.makeText(BakerOrderActivity.this, "אין הזמנות בתור", Toast.LENGTH_LONG).show();
                    return;
                }
                OrdersBakerAdapter orderB_Adapter = new OrdersBakerAdapter(BakerOrderActivity.this, ordersB_list);
                listOrders.setAdapter(orderB_Adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


    });

    }
}
