package com.jok.pieceofcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    //Test
    String email = "", password = "";
    boolean isBaker = false;
    private FirebaseAuth FireLog;
    FirebaseFirestore fStore; //firebase DB
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

    }


    public void Login() {

        password = ((EditText) findViewById(R.id.password)).getText().toString();
        email = ((EditText) findViewById(R.id.EmailInput)).getText().toString();

        FireLog.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[INFO]", "signInWithEmail:success");

                            FirebaseUser user = FireLog.getCurrentUser();
                            userID = user.getUid();
                            DocumentReference docBaker = fStore.collection("Bakers").document(userID);
                            docBaker.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        isBaker = true;
                                        BakerLogin();
                                    } else {
                                        customerDoc();
                                        CustomerLogin();
                                    }

                                }
                            });



                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                            Log.w("[ERROR]", "signInWithEmail:failure", task.getException());

                        }

                    }
                });
    }

    public void customerDoc() {
        DocumentReference docCustomer = fStore.collection("Customers").document(userID);
        docCustomer.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    isBaker = false;
                } else {
                    Toast.makeText(Login.this, "This user is not exist",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void BakerLogin() {
        Intent intent = new Intent(Login.this, bakerScreen.class);
        startActivity(intent);
    }

    private void CustomerLogin() {
        Intent intent = new Intent(Login.this, customerScreen.class);
        startActivity(intent);
    }

    public void onConfirmClick(View v) {
        Login();
    }

}
