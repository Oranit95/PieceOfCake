package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button customer;
    Button baker;
    Typeface font;
    Typeface bakerF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customer = (Button) findViewById(R.id.customer);
        baker = (Button) findViewById(R.id.baker);
        font = Typeface.createFromAsset(this.getAssets(),"fonts/Anka CLM Bold.ttf");
        customer.setTypeface(font);
        baker.setTypeface(font);

    }
}
