package com.vishalpvijayan.khoslalabtaskvishal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private PojoforRecycler[] listdata;

    public ListAdapter(PojoforRecycler[] listdata) {
        this.listdata = listdata;
    }


    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rowdesign, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {


        final PojoforRecycler myListData = listdata[position];
        holder.txtDate.setText(listdata[position].getDate());
        holder.txtTemp.setText(listdata[position].getTemperature());
        holder.imageIcon.setImageResource(listdata[position].getImgID());



    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtTemp;
        ImageView imageIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            imageIcon = itemView.findViewById(R.id.imageIcon);
        }
    }
}
