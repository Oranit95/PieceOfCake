package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.jok.pieceofcake.Login;
import com.jok.pieceofcake.R;

import androidx.appcompat.app.AppCompatActivity;

public class customerScreen extends AppCompatActivity {
    Button myOrders;
    Button newOrder;
    Button myFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_screen);

        myFavorites = findViewById(R.id.myFavorites);
        myOrders = findViewById(R.id.myOrders);
        newOrder =findViewById(R.id.newOrder);

    }

    public void LogOutC (View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    public void moveToSearch(View view) {
        Intent intent = new Intent(customerScreen.this, SearchPastry.class);
        startActivity(intent);
    }

    public void moveToCustomerOrders(View view) {
        Intent intent = new Intent(this, CustomerOrderActivity.class);
        startActivity(intent);
    }

    public void moveToFavorites(View view) {
        Intent intent = new Intent(this, Favorites.class);
        startActivity(intent);
    }
}
