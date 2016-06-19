package com.ssverma.iiitkota;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


class Academic_Timetable_Adapter extends RecyclerView.Adapter<Academic_Timetable_Adapter.ViewHolder>{

    private Context context;
    private ArrayList<Academic_TimetableWrapper> list;

    private RCVClickListener listener;

    Academic_Timetable_Adapter(Context context , ArrayList<Academic_TimetableWrapper> list){
        this.context = context;
        this.list = list;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_timetable_row_item, parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.academic_timetable__row_item_holder);
            itemHolder.setOnClickListener(this);

            name= (TextView) itemView.findViewById(R.id.name);
            name.setSelected(true);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }

    }
}
