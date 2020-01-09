package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jok.pieceofcake.bakerSide.bakerScreen;

public class baker_settings extends Baker_Navigation {
    private FirebaseAuth FireLog = FirebaseAuth.getInstance();// fire base authentication
    FirebaseUser user = FireLog.getCurrentUser();
    FirebaseDatabase DB;
    DatabaseReference usersRef;


    EditText oldPassword;
    EditText newPassword;
    EditText city;
    EditText street;
    EditText numOfHouse;
    EditText floor;
    EditText appartment;
    Button confirm;



    String oldPasswordS,newPasswordS,cityS, streetS, numOfHouseS, floorS,appartmentS, confirmS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB = FirebaseDatabase.getInstance();
        usersRef = DB.getReference("Users");

        setContentView(R.layout.activity_baker_settings);
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        numOfHouse = findViewById(R.id.house);
        floor = findViewById(R.id.floor);
        appartment = findViewById(R.id.appartment);
        confirm = (Button)findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPasswordS = oldPassword.getText().toString().trim();
                newPasswordS = newPassword.getText().toString().trim();
                cityS = city.getText().toString().trim();
                streetS = street.getText().toString().trim();
                numOfHouseS = numOfHouse.getText().toString().trim();
                floorS = floor.getText().toString().trim();
                appartmentS = appartment.getText().toString().trim();

                Address address = new Address(cityS, streetS, numOfHouseS, floorS, appartmentS);

                user.updatePassword(newPasswordS);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                usersRef.child(uid).


                Toast.makeText(getApplicationContext(), "הפרטים עודכנו בהצלחה",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), bakerScreen.class));
            }
        });

    }

}
