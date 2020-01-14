package com.jok.pieceofcake.customerSideActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.Objects.Baker;
import com.jok.pieceofcake.Objects.Customer;
import com.jok.pieceofcake.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FavoriteBakerActivity extends AppCompatActivity {

    public Baker baker;
    private FirebaseAuth FireLog;// fire base authentication
    String userID;
    DatabaseReference customerRef;
    FirebaseDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_baker);
        Intent intent = getIntent();
        baker = (Baker) intent.getSerializableExtra("baker");
        DB = FirebaseDatabase.getInstance();
        FireLog = FirebaseAuth.getInstance();
        userID = FireLog.getCurrentUser().getUid();
        TextView bakername = (TextView)findViewById(R.id.baker_name);
        bakername.setText("אופה: "+baker.getFull_name());
    }

    public void deleteFromFavorites(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("מחיקת אופה מהמועדפים");
        builder.setMessage("האם אתה בטוח שברצונך למחוק אופה זה?");
        builder.setPositiveButton("מחק",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       customerRef = DB.getReference("Users/Customers").child(userID);
                       customerRef.addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                               Customer me = dataSnapshot.getValue(Customer.class);
                               for (int i = 0; i <me.getFavorites().size() ; i++) {
                                   if(me.getFavorites().get(i).getUserID().equals(baker.getUserID())){
                                       me.removeFromFavorites(i);
                                       customerRef.setValue(me);
                                       startActivity(new Intent(FavoriteBakerActivity.this,Favorites.class));
                                   }
                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError databaseError) {

                           }
                       });
                    }
                });
        builder.setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void moveToMenu(View view) {
        Intent intent = new Intent(FavoriteBakerActivity.this, CustomerMenuActivity.class);
        intent.putExtra("baker", baker);
        startActivity(intent);
    }
}
