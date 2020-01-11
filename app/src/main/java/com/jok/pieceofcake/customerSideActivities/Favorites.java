package com.jok.pieceofcake.customerSideActivities;

import android.os.Bundle;

import com.jok.pieceofcake.Navigation.Customer_Navigation;
import com.jok.pieceofcake.R;

public class Favorites extends Customer_Navigation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
    }
}
