package com.jok.pieceofcake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;
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


        EditText password_handler = (EditText) findViewById(R.id.PasswordInput);
        String password = password_handler.getText().toString();

        EditText email_handler = (EditText) findViewById(R.id.EmailInput);
        String email = email_handler.getText().toString();

        EditText inputPhone = (EditText) findViewById(R.id.inputPhone);
        String Phone = inputPhone.getText().toString();

        EditText inputFullName = (EditText) findViewById(R.id.inputFullName);
        String fullName = inputFullName.getText().toString();

        if(TextUtils.isEmpty(email)){
            email_handler.setError("Email is Required.");
            return;
        }

        if(TextUtils.isEmpty(password)){
            password_handler.setError("Password is Required.");
            return;
        }

        if(password.length() < 6){
            password_handler.setError("Password Must be >= 6 Characters");
            return;
        }

        if(TextUtils.isEmpty(fullName)){
            inputFullName.setError("fullName is Required.");
            return;
        }

        if(TextUtils.isEmpty(Phone)){
            inputPhone.setError("Phone is Required.");
            return;
        }





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
