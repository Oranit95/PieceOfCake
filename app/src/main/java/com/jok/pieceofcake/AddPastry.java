package com.jok.pieceofcake;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPastry extends AppCompatActivity {
    public static final String TAG = "TAG_ADD_PASTRY";
    String userID;
    String priceIn, nameIn, descIn, allergicIn;
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    EditText price, name, description, allergenic;
    DatabaseReference pastryRef;
    FirebaseDatabase DB;
    // CollectionReference docBaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pastry);
        FireLog = FirebaseAuth.getInstance();
        DB = FirebaseDatabase.getInstance();
        // fStore = FirebaseFirestore.getInstance();
        pastryRef = DB.getReference("Menu/" + FireLog.getCurrentUser().getUid());

        //  docBaker = fStore.collection("Bakers")
        // .document(userID).collection("Menu");
        FirebaseUser user = FireLog.getCurrentUser();
        userID = user.getUid();

        retrieve();
    }

    public void addItem(View view) {

        priceIn = price.getText().toString().trim();
        nameIn = name.getText().toString().trim();
        descIn = description.getText().toString().trim();
        allergicIn = allergenic.getText().toString().trim();
        if (TextUtils.isEmpty(priceIn)) {
            price.setError("נא למלא מחיר");
            return;
        }
        if (TextUtils.isEmpty(nameIn)) {
            name.setError("נא למלא שם");
            return;
        }
        if (TextUtils.isEmpty(descIn)) {
            description.setError("נא למלא תיאור");
            return;
        }
        DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError!=null){
                    Toast.makeText(getApplicationContext(), "הוספת מאפה נכשלה",
                            Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "מאפה נוסף בהצלחה!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
        Pastry pastry = new Pastry(Integer.parseInt(priceIn), nameIn, allergicIn, descIn);
        pastry.setDocID(pastryRef.push().getKey());
        pastryRef.child(pastry.getDocID()).setValue(pastry, completionListener);


        /**docBaker.add(pastry).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override public void onSuccess(DocumentReference documentReference) {
        Log.d(TAG, "onSuccess: added pastry " + nameIn);
        }
        }).addOnFailureListener(new OnFailureListener() {
        @Override public void onFailure(@NonNull Exception e) {
        Log.d(TAG, "onFailue" + e.toString());
        }
        });**/
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
