package com.jok.pieceofcake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PastryAdapter extends ArrayAdapter {
    private Context context;
    private int rowLayout;
    private Pastry[] pastries;

    public PastryAdapter(@NonNull Context context, int rowLayout, @NonNull Pastry[] pastries) {
        super(context, rowLayout);
        this.context = context;
        this.rowLayout = rowLayout;
        this.pastries = pastries;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View row = inflater.inflate(rowLayout, parent, false);

        TextView price = row.findViewById(R.id.price);
        TextView name = row.findViewById(R.id.pastryName);
        TextView allerganics = row.findViewById(R.id.allerganics);
        TextView description = row.findViewById(R.id.descript);

        Pastry pastry = pastries[position];

        price.setText(pastry.price);
        name.setText(pastry.name);
        allerganics.setText(pastry.allerganics);
        description.setText(pastry.description);

        return row;
    }

    @Override
    public int getCount() {
        if(pastries == null) return 0;
        else return pastries.length;
    }
}