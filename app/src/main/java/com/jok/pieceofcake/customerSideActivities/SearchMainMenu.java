package com.jok.pieceofcake.customerSideActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.ListsAdapters.OrderAdapterCustomer;
import com.jok.pieceofcake.Navigation.Customer_Navigation;
import com.jok.pieceofcake.Objects.Order;
import com.jok.pieceofcake.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class SearchMainMenu extends Customer_Navigation {
    private FirebaseAuth FireLog;// fire base authentication
    ListView ordersListView;
    String userID;
    DatabaseReference databaseOrdersC;
    FirebaseDatabase DB;
    List<Order> ordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main_menu);
        ordersListView = (ListView)findViewById(R.id.my_last_orders_list_view);

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
                    Toast.makeText(SearchMainMenu.this, "אין הזמנות בתור", Toast.LENGTH_LONG).show();
                    return;
                }
                OrderAdapterCustomer orderC_Adapter = new OrderAdapterCustomer(SearchMainMenu.this, ordersList);
                ordersListView.setAdapter(orderC_Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ordersListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchMainMenu.this, PastryWatchActivityCustomer.class);
                intent.putExtra("Pastry", ordersList.get(i).getPastry());
                intent.putExtra("Baker",ordersList.get(i).getBaker());
                startActivity(intent);
            }

        });
    }

    public void SearchBaker(View view) {
        startActivity(new Intent(SearchMainMenu.this,SearchBakerActivity.class));
    }

    public void SearchPastry(View view) {
        startActivity(new Intent(SearchMainMenu.this,SearchPastryActivity.class));
    }
}
