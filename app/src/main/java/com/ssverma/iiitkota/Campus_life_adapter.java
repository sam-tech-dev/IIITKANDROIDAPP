package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


class Campus_life_adapter extends RecyclerView.Adapter<Campus_life_adapter.ViewHolder> {

    private Context context;
    private ArrayList<Campus_life_wrapper> listCampus;

    private RCVClickListener listener;

    Campus_life_adapter(Context context, ArrayList<Campus_life_wrapper> listCampus) {
        this.context = context;
        this.listCampus = listCampus;
    }

    public void setOnRCVClickListener(RCVClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.campus_life_row_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.campus_life_tittle.setText(listCampus.get(position).getCampus_tittle());

        Picasso.with(context).load(ServerContract.getCampusImagesUrl() + listCampus.get(position).getCampus_imageLink()).into(holder.campus_life_image);
    }

    @Override
    public int getItemCount() {
        return listCampus.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView campus_life_tittle;

        ImageView campus_life_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.campus_row_item_holder);
            itemHolder.setOnClickListener(this);

            campus_life_tittle = (TextView) itemView.findViewById(R.id.title);
            campus_life_image = (ImageView) itemView.findViewById(R.id.campus_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onRCVClick(v, getAdapterPosition());
            }
        }
    }
}
