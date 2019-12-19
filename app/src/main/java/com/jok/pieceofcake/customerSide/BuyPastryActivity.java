package com.jok.pieceofcake.customerSide;

import android.os.Bundle;
import android.view.View;

import com.jok.pieceofcake.R;

import androidx.appcompat.app.AppCompatActivity;

public class BuyPastryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pastry);
/**
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.8));
**/
    }

    public void sendOrderToBaker(View view) {

    }
}
