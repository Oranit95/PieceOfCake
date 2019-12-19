package com.jok.pieceofcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

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
    //CollectionReference menuRef = fStore.collection("Bakers")
    //.document(userID).collection("Menu");

    ArrayList<Pastry> pastryList = new ArrayList<Pastry>();

    //  private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_menu);
        listView = findViewById(R.id.menu);
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        currentUser = FireLog.getCurrentUser();
        final String userID = currentUser.getUid();
        //fStore = FirebaseFirestore.getInstance();
        menu = DB.getReference("Menus").child(userID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = FireLog.getCurrentUser();
        userID = currentUser.getUid();
        menu = DB.getReference("Menu").child(userID);
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("*************"+userID);
                for (DataSnapshot pastrySnapShot : dataSnapshot.getChildren()) {
                    Pastry pastry = pastrySnapShot.getValue(Pastry.class);
                    pastryList.add(pastry);
                }
                if (dataSnapshot.hasChildren()==false) {
                    Toast.makeText(BakerMenuActivity.this, "התפריט ריק! הוסף מאפה חדש", Toast.LENGTH_LONG).show();
                    moveToAddPastry();
                    //return;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /** menuRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
        @Override public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e!=null){
        return;
        }
        for (QueryDocumentSnapshot querySnap : queryDocumentSnapshots) {
        Pastry pastry = querySnap.toObject(Pastry.class);
        pastryList.add(pastry);
        }
        if (pastryList.size() == 0) {
        moveToAddPastry();
        }
        }
        });**/
        // String[] pastries = {"cake", " cookie"};
        PastryAdapter pastryAdapter = new PastryAdapter(this,1, R.layout.list_pastry_item, pastryList);
        if (pastryAdapter == null) {
            Log.d("TAG", "pastry adapter is null");
        }
        //Bind listview

        //Create adapter and set it to listview.
        //   adapter=new ListViewAdapter(BakerMenuActivity.this, pastryList);
        listView.setAdapter(pastryAdapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }

        });


    }

    public void moveToAddPastry() {
        Intent intent = new Intent(BakerMenuActivity.this, AddPastry.class);
        startActivity(intent);
    }
}