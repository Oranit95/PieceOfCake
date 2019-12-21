package com.jok.pieceofcake.customerSide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jok.pieceofcake.Order;
import com.jok.pieceofcake.R;

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
/**
        TextView numOfOrder = listViewItem.findViewById(R.id.numOforderC);
        TextView B_email = listViewItem.findViewById(R.id.baker_email);
        TextView City = listViewItem.findViewById(R.id.city_C);
        TextView date = listViewItem.findViewById(R.id.date_C);
        TextView pastry_name = listViewItem.findViewById(R.id.pastry_name_C);
        TextView pastry_id = listViewItem.findViewById(R.id.pastry_id_C);
        TextView pay = listViewItem.findViewById(R.id.pay_C);

        Order order = ordersList.get(position);
        numOfOrder.setText("מספר הזמנה: " +order.getNumOfOrder());
        B_email.setText("מייל האופה: " +order.getB_email());
        //City.setText("עיר: " +order.getCity());
        date.setText("תאריך: " +order.getDate());
        pastry_name.setText("שם המאפה: " +order.getPastry_name());
 pastry_id.setText("מזהה המאפה: " +order.getPastry_id());**/
        return listViewItem;
    }
}