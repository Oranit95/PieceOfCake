package com.jok.pieceofcake.customerSide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jok.pieceofcake.R;
import com.jok.pieceofcake.bakerSide.OrderB;

import java.util.List;

public class OrderCustomerAdpter extends ArrayAdapter<OrderC> {

    private Activity context;
    private List<OrderC> ordersC;

    public OrderCustomerAdpter(Activity context, List<OrderC> ordersC) {
        super(context, R.layout.order_customer_item, ordersC);
        this.context = context;
        this.ordersC = ordersC;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.order_customer_item, null, true);

        TextView numOfOrder = listViewItem.findViewById(R.id.numOforderC);
        TextView B_email = listViewItem.findViewById(R.id.baker_email);
        TextView City = listViewItem.findViewById(R.id.city_C);
        TextView date = listViewItem.findViewById(R.id.date_C);
        TextView pastry_name = listViewItem.findViewById(R.id.pastry_name_C);
        TextView pastry_id = listViewItem.findViewById(R.id.pastry_id_C);
        TextView pay = listViewItem.findViewById(R.id.pay_C);

        OrderC orderC = ordersC.get(position);
        numOfOrder.setText("מספר הזמנה: " +orderC.getNumOfOrder());
        B_email.setText("מייל האופה: " +orderC.getB_email());
        City.setText("עיר: " +orderC.getCity());
        date.setText("תאריך: " +orderC.getDate());
        pastry_name.setText("שם המאפה: " +orderC.getPastry_name());
        pastry_id.setText("מזהה המאפה: " +orderC.getPastry_id());
        pay.setText("תשלום: " +orderC.getPay());
        return listViewItem;
    }
}