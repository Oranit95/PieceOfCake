package com.jok.pieceofcake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BakerMenuActivity extends AppCompatActivity {


    public class MainActivity extends Activity {

        private List<PastryItem> albumList;
        private ListView listView;
        private ListViewAdapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Create album data list
            albumList=new ArrayList<>();
            albumList.add(new PastryItem("עוגת גבינה","120 שקלים"));
            albumList.add(new PastryItem("עוגת שוקולד","120 שקלים"));
            albumList.add(new PastryItem("עוגיות שוקולד צ'יפס","120 שקלים"));
            albumList.add(new PastryItem("עוגת תפוחים","120 שקלים"));
            albumList.add(new PastryItem("עוגת טורט","120 שקלים"));
            albumList.add(new PastryItem("עוגת יום הולדת","20 שקלים"));
            albumList.add(new PastryItem("עוגת תפוזים","40 שקלים"));
            albumList.add(new PastryItem("עוגת גבינה טבעונית","400 שקלים"));
            albumList.add(new PastryItem("עוגת קרמשניט","200 שקלים"));
            albumList.add(new PastryItem("עוגיות שושנים","100 שקלים"));
            albumList.add(new PastryItem("עוגיות תמרים","90 שקלים"));
            albumList.add(new PastryItem("כדורי שוקולד","80 שקלים"));
            //Bind listview
            listView=(ListView)findViewById(R.id.listview);

            //Create adapter and set it to listview.
            adapter=new ListViewAdapter(BakerMenuActivity.this,albumList);
            listView.setAdapter(adapter);

        }

    }
}
