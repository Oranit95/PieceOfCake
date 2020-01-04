package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.Upload;
import com.jok.pieceofcake.bakerSide.Baker;
import com.jok.pieceofcake.bakerSide.Pastry;
import com.jok.pieceofcake.bakerSide.PastryImageAdapterBaker;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PastryWatchActivityCustomer extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PastryImageAdapterBaker pastryImageAdapter;
    private DatabaseReference imageRef;
    private DatabaseReference pastryRef;
    private FirebaseStorage storage;
    private List<Upload> uploads;
    private ProgressBar progressBar;
    private ValueEventListener imageRefListener;
    FirebaseDatabase DB;
    private FirebaseAuth FireLog;
    String userID;
    Pastry pastry;
    Baker baker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastry_watch_customer);
        Intent intent = getIntent();
        pastry =(Pastry) intent.getSerializableExtra("Pastry");
        baker =(Baker) intent.getSerializableExtra("Baker");
        recyclerView = findViewById(R.id.pastryPicturesRecycler);
        progressBar = findViewById(R.id.progress_image_baker);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploads = new ArrayList<>();
        pastryImageAdapter = new PastryImageAdapterBaker(PastryWatchActivityCustomer.this, uploads);
        recyclerView.setAdapter(pastryImageAdapter);
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        DB = FirebaseDatabase.getInstance();
        userID = FireLog.getCurrentUser().getUid();
        imageRef = DB.getReference("Menu").child(baker.getUserID()).child(pastry.getDocID()).child("images");
        storage = FirebaseStorage.getInstance();
        pastryRef=DB.getReference("Menu").child(baker.getUserID()).child(pastry.getDocID());
    }
    protected void onStart() {
        super.onStart();
        imageRefListener = imageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uploads.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Upload upload = postSnapshot.getValue(Upload.class);

                    uploads.add(upload);

                }
                pastryImageAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PastryWatchActivityCustomer.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
        public void order(View v) {
            Intent intent = new Intent(PastryWatchActivityCustomer.this, BuyPastryActivity.class);
            intent.putExtra("Pastry",pastry);
            intent.putExtra("Baker",baker);
            startActivity(intent);
        }
}