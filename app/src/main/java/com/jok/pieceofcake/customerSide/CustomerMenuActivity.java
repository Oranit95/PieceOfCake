package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;

import com.jok.pieceofcake.R;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerMenuActivity extends AppCompatActivity {
    String bakerID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        Intent intent = getIntent();
        bakerID = intent.getExtras().getString("bakerID");
        
    }
}
