package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

class Academic_ResourcesAdapter extends RecyclerView.Adapter<Academic_ResourcesAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Academic_ResourcesWrapper> list;

    Academic_ResourcesAdapter(Context context , ArrayList<Academic_ResourcesWrapper> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_resources_row_item, parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.lab_no.setText(list.get(position).getLab_no());
        holder.total_pc.setText(list.get(position).getTotal_pc());
        holder.deployment.setText(list.get(position).getDeployment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout itemHolder;
        TextView lab_no;
        TextView deployment;
        TextView total_pc;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (LinearLayout) itemView.findViewById(R.id.resources_row_item_holder);

            lab_no= (TextView) itemView.findViewById(R.id.lab_no);
            total_pc= (TextView) itemView.findViewById(R.id.total_pc);
            deployment = (TextView) itemView.findViewById(R.id.deployment);

        }

    }
}
