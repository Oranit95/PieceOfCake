package com.jok.pieceofcake.bakerSide;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.jok.pieceofcake.Login;
import com.jok.pieceofcake.R;


public class bakerScreen extends AppCompatActivity {
    private DrawerLayout drawer;
    ArrayAdapter adapter;
    Button myMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baker_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        myMenu = (Button) findViewById(R.id.myMenu);
        myMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToBakerMenu();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
        super.onBackPressed();
    }
    }

    private void moveToBakerMenu() {
        Intent intent = new Intent(this, BakerMenuActivity.class);
        startActivity(intent);
    }

    public void moveToBakerOrders(View v){
        startActivity(new Intent(getApplicationContext(), BakerOrderActivity.class));
    }
    public void LogOutB (View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
