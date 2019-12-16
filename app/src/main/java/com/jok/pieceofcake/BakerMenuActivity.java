package com.jok.pieceofcake;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BakerMenuActivity extends AppCompatActivity {
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    private ListView listView;
    String userID;

    CollectionReference menuRef = fStore.collection("Bakers")
            .document(userID).collection("Menu");

    ArrayList<Pastry>  pastryList = new ArrayList<Pastry>();
    //  private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_menu);
        listView= findViewById(R.id.menu);

        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        menuRef.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
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
    });
        String[] pastries = {"cake", " cookie"};
        PastryAdapter pastryAdapter = new PastryAdapter(this,R.layout.list_pastry_item, pastries);
        if (pastryAdapter==null){
            Log.d("TAG","pastry adapter is null");
        }
        //Bind listview

        //Create adapter and set it to listview.
        //   adapter=new ListViewAdapter(BakerMenuActivity.this, pastryList);
        listView.setAdapter(pastryAdapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }

        });


    }

    public void moveToAddPastry() {
    }
}
