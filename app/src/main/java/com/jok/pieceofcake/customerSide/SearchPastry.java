package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.Customer_Navigation;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.Baker;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SearchPastry extends Customer_Navigation {
    private FirebaseAuth FireLog;// fire base authentication
    ListView listViewBakers;
    String userID;
    DatabaseReference bakersRef;
    FirebaseDatabase DB;
    FirebaseUser currentUser;
    List<Baker>  bakerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pastry);
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        currentUser = FireLog.getCurrentUser();
        userID = currentUser.getUid();
        listViewBakers = findViewById(R.id.bakersListView);

        bakerList = new ArrayList<Baker>();

        bakersRef = DB.getReference("Users").child("Bakers");
    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        bakersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bakerList.clear();
                for (DataSnapshot bakersnapShot : dataSnapshot.getChildren()) {
                    Baker baker = bakersnapShot.getValue(Baker.class);
                    bakerList.add(baker);
                }
                if (bakerList.isEmpty()) {
                    Toast.makeText(SearchPastry.this, "התפריט ריק! הוסף מאפה חדש", Toast.LENGTH_LONG).show();
                   // moveToAddPastry();
                    return;
                }
                BakerAdapter bakerAdapter = new BakerAdapter(SearchPastry.this, bakerList);
                listViewBakers.setAdapter(bakerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listViewBakers.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchPastry.this, CustomerMenuActivity.class);
                intent.putExtra("bakerID", bakerList.get(i).getUserID());
                startActivity(intent);
            }

        });

    }
}
