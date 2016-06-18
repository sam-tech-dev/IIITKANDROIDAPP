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
 * Author-Dixit Chauhan : 13/06/2016
 */
public class Academic_Research_Adapter extends RecyclerView.Adapter<Academic_Research_Adapter.ViewHolder > {

    private Context context;
    private ArrayList<Academic_ResearchWrapper> list;

   private RCVClickListener listener;

    Academic_Research_Adapter(Context context, ArrayList<Academic_ResearchWrapper> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnRCVClickListener(RCVClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_research_row_item, parent, false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.person_name.setText(list.get(position).getPerson_name());
        holder.project_name.setText(list.get(position).getProject_name());

        Picasso.with(context).load(ServerContract.getAcademicResearchPersonImagePath() + list.get(position).getPerson_image_link()).into(holder.person_image);
       // Picasso.with(getApplicationContext()).load(ServerContract.getProgramImagesPath() + getIntent().getExtras().getString("Program_image")).into(program_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView person_name;
        TextView project_name;
        CircleImageView person_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.project_row_item_holder);
            itemHolder.setOnClickListener(this);

            person_name= (TextView) itemView.findViewById(R.id.person_name);;
            project_name = (TextView) itemView.findViewById(R.id.project_name);

            person_image = (CircleImageView) itemView.findViewById(R.id.person_image);
        }


       @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onRCVClick(v, getAdapterPosition());
            }
        }
    }
}
