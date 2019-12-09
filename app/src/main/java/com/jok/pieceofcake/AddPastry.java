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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddPastry extends AppCompatActivity {
    public static final String TAG = "TAG_ADD_PASTRY";
    String userID;
    String priceIn, nameIn, descIn, allergicIn;
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    EditText price, name, description, allergenic;
    CollectionReference docBaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pastry);
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        docBaker = fStore.collection("Bakers")
                .document(userID).collection("Menu");
        FirebaseUser user = FireLog.getCurrentUser();
        userID = user.getUid();

        retrieve();
    }

    public void addItem(View view) {

        priceIn = price.getText().toString().trim();
        nameIn = name.getText().toString().trim();
        descIn = description.getText().toString().trim();
        allergicIn = allergenic.getText().toString().trim();

        Pastry pastry = new Pastry(Integer.parseInt(priceIn), nameIn, allergicIn, descIn);

        docBaker.add(pastry).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
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
        Intent intent = new Intent(AddPastry.this, BakerMenuActivity.class);
        startActivity(intent);
    }

}
