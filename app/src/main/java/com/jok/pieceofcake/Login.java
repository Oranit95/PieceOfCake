package com.jok.pieceofcake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
//Test
    String email = "" , password = "";
    boolean baker = false;
    private FirebaseAuth FireLog;
    FirebaseFirestore fStore; //firebase DB
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

    }


    public void Login() {

         password = ((EditText) findViewById(R.id.password)).getText().toString();
         email =  ((EditText) findViewById(R.id.EmailInput)).getText().toString();

        FireLog.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[INFO]", "signInWithEmail:success");
                            UserID = FireLog.getCurrentUser().getUid();
                            DocumentReference docRef = fStore.collection("Users").document(UserID);
                            DocumentSnapshot docSnap = docRef.get().getResult();
                            String role = docSnap.getString("Role");
                            System.out.println("*****************************  " + role + "***************************");

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                            Log.w("[ERROR]", "signInWithEmail:failure", task.getException());

                        }

                    }
                });
    }



    private void BakerLogin( ) {
        Intent intent = new Intent(Login.this, bakerScreen.class);
        startActivity(intent);
    }

    private void CustomerLogin( ) {
        Intent intent = new Intent(Login.this, customerScreen.class);
        startActivity(intent);
    }
    public void onConfirmClick(View v) {
        Login();
    }

}
