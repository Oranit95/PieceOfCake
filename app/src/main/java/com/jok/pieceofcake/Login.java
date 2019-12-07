package com.jok.pieceofcake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
//Test
    String email = "" , password = "";
    private FirebaseAuth FireLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FireLog = FirebaseAuth.getInstance();


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
                            FirebaseUser user = FireLog.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                            Log.w("[ERROR]", "signInWithEmail:failure", task.getException());

                        }
                    }
                });
    }





    private void updateUI( ) {
        Intent intent = new Intent(getApplicationContext(), customerScreen.class);
        startActivity(intent);
    }
    public void onConfirmClick(View v) {
        Login();
    }

}
