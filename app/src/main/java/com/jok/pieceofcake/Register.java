package com.jok.pieceofcake;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    Typeface font; // font
    EditText password_handler, email_handler, inputPhone, inputFullName;//input bars
    TextView pass, mail, phone, name;//names near input bars
    Button confirm;
    String password, email, Phone, fullName;
    String userID;
    CheckBox inputBaker, inputCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/Anka CLM Bold.ttf");
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        retrieve();//retrieve all elements in the activity
        setFonts();


    }

    public void register(View v) {

        //saving context of user details
        password = password_handler.getText().toString().trim();
        email = email_handler.getText().toString().trim();
        Phone = inputPhone.getText().toString().trim();
        fullName = inputFullName.getText().toString().trim();

        //check that all the inputs are valid
        if (TextUtils.isEmpty(email)) {
            email_handler.setError("נא למלא E-mail");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            password_handler.setError("נא למלא סיסמא.");
            return;
        }

        if (password.length() < 6) {
            password_handler.setError("סיסמא חייבת להיות עם 6 תווים לפחות.");
            return;
        }

        if (TextUtils.isEmpty(fullName)) {
            inputFullName.setError("זהו שדה חובה.");
            return;
        }

        if (TextUtils.isEmpty(Phone)) {
            inputPhone.setError("יש למלא מספר טלפון");
            return;
        }


        if ((!inputBaker.isChecked()) && (!inputCustomer.isChecked())) {
            inputBaker.setError("יש לבחור אופה/לקוח");
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
                            userID = user.getUid();
                            DocumentReference docBaker;
                            DocumentReference docCustomer;
                            Map<String, Object> userDetails = new HashMap<>();
                            userDetails.put("Full Name",fullName );
                            userDetails.put("Email",email );
                            userDetails.put("Phone",Phone );
                            //  userDetails.put("Password", password );
                            if(inputBaker.isChecked()){
                                docBaker = fStore.collection("Bakers").document(userID);
                                docBaker.set(userDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user profile is create for "+ userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailue" + e.toString());
                                    }
                                });
                            }
                            else {
                                docCustomer = fStore.collection("Customers").document(userID);

                                docCustomer.set(userDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "onSuccess: user profile is create for " + userID);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailue" + e.toString());
                                    }
                                });
                            }
                            moveToLogin();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("[INFO]", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    /**
     * retrieve all elements in the activity
     */
    public void retrieve(){
        //retrieving details EditText input bars
        password_handler = (EditText) findViewById(R.id.PasswordInput);
        email_handler = (EditText) findViewById(R.id.EmailInput);
        inputPhone = (EditText) findViewById(R.id.inputPhone);
        inputFullName = (EditText) findViewById(R.id.inputFullName);

        //retrieving the names near the input bars
        pass = (TextView ) findViewById(R.id.Password);
        mail = (TextView ) findViewById(R.id.Email);
        phone = (TextView ) findViewById(R.id.Phone);
        name= (TextView ) findViewById(R.id.fullName);

        //confirm button
        confirm = (Button)findViewById(R.id.confirm);

        //checkboxes of baker and customer
        inputBaker = (CheckBox) findViewById(R.id.ifBaker);
        inputCustomer = (CheckBox) findViewById(R.id.ifCustomer);

    }
    public void setFonts(){
        pass.setTypeface(font);
        mail.setTypeface(font);
        phone.setTypeface(font);
        pass.setTypeface(font);
        name.setTypeface(font);
        inputBaker.setTypeface(font);
        inputCustomer.setTypeface(font);
        confirm.setTypeface(font);
    }

    private void moveToLogin() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }

}
