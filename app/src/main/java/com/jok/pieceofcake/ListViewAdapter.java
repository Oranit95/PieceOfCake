package com.jok.pieceofcake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jok.pieceofcake.PastryItem;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<PastryItem> pastryList;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<PastryItem> pastryList){
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Adapter has to know your model list
        this.pastryList=pastryList;
    }

    @Override
    public int getCount() {
        //Return listview item count
        //It is the same with your model list size.
        return pastryList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)    {
        View view=inflater.inflate(R.layout.list_view_item,null,false);
        //Adapter gives position parameter.
        //This parameter helps us to know which item view is wanted by adapter.
        ((TextView)view.findViewById(R.id.album_name)).setText(pastryList.get(position).getName());
        ((TextView)view.findViewById(R.id.artist_name)).setText(pastryList.get(position).getPrice());
        return view;
    }
}