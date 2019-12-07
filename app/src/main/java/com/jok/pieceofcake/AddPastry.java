package com.jok.pieceofcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddPastry extends AppCompatActivity {
    public static final String TAG = "TAG_ADD_PASTRY";
    String userID;
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    EditText price, name, description, allergenic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pastry);
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        retrieve();
    }

    public void addItem(View view) {
        FirebaseUser user = FireLog.getCurrentUser();
        userID = user.getUid();
        String priceIn = price.getText().toString().trim();
        final String nameIn = name.getText().toString().trim();
        String descIn = description.getText().toString().trim();
        String allergicIn = allergenic.getText().toString().trim();

        DocumentReference docBaker = fStore.collection("Bakers")
                .document(userID).collection("Menu").document(nameIn);
        Map<String, Object> pastryDetails = new HashMap<>();
        pastryDetails.put("מחיר", priceIn);
        pastryDetails.put("תיאור", descIn);
        pastryDetails.put("רכיבים אלרגניים", allergicIn);
        docBaker.set(pastryDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: added pastry " + nameIn);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailue" + e.toString());
            }
        });
        backToMenu();
    }

    public void retrieve() {
        price = (EditText) findViewById(R.id.priceInput);
        name = (EditText) findViewById(R.id.nameInput);
        description = (EditText) findViewById(R.id.desInput);
        allergenic = (EditText) findViewById(R.id.alerganicInput);
    }

    public void backToMenu() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

}
