package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Administration_Adapter extends RecyclerView.Adapter<Administration_Adapter.ViewHolder>{

    private Context context;
    private ArrayList<AdministrationWrapper> listAdministration;

    private RCVClickListener listener;

    Administration_Adapter(Context context , ArrayList<AdministrationWrapper> listAdministration){
        this.context = context;
        this.listAdministration = listAdministration;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.administration_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.admin_name.setText(listAdministration.get(position).getAdmin_name());
        holder.admin_designation.setText(listAdministration.get(position).getAdmin_designation());
        holder.admin_category.setText(listAdministration.get(position).getAdmin_category());

    }

    @Override
    public int getItemCount() {
        return listAdministration.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView admin_name;
        TextView admin_designation;
        TextView admin_category;


        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.faculty_row_item_holder);
            itemHolder.setOnClickListener(this);

            admin_name = (TextView) itemView.findViewById(R.id.admin_name);
            admin_designation = (TextView) itemView.findViewById(R.id.admin_designation);
            admin_category = (TextView) itemView.findViewById(R.id.admin_category);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }
}
