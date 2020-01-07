package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.jok.pieceofcake.Login;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.BakerMainMenuFragment;
import com.jok.pieceofcake.bakerSide.BakerMenuActivity;
import com.jok.pieceofcake.bakerSide.BakerOrderActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class customerScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button myOrders;
    Button newOrder;
    Button myFavorites;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_screen);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout2);
        NavigationView navigationView = findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        myFavorites = findViewById(R.id.myFavorites);
        myOrders = findViewById(R.id.myOrders);
        newOrder =findViewById(R.id.newOrder);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.customer_orders:
                Intent i = new Intent(this, CustomerOrderActivity.class);
                startActivity(i);
                break;

            case R.id.buy_pastry:
                Intent j = new Intent(this, SearchPastry.class);
                startActivity(j);
                break;

            case R.id.main_menu_customer:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container2,
                        new CustomerMainMenuFragment()).commit();
                break;

            case R.id.customer_favorites:
                Intent k = new Intent(this, Favorites.class);
                startActivity(k);
                break;

            case R.id.log_out_customer:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
