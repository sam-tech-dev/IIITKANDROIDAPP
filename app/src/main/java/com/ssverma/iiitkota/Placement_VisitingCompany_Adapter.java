package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Placement_VisitingCompany_Adapter extends RecyclerView.Adapter<Placement_VisitingCompany_Adapter.ViewHolder >{
    private Context context;
    private ArrayList<Placement_VisitingCompany_Wrapper> listPlace;

    private RCVClickListener listener;

    Placement_VisitingCompany_Adapter(Context context, ArrayList<Placement_VisitingCompany_Wrapper> listPlace) {
        this.context = context;
        this.listPlace = listPlace;
    }

    public void setOnRCVClickListener(RCVClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.visiting_company_row_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(listPlace.get(position).getCompany_name());
        holder.summary.setText(listPlace.get(position).getCompany_summary());
    }

    @Override
    public int getItemCount() {
        return listPlace.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView name;
        TextView summary;
        CircleImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.vc_row_item_holder);
            itemHolder.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.vc_name);
            summary = (TextView) itemView.findViewById(R.id.vc_desc);
            image = (CircleImageView) itemView.findViewById(R.id.vc_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onRCVClick(v, getAdapterPosition());
            }
        }
    }
}
