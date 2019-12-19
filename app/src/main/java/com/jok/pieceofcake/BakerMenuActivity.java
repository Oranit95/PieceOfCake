package com.jok.pieceofcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BakerMenuActivity extends AppCompatActivity {
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    private ListView listView;
    String userID;
    DatabaseReference menu;
    FirebaseDatabase DB;
    FirebaseUser currentUser;
    Pastry p;
    //CollectionReference menuRef = fStore.collection("Bakers")
    //.document(userID).collection("Menu");


    //PastryAdapter pastryAdapter;
    //List<Pastry> pastryAdapter;
    ArrayList<Pastry> pastryList;
    ArrayAdapter<Pastry> pastryAdapter;

    //  private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         p = new Pastry();
        setContentView(R.layout.activity_baker_menu);
        listView = (ListView) findViewById(R.id.menu);
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        currentUser = FireLog.getCurrentUser();
        final String userID = currentUser.getUid();
        menu = DB.getReference("Menu").child(userID);
        menu.keepSynced(true);

        pastryList = new ArrayList<>();
        pastryAdapter = new ArrayAdapter<>(this, R.layout.activity_add_pastry,R.id.name);
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot DS : dataSnapshot.getChildren()){
                  p = DS.getValue(Pastry.class);
                  System.out.println("********************"+p);
                  System.out.println("********************"+userID);
                  pastryList.add(p);
              }
                listView.setAdapter(pastryAdapter);
                System.out.println("**************"+listView.getSelectedItem());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //ArrayAdapter<Pastry> arrayAdapter = new ArrayAdapter<Pastry>(this,android.R.layout.simple_list_item_1,pastryList);
        //listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /**
        userID = currentUser.getUid();
        menu = DB.getReference("Menu").child(userID);
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()==false) {
                    Toast.makeText(BakerMenuActivity.this, "התפריט ריק! הוסף מאפה חדש", Toast.LENGTH_LONG).show();
                    moveToAddPastry();
                    return;
                }
                for (DataSnapshot pastrySnapShot : dataSnapshot.getChildren()) {
                    Pastry pastry = pastrySnapShot.getValue(Pastry.class);
                    pastryList.add(pastry);
                   // System.out.println("******************"+userID);
                    System.out.println("&&&&&&&&&&&&&&&"+pastryList.size());
                    pastryAdapter = new PastryAdapter(BakerMenuActivity.this,1, R.layout.list_pastry_item, pastryList);
                    System.out.println("*****************************"+pastryAdapter.isEmpty());
                    if (pastryAdapter == null) {
                        Log.d("TAG", "pastry adapter is null");
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // String[] pastries = {"cake", " cookie"};


        listView.setAdapter(pastryAdapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pastry p = pastryList.get(i);
                Intent intent = new Intent(BakerMenuActivity.this, AddPastry.class);
                intent.putExtra("key", p.docID);
                startActivity(intent);
            }

        });
      **/
    }

    public void moveToAddPastry() {
        Intent intent = new Intent(BakerMenuActivity.this, AddPastry.class);
        startActivity(intent);
    }
}
