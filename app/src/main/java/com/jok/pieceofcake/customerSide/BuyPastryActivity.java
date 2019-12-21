package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jok.pieceofcake.R;

import androidx.appcompat.app.AppCompatActivity;

public class BuyPastryActivity extends AppCompatActivity {

    Button Buy;
    EditText date;
    CheckBox pay;
    CheckBox recievedOrder;
    EditText comment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pastry);
         Buy = findViewById(R.id.buy);

/**
 DisplayMetrics dm = new DisplayMetrics();
 getWindowManager().getDefaultDisplay().getMetrics(dm);

 getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.8));
 **/
    }
    public void sendOrderToBaker(View view) {

    }

    public void CreateNewOrderC(View view) {
        //OrderC orderC = new OrderC();
    }
}
