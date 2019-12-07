package com.jok.pieceofcake;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class BakerMenuActivity extends AppCompatActivity {


    public class MainActivity extends Activity {

        private ListView listView;
      //  private ListViewAdapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Bind listview
            listView=(ListView)findViewById(R.id.menu);

            //Create adapter and set it to listview.
         //   adapter=new ListViewAdapter(BakerMenuActivity.this, pastryList);
          //  listView.setAdapter(adapter);

        }

    }
}
