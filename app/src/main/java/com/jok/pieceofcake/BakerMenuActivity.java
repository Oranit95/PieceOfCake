package com.jok.pieceofcake;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

public class BakerMenuActivity extends AppCompatActivity {
    private FirebaseAuth FireLog;// fire base authentication
    FirebaseFirestore fStore; //firebase DB
    private ListView listView;
    String userID;

    //  private ListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FireLog = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        Pastry cake = new Pastry(50,"cake","eggs, nuts", "chocloate cake with nuts");
        Pastry[] pastries = new Pastry[] {cake};

        PastryAdapter pastryAdapter = new PastryAdapter(this,R.layout.list_pastry_item, pastries);
        //Bind listview
        listView = (ListView) findViewById(R.id.menu);

        //Create adapter and set it to listview.
        //   adapter=new ListViewAdapter(BakerMenuActivity.this, pastryList);
          listView.setAdapter(pastryAdapter);
          listView.setOnItemClickListener(new ListView.OnItemClickListener(){
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              }

          });


    }

}
