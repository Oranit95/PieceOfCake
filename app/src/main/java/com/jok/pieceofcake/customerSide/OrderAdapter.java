package com.jok.pieceofcake.customerSide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jok.pieceofcake.Order;
import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.Pastry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Activity context;
    private List<Order> ordersList;

    public OrderAdapter(Activity context, List<Order> ordersList) {
        super(context, R.layout.order_item, ordersList);
        this.context = context;
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.order_item, null, true);
        TextView B_phone = listViewItem.findViewById(R.id.bakerPhone);
        TextView C_phone = listViewItem.findViewById(R.id.customerPhone);
        TextView B_name = listViewItem.findViewById(R.id.bakerName);
        TextView C_name = listViewItem.findViewById(R.id.customerName);
        TextView B_email = listViewItem.findViewById(R.id.bakerEmail);
        TextView C_email = listViewItem.findViewById(R.id.customerEmail);
        TextView B_City = listViewItem.findViewById(R.id.bakerCity);
        TextView C_City = listViewItem.findViewById(R.id.customerCity);
        TextView B_Street = listViewItem.findViewById(R.id.bakerStreet);
        TextView C_Street = listViewItem.findViewById(R.id.customerStreet);
        TextView B_House = listViewItem.findViewById(R.id.bakerNumHouse);
        TextView C_House = listViewItem.findViewById(R.id.customerNumHouse);
        TextView B_floor = listViewItem.findViewById(R.id.bakerFloor);
        TextView C_floor = listViewItem.findViewById(R.id.customerFloor);
        TextView B_appartment = listViewItem.findViewById(R.id.bakerAppartment);
        TextView C_appartment = listViewItem.findViewById(R.id.customerAppartment);
        TextView date = listViewItem.findViewById(R.id.dateOrder);
        TextView pay = listViewItem.findViewById(R.id.PayOrder);
        TextView recievedOrder = listViewItem.findViewById(R.id.recievedOrder);
        TextView namePastry = listViewItem.findViewById(R.id.namePastry);
        TextView comments = listViewItem.findViewById(R.id.Comment);

        Order order = ordersList.get(position);
        Pastry pastry = ordersList.get(position).getPastry();
        B_email.setText("מייל האופה: " +order.getBaker().getEmail());
        C_email.setText("מייל הלקוח: " +order.getCustomer().getEmail());
        B_phone.setText("טלפון האופה: " +order.getBaker().getPhone());
        C_phone.setText("טלפון הלקוח: " +order.getCustomer().getPhone());
        B_name.setText("שם האופה: " +order.getBaker().getFull_name());
        C_name.setText("שם הלקוח: " +order.getCustomer().getFull_name());
        B_City.setText("עיר: " +order.getBaker().getAddress().getCity());
        C_City.setText("עיר: " +order.getCustomer().getAddress().getCity());
        B_Street.setText("רחוב: " +order.getBaker().getAddress().getStreetName());
        C_Street.setText("רחוב: " +order.getCustomer().getAddress().getStreetName());
        B_House.setText("מספר בית: " +order.getBaker().getAddress().getBuildingNumber());
        C_House.setText("מספר בית: " +order.getCustomer().getAddress().getBuildingNumber());
        B_floor.setText("קומה: " +order.getBaker().getAddress().getFloor());
        C_floor.setText("קומה: " +order.getCustomer().getAddress().getFloor());
        B_appartment.setText("דירה: " +order.getBaker().getAddress().getAppartmentNumber());
        C_appartment.setText("דירה: " +order.getCustomer().getAddress().getAppartmentNumber());
        date.setText("תאריך: " +order.getDate());
        namePastry.setText("שם המאפה: " +order.getPastry().getName());
        comments.setText("הערות: " +order.getComments());
        if(order.isCard() == true){
            pay.setText("אמצעי תשלום: אשראי");
        }
        else {
            pay.setText("אמצעי תשלום: מזומן");
        }

        if (order.isDelivery()==true){
            recievedOrder.setText("קבלת ההזמנה: משלוח");
        }
        else{
            recievedOrder.setText("קבלת ההזמנה: איסוף עצמי");
        }
        return listViewItem;
    }
}