package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class Fest_Adapter extends RecyclerView.Adapter<Fest_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<FestWrapper> listFest;

    private RCVClickListener listener;

    Fest_Adapter(Context context, ArrayList<FestWrapper> listFest) {
        this.context = context;
        this.listFest = listFest;
    }

    public void setOnRCVClickListener(RCVClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fest_row_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.fest_name.setText(listFest.get(position).getFest_name());
        Picasso.with(context).load(ServerContract.getFestImagesUrl() + listFest.get(position).getFest_image_link()).into(holder.fest_image);
    }

    @Override
    public int getItemCount() {
        return listFest.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView fest_name;

        ImageView fest_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.fest_row_item_holder);
            itemHolder.setOnClickListener(this);

            fest_name = (TextView) itemView.findViewById(R.id.title);

            fest_image = (ImageView) itemView.findViewById(R.id.fest_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onRCVClick(v, getAdapterPosition());
            }
        }
    }
}
