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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IIITK on 5/27/2016.
 */
public class Faculty_Adapter extends RecyclerView.Adapter<Faculty_Adapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<FacultyWrapper> listFaculty;

    private RCVClickListener listener;

    Faculty_Adapter(Context context , ArrayList<FacultyWrapper> listFaculty){
        this.context = context;
        this.listFaculty = listFaculty;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.faculty_name.setText(listFaculty.get(position).getFaculty_name());
        holder.faculty_email.setText(listFaculty.get(position).getFaculty_email());
        Picasso.with(context).load(ServerContract.getFacultyImagesPath() + listFaculty.get(position).getFaculty_imageLink()).into(holder.faculty_image);
    }

    @Override
    public int getItemCount() {
        return listFaculty.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView faculty_name;
        TextView faculty_email;
        CircleImageView faculty_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.faculty_row_item_holder);
            itemHolder.setOnClickListener(this);

            faculty_name = (TextView) itemView.findViewById(R.id.faculty_name);
            faculty_email = (TextView) itemView.findViewById(R.id.faculty_email);
            faculty_image = (CircleImageView) itemView.findViewById(R.id.faculty_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }
}