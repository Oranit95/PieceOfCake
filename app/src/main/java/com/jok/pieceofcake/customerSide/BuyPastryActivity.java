package com.jok.pieceofcake.customerSide;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jok.pieceofcake.Objects.Order;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.Baker;
import com.jok.pieceofcake.bakerSide.Pastry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

public class BuyPastryActivity extends AppCompatActivity {

    Button Buy, date;
    EditText comment;
    RadioButton cash,card, delivery, pickup;
    String dateS,commentS;
    FirebaseDatabase DB;
    FirebaseAuth auth;
    DatabaseReference orderCRef;
    DatabaseReference orderBRef;
    DatabaseReference customerRef;
    String userID;
    Baker baker;
    Pastry pastry;
    Customer customer;
    Order order;
    String orderNum;
    boolean creditCard;
    boolean deliveryBool;
    int year,month,day;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener mDateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pastry);
        calendar = Calendar.getInstance();
        Intent intent = getIntent();
        pastry = (Pastry) intent.getSerializableExtra("Pastry");
        baker = (Baker) intent.getSerializableExtra("Baker");
        pastry.getName();
        Buy = findViewById(R.id.buy);
        date = (Button) findViewById(R.id.inputDate);
        comment = findViewById(R.id.commentInput);
        cash = findViewById(R.id.cash);
        card = findViewById(R.id.creditCard);
        delivery = findViewById(R.id.delivery);
        pickup = findViewById(R.id.selfDelivery);
        creditCard = false;
        deliveryBool = false;
        DB = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        orderBRef = DB.getReference("Orders/Bakers Orders");
        orderCRef = DB.getReference("Orders/Customers Orders");
        customerRef = DB.getReference("Users").child("Customers").child(userID);

        date.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog =  new DatePickerDialog(
                        BuyPastryActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
                dialog.show();
            }
        } );

        mDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker ,int Year ,int Month ,int Day) {
                day = Day;
                month = Month + 1;
                year = Year;

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        customerRef = DB.getReference("Users/Customers");
        customerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userID = auth.getCurrentUser().getUid();
                if(dataSnapshot.hasChild(userID)) {
                    customer = dataSnapshot.child(userID).getValue(Customer.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void CreateNewOrderC(View view) {
        findViewById(R.id.buy).setEnabled(false);
        dateS = (day + "/" + month + "/" + year);
        commentS = comment.getText().toString().trim();
        if(dateS.isEmpty()){
            date.setError("חובה להזין תאריך!");
            return;
        }
        if((!card.isChecked())&&(!cash.isChecked())){
            card.setError("חובה לבחור אמצעי תשלום!");
            return;

        }
        if(card.isChecked()){
            creditCard = true;

        }
        if(delivery.isChecked()){
            deliveryBool = true;
        }
        DatabaseReference.CompletionListener completionListener = new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError!=null){
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }
            }
        };
        orderNum = orderBRef.push().getKey();
         order = new Order(customer,baker,pastry,
                 dateS,commentS,creditCard,deliveryBool);
        orderCRef.child(customer.getUserID()).child(orderNum).setValue(order,completionListener);
        orderBRef.child(baker.getUserID()).child(orderNum).setValue(order,completionListener);
/**
if (creditCard) {
    System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
         String url = "http://www.google.com";
         Intent i = new Intent(Intent.ACTION_VIEW);
         i.setData(Uri.parse(url));
         startActivity(i);

}
**/
        Toast.makeText(BuyPastryActivity.this, "הזמנה נשלחה בהצלחה!", Toast.LENGTH_LONG).show();
        findViewById(R.id.buy).setEnabled(true);
        startActivity(new Intent(BuyPastryActivity.this,CustomerOrderActivity.class));
    }

}
