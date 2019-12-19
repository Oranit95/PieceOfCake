package com.jok.pieceofcake;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PastryAdapter extends ArrayAdapter<Pastry> {
    private Context context;
    private int rowLayout;
    private List<Pastry> pastries;

    public PastryAdapter(@NonNull Context context,int resorce, int rowLayout, @NonNull List<Pastry> pastries) {
        super(context,resorce, rowLayout,pastries);
        this.context = context;
        //this.rowLayout = rowLayout;
        this.pastries = pastries;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =  ((Activity)context).getLayoutInflater();
                //LayoutInflater.from(context);
        View row = inflater.inflate(rowLayout, parent, false);

        TextView price = row.findViewById(R.id.price);
        TextView name = row.findViewById(R.id.pastryName);
        TextView allerganics = row.findViewById(R.id.allerganics);
        TextView description = row.findViewById(R.id.descript);

        Pastry pastry = pastries.get(position);
        price.setText(pastry.getPrice());
        name.setText(pastry.getName());
        allerganics.setText(pastry.getAllerganics());
        description.setText(pastry.getDescription());

        return row;
    }

    @Override
    public int getCount() {
        if(pastries == null) return 0;
        else return pastries.size();
    }
}
