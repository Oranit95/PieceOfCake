package com.jok.pieceofcake;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class BakerMenu extends AppCompatActivity {
    String userID;
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baker_menu2);
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
    }

    public void addItem(View view) {
        FirebaseUser user = FireLog.getCurrentUser();
        userID = user.getUid();
        DocumentReference docBaker = fStore.collection("Bakers").document(userID).collection("Menu")
                .document("pastry name - **need to change***");
        Map<String, Object> pastryDetails = new HashMap<>();
        pastryDetails.put("Full Name","" );
    }
}
