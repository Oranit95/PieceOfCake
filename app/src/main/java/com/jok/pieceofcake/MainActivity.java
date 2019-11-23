package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button customer;
    Button baker;
    Typeface font;
    Typeface bakerF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");
        customer = (Button) findViewById(R.id.customer);
        baker = (Button) findViewById(R.id.baker);
        font = Typeface.createFromAsset(this.getAssets(), "fonts/Anka CLM Bold.ttf");
        customer.setTypeface(font);
        baker.setTypeface(font);
        baker.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            moveToActivitySchedule();
                                        }
                                    }
        );

    }

    private void moveToActivitySchedule() {
        Intent intent = new Intent(this, bakerScreen.class);
        startActivity(intent);
    }

}

