package com.jok.pieceofcake.customerSideActivities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.ListsAdapters.PastryAdapter;
import com.jok.pieceofcake.Objects.Baker;
import com.jok.pieceofcake.Objects.Pastry;
import com.jok.pieceofcake.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SearchPastryActivity extends AppCompatActivity {
    private FirebaseAuth FireLog;// fire base authentication
    ListView listViewPastries;
    String userID;
    DatabaseReference menuForCustomer;
    FirebaseDatabase DB;
    List<Pastry> pastryList;
    Baker baker;
    String bakerID;
    EditText search_edit_text;
    TextView noResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_pastry);search_edit_text = (EditText) findViewById(R.id.search_edit_text2);
        noResults = findViewById(R.id.noResults2);
        noResults.setVisibility(View.INVISIBLE);
        listViewPastries = (ListView) findViewById(R.id.listViewPastriesC);
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        pastryList = new ArrayList<Pastry>();
        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString().toLowerCase().trim());
                }
                else{
                    cleanFilter();
                }
            }
        });
    }

    private void cleanFilter() {
        userID = FireLog.getCurrentUser().getUid();
        menuForCustomer = DB.getReference("Pastries");
        menuForCustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pastryList.clear();
                for (DataSnapshot pastrySnapShot : dataSnapshot.getChildren()) {
                    Pastry pastry = pastrySnapShot.getValue(Pastry.class);
                    pastryList.add(pastry);
                }

                PastryAdapter pastryAdapter = new PastryAdapter(SearchPastryActivity.this, pastryList);
                listViewPastries.setAdapter(pastryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        userID = FireLog.getCurrentUser().getUid();
        menuForCustomer = DB.getReference("Pastries");
        menuForCustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pastryList.clear();
                for (DataSnapshot pastrySnapShot : dataSnapshot.getChildren()) {
                    Pastry pastry = pastrySnapShot.getValue(Pastry.class);
                    pastryList.add(pastry);
                }

                PastryAdapter pastryAdapter = new PastryAdapter(SearchPastryActivity.this, pastryList);
                listViewPastries.setAdapter(pastryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listViewPastries.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DB.getReference("Users").child("Bakers").child(pastryList.get(i).getBakerID()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        baker = dataSnapshot.getValue(Baker.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent intent = new Intent(SearchPastryActivity.this, PastryWatchActivityCustomer.class);
                intent.putExtra("Pastry", pastryList.get(i));
                intent.putExtra("Baker",baker);
                startActivity(intent);
            }
        });

    }
    public void setAdapter(final String searchedText) {
        menuForCustomer = DB.getReference("Pastries");
        menuForCustomer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 * Clear the list for every new search
                 * */
                pastryList.clear();
                /*
                 * Search all users for matching searched string
                 * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Pastry pastry = snapshot.getValue(Pastry.class);
                    String name = pastry.getName();
                    if (name.toLowerCase().trim().contains(searchedText)) {
                        pastryList.add(pastry);
                    }
                }
                if(pastryList.isEmpty()){
                    noResults.setVisibility(View.VISIBLE);
                }
                PastryAdapter pastryAdapter = new PastryAdapter(SearchPastryActivity.this, pastryList);
                listViewPastries.setAdapter(pastryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
