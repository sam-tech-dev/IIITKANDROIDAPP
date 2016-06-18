package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Author-Dixit Chauhan      :16/06/2016
 */
class Academic_CoursesAdapter extends RecyclerView.Adapter<Academic_CoursesAdapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<Academic_CoursesWrapper> list;

    //private RCVClickListener listener;

    Academic_CoursesAdapter(Context context , ArrayList<Academic_CoursesWrapper> list){
        this.context = context;
        this.list = list;
    }

   // public void setOnRCVClickListener(RCVClickListener listener){this.listener = listener;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_courses_row_item, parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.course_name.setText(list.get(position).getCourse_name());
        holder.course_code.setText(list.get(position).getCode());
        holder.credit.setText(list.get(position).getCredit());
        holder.abbr.setText(list.get(position).getAbbr());


       // Toast.makeText(this.context,""+ServerContract.getNewsImagePath()+listEvents.get(position).getImage(),Toast.LENGTH_LONG).show();
        //Picasso.with(context).load(ServerContract.getEventsImagePath()+listEvents.get(position).getImage()).into(holder.event_image);

        //holder.news_image.setImageResource(R.drawable.background);

        //Toast.makeText(this.context, "" +ServerContract.getNewsImagesUrl()+listNews.get(0).getFaculty_imageLink()  , Toast.LENGTH_LONG).show();
        //holder.events_date_cal.setText(listEvents.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout itemHolder;
        TextView course_name;
        TextView course_code;
        TextView abbr;
        TextView credit;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (LinearLayout) itemView.findViewById(R.id.courses_row_item_holder);
         //   itemHolder.setOnClickListener(this);

            course_name= (TextView) itemView.findViewById(R.id.course_name);
            course_code= (TextView) itemView.findViewById(R.id.course_code);
            abbr = (TextView) itemView.findViewById(R.id.abbr);
            credit = (TextView) itemView.findViewById(R.id.credit);

        }

    }
}
