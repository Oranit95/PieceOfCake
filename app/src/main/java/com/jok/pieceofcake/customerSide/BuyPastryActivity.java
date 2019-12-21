package com.jok.pieceofcake.customerSide;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.Order;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.Baker;
import com.jok.pieceofcake.bakerSide.Pastry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BuyPastryActivity extends AppCompatActivity {

    Button Buy;
    EditText date, comment;
    RadioButton cash,card, delivery, pickup;
    String dateS,commentS;
    FirebaseDatabase DB;
    FirebaseAuth auth;
    DatabaseReference orderCRef;
    DatabaseReference orderBRef;
    String userID;
    Baker baker;
    Pastry pastry;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pastry);
        Intent intent = getIntent();

        pastry = (Pastry) intent.getSerializableExtra("Pastry");
        baker = (Baker) intent.getSerializableExtra("Baker");

        Buy = findViewById(R.id.buy);
        date = findViewById(R.id.inputDate);
        comment = findViewById(R.id.commentInput);
        cash = findViewById(R.id.cash);
        card = findViewById(R.id.creditCard);
        delivery = findViewById(R.id.delivery);
        pickup = findViewById(R.id.selfDelivery);
        DB = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        orderBRef = DB.getReference("Orders/Bakers Orders");
        orderCRef = DB.getReference("Orders/Customer Orders");
    }

    public void CreateNewOrderC(View view) {
        //OrderC orderC = new OrderC();
        dateS = date.getText().toString().trim();
        commentS = comment.getText().toString().trim();
        boolean creditCard = false;
        boolean deliveryBool = false;
        if(TextUtils.isEmpty(dateS)){
            date.setError("חובה להזין תאריך!");
        }
        if((!card.isChecked())&&(!cash.isChecked())){
            card.setError("חובה לבחור אמצעי תשלום!");
        }
        if(card.isChecked()){
            creditCard = true;
        }
        if(delivery.isChecked()){
            deliveryBool = true;
        }

        DB.getReference("Users").child("Customers").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customer = dataSnapshot.getValue(Customer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String orderNum = orderBRef.push().getKey();
        Order order = new Order(baker.getUserID(),userID,baker.getEmail()
                ,customer.getEmail(),orderNum,commentS,baker.getAddress(),customer.getAddress()
                ,dateS,pastry.getName(),pastry.getDocID(),creditCard,deliveryBool);
        orderCRef.child(orderNum).setValue(order);
        orderBRef.child(orderNum).setValue(order);
    }
}
