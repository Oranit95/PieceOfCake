package com.jok.pieceofcake.customerSide;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.jok.pieceofcake.R;

import androidx.appcompat.app.AppCompatActivity;

public class BuyPastryPopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pastry_pop_up);
/**
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*.8), (int)(dm.heightPixels*.8));
**/
    }

    public void sendOrderToBaker(View view) {

    }
}
