package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


public class bakerScreen extends AppCompatActivity {
    ArrayAdapter adapter;
    Button myOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baker_screen);
        myOrders = (Button) findViewById(R.id.myOrders);
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToActivitySchedule();
            }
        });


    }
    private void moveToActivitySchedule() {
        Intent intent = new Intent(this, BakerMenuActivity.class);
        startActivity(intent);
    }
}
