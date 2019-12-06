package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button newUser;
    Button ExsitCustomer;
    TextView headline;
    TextView welcome;
    Typeface font;
    Typeface bakerF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "firebase conection success",Toast.LENGTH_LONG).show();
        newUser = (Button) findViewById(R.id.Customer);
        ExsitCustomer = (Button) findViewById(R.id.Baker);
        headline = (TextView) findViewById(R.id.PieceOfCake);
        welcome = (TextView) findViewById(R.id.Hello);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/Anka CLM Bold.ttf");
        newUser.setTypeface(font);
        ExsitCustomer.setTypeface(font);
        headline.setTypeface(font);
        welcome.setTypeface(font);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
           }
        });
        ExsitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });



    }
}
