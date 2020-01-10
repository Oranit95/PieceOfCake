package com.jok.pieceofcake.bakerSide;

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
import com.jok.pieceofcake.Navigation.Baker_Navigation;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.ListsAdapters.PastryAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class BakerMenuActivity extends Baker_Navigation {
    private FirebaseAuth FireLog;// fire base authentication
    ListView listViewPastries;
    String userID;
    DatabaseReference menu;
    FirebaseDatabase DB;

    List<Pastry> pastryList;

    //  private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_menu);
        listViewPastries = (ListView) findViewById(R.id.menu);

        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();

        pastryList = new ArrayList<Pastry>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        menu = DB.getReference("Menu").child(userID);
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pastryList.clear();

                for (DataSnapshot pastrySnapShot : dataSnapshot.getChildren()) {
                    Pastry pastry = pastrySnapShot.getValue(Pastry.class);
                    pastryList.add(pastry);
                }
                if (pastryList.isEmpty()) {
                    Toast.makeText(BakerMenuActivity.this, "התפריט ריק! הוסף מאפה חדש", Toast.LENGTH_LONG).show();
                    moveToAddPastry();
                    return;
                }
                PastryAdapter pastryAdapter = new PastryAdapter(BakerMenuActivity.this, pastryList);
                listViewPastries.setAdapter(pastryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listViewPastries.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BakerMenuActivity.this, PopUpPastryBaker.class);
                intent.putExtra("Pastry", pastryList.get(i));
                startActivity(intent);
            }
        });

    }
    public void moveToAddPastry() {
        Intent intent = new Intent(BakerMenuActivity.this, AddPastry.class);
        startActivity(intent);
    }

    public void addNewPastry(View view) {
        moveToAddPastry();
    }
}