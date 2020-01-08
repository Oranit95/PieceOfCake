package com.jok.pieceofcake.bakerSide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jok.pieceofcake.Baker_Navigation;
import com.jok.pieceofcake.Login;
import com.jok.pieceofcake.R;


public class bakerScreen extends Baker_Navigation {
   // private DrawerLayout drawer;
    ArrayAdapter adapter;
    Button myMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.baker_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
        drawer = findViewById(R.id.drawer_layout);
       NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
              R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.addDrawerListener(toggle);
        toggle.syncState();
**/
        myMenu = (Button) findViewById(R.id.myMenu);
        myMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToBakerMenu();
            }
        });


    }
/**
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       return super.onNavigationItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
**/
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
