package com.jok.pieceofcake;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class OrdersBakerAdapter extends ArrayAdapter<OrderB> {

    private Activity context;
    private List<OrderB> ordersB;

    public OrdersBakerAdapter(Activity context, List<OrderB>ordersB){
        super(context, R.layout.order_baker_item,ordersB);
        this.context = context;
        this.ordersB = ordersB;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.order_baker_item, null, true);

        TextView numOfOrder = listViewItem.findViewById(R.id.numOfOrder);
        TextView C_email = listViewItem.findViewById(R.id.C_email);
        TextView City = listViewItem.findViewById(R.id.C_city);
        TextView date = listViewItem.findViewById(R.id.date);
        TextView pastry_name = listViewItem.findViewById(R.id.pastry_name);
        TextView pastry_id = listViewItem.findViewById(R.id.pastry_id);
        TextView pay = listViewItem.findViewById(R.id.pay);

        OrderB orderB = ordersB.get(position);
        numOfOrder.setText("מספר הזמנה: " +orderB.getNumOfOrder());
        numOfOrder.setText("מייל הלקוח: " +orderB.getC_email());
        numOfOrder.setText("עיר: " +orderB.getCity());
        numOfOrder.setText("תאריך: " +orderB.getDate());
        numOfOrder.setText("שם המאפה: " +orderB.getPastry_name());
        numOfOrder.setText("מזהה המאפה: " +orderB.getPastry_id());
        numOfOrder.setText("תשלום: " +orderB.getPay());
        return listViewItem;
    }
}
