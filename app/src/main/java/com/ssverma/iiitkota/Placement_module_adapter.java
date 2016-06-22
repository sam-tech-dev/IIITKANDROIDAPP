package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IIITK on 6/13/2016.
 */

public class Placement_module_adapter extends RecyclerView.Adapter<Placement_module_adapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<Placement_module_wrapper> list;

    private RCVClickListener listener;

    Placement_module_adapter(Context context , ArrayList<Placement_module_wrapper> list){
        this.context = context;
        this.list = list;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.placement_module_row_item, parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // String name = "<html><style type=\"text/css\">p{text-align:justify;font-size:12px;}</style></head><body>"+"<p>"+listEvents.get(position).getName()+"</p>"+"</body></html>" ;
        holder.report_name.setText(list.get(position).getReport_type());
        //   holder.link.setText(list.get(position).getLink());

        // Toast.makeText(this.context,""+ServerContract.getNewsImagePath()+listEvents.get(position).getImage(),Toast.LENGTH_LONG).show();
        //Picasso.with(context).load(ServerContract.getEventsImagePath()+listEvents.get(position).getImage()).into(holder.event_image);

        //holder.news_image.setImageResource(R.drawable.background);

        //Toast.makeText(this.context, "" +ServerContract.getNewsImagesUrl()+listNews.get(0).getFaculty_imageLink()  , Toast.LENGTH_LONG).show();
        //  holder.events_date_cal.setText(listEvents.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView report_name;
        //   TextView link;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.plt_row_item_holder);
            itemHolder.setOnClickListener(this);


            report_name = (TextView) itemView.findViewById(R.id.report_name);
            report_name.setSelected(true);

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }

    }

}