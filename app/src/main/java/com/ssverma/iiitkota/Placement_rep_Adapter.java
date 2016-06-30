package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Placement_rep_Adapter extends RecyclerView.Adapter<Placement_rep_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<PlacementWrapper> listPlace;

    private RCVClickListener listener;

    Placement_rep_Adapter(Context context, ArrayList<PlacementWrapper> listPlace) {
        this.context = context;
        this.listPlace = listPlace;
    }

    public void setOnRCVClickListener(RCVClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.representative_info_row_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.rep_name.setText(listPlace.get(position).getName());
        holder.rep_mail.setText(listPlace.get(position).getMail());
        holder.rep_contact.setText(listPlace.get(position).getContact());
        holder.rep_position.setText(listPlace.get(position).getPosition());
        Picasso.with(context).load(ServerContract.getProgramImagePath() + listPlace.get(position).getImage()).into(holder.rep_image);
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView rep_name;
        TextView rep_mail;
        TextView rep_position;
        TextView rep_contact;
        CircleImageView rep_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.rep_row_item_holder);
            itemHolder.setOnClickListener(this);

            rep_name = (TextView) itemView.findViewById(R.id.rep_name);
            rep_mail = (TextView) itemView.findViewById(R.id.rep_email);
            rep_contact = (TextView) itemView.findViewById(R.id.rep_contact);
            rep_position = (TextView) itemView.findViewById(R.id.rep_position);
            rep_image = (CircleImageView) itemView.findViewById(R.id.rep_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onRCVClick(v, getAdapterPosition());
            }
        }
    }
}
