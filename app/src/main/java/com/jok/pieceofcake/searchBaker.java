package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class searchBaker extends AppCompatActivity {
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_baker);
        ListView list = (ListView) findViewById(R.id.searchList);
        EditText filter = (EditText) findViewById(R.id.Filter);
        ArrayList<String> test = new ArrayList<String>();
        test.add("אריאל");
        test.add("דניאל");
        test.add("אוראל");
        test.add("אלה");
        test.add("קארין");
        test.add("ירדן");
        test.add("ספיר");
         adapter = new ArrayAdapter(this, R.layout.activity_main, test);
        list.setAdapter(adapter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (searchBaker.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
