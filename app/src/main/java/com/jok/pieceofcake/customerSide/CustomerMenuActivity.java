package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.ListsAdapters.PastryAdapter;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.Baker;
import com.jok.pieceofcake.bakerSide.Pastry;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerMenuActivity extends AppCompatActivity {

    private FirebaseAuth FireLog;// fire base authentication
    ListView listViewPastriesC;
    String userID;
    DatabaseReference menuForCustomer;
    FirebaseDatabase DB;
    List<Pastry> pastryList;
    Baker baker;
    String bakerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        Intent intent = getIntent();
        bakerID = intent.getExtras().getString("bakerID");

        listViewPastriesC = (ListView) findViewById(R.id.listViewPastriesC);
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        pastryList = new ArrayList<Pastry>();
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        menuForCustomer = DB.getReference("Menu").child(bakerID);
        menuForCustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pastryList.clear();
                for (DataSnapshot pastrySnapShot : dataSnapshot.getChildren()) {
                    Pastry pastry = pastrySnapShot.getValue(Pastry.class);
                    pastryList.add(pastry);
                }

                PastryAdapter pastryAdapter = new PastryAdapter(CustomerMenuActivity.this, pastryList);
                listViewPastriesC.setAdapter(pastryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DB.getReference("Users").child("Bakers").child(bakerID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                baker = dataSnapshot.getValue(Baker.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listViewPastriesC.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CustomerMenuActivity.this, PastryWatchActivityCustomer.class);
                intent.putExtra("Pastry", pastryList.get(i));
                intent.putExtra("Baker",baker);
                startActivity(intent);
            }

        });

    }
}
