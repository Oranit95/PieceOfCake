package com.jok.pieceofcake.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSideActivities.bakerScreenActivity;
import com.jok.pieceofcake.customerSideActivities.customerScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Login for an existing user - both customer and baker
 */
public class Login extends AppCompatActivity {
    //Test
    String email = "", password = "";
    ProgressBar progressBar;
    boolean isBaker = false;
    private FirebaseAuth FireLog;
    String userID;
    DatabaseReference bakersRef;
    DatabaseReference customersRef;
    DatabaseReference usersRef;
    FirebaseDatabase DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FireLog = FirebaseAuth.getInstance();
        DB = FirebaseDatabase.getInstance();
        progressBar = findViewById(R.id.progressBar);
        usersRef = DB.getReference("Users");
        bakersRef = DB.getReference("Users/Bakers");
        customersRef = DB.getReference("Users/Customers");
    }


    public void Login() {

        password = ((EditText) findViewById(R.id.password)).getText().toString();
        email = ((EditText) findViewById(R.id.EmailInput)).getText().toString();
        FireLog.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.VISIBLE);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[INFO]", "signInWithEmail:success");

                            FirebaseUser user = FireLog.getCurrentUser();

                            userID = user.getUid();
                            //  System.out.println("************************************   "+ userID);
                            if (user != null) {
                                usersRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.child("Bakers").hasChild(userID)) {
                                            BakerLogin();

                                        } else if (dataSnapshot.child("Customers").hasChild(userID)) {
                                            CustomerLogin();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }


                                });
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                            Log.w("[ERROR]", "signInWithEmail:failure", task.getException());

                        }

                    }
                });
    }

    private void BakerLogin() {
        Intent intent = new Intent(Login.this, bakerScreenActivity.class);
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
