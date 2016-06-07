package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IIITK on 5/31/2016.
 * Adapter for Programs Module--Rajat Jain
 */
public class Program_Adapter extends RecyclerView.Adapter<Program_Adapter.ViewHolder > {

    private Context context;
    private ArrayList<ProgramWrapper> listProgram;

    private RCVClickListener listener;

    Program_Adapter(Context context, ArrayList<ProgramWrapper> listProgram) {
        this.context = context;
        this.listProgram = listProgram;
    }

    public void setOnRCVClickListener(RCVClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_row_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.program_name.setText(listProgram.get(position).getProgram_name());
        holder.program_desc.setText(listProgram.get(position).getProgram_desc());
        Picasso.with(context).load(ServerContract.getProgramImagePath() + listProgram.get(position).getProgram_image()).into(holder.program_image);
       // Picasso.with(getApplicationContext()).load(ServerContract.getProgramImagesPath() + getIntent().getExtras().getString("Program_image")).into(program_image);

    }

    @Override
    public int getItemCount() {
        return listProgram.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView program_name;
        TextView program_desc;
        CircleImageView program_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.program_row_item_holder);
            itemHolder.setOnClickListener(this);

            program_name = (TextView) itemView.findViewById(R.id.program_name);
            program_desc = (TextView) itemView.findViewById(R.id.program_desc);
            program_image = (CircleImageView) itemView.findViewById(R.id.program_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onRCVClick(v, getAdapterPosition());
            }
        }
    }
}
