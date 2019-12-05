package com.jok.pieceofcake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity {

    private FirebaseAuth FireLog;

    TextView registeTextView;
    Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        font = Typeface.createFromAsset(this.getAssets(), "fonts/Anka CLM Bold.ttf");
//        registeTextView.setTypeface(font);
        FireLog = FirebaseAuth.getInstance();
    }

    public void register (View v){
        EditText password_handler = (EditText) findViewById(R.id.emailInput);
        String password = password_handler.getText().toString();
        EditText email_handler = (EditText) findViewById(R.id.EmailInput);
        String email = email_handler.getText().toString();
        FireLog.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[INFO]", "createUserWithEmail:success");
                            FirebaseUser user = FireLog.getCurrentUser();
                             updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("[INFO]", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void updateUI() {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

}
