package com.jok.pieceofcake;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PastryImageAdapter extends RecyclerView.Adapter<PastryImageAdapter.ImageViewHolder> {
    private Context context;
    private List<Upload> uploads;
    private OnItemClickListener listener;

    public PastryImageAdapter(Context context, List<Upload> uploads){
        this.context=context;
        this.uploads=uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCurrent = uploads.get(position);
        Picasso.with(context)
                .load(uploadCurrent.getImageURL())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public Button delete;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pastryImage);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View view) {
            if(listener!=null){
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view,
                                        ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("בחר פעולה");
            MenuItem delete = contextMenu.add(Menu.NONE, 1, 1, "מחק תמונה");
            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            if(listener!=null){
                int position = getAdapterPosition();
                if(position!=RecyclerView.NO_POSITION){
                    listener.onDeleteClick(position);
                    return  true;
                }
            }
            return false;
        }
    }
    public interface  OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);


    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
