package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author-Dixit Chauhan      :03/06/2016
 */
class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<EventsWrapper> listEvents;

    private RCVClickListener listener;

    EventsAdapter(Context context , ArrayList<EventsWrapper> list){
        this.context = context;
        this.listEvents = list;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.events_title.setText(listEvents.get(position).getTitle());
        holder.events_subtitle.setText(listEvents.get(position).getSubtitle());

       // Toast.makeText(this.context,""+ServerContract.getNewsImagePath()+listEvents.get(position).getImage(),Toast.LENGTH_LONG).show();
        //Picasso.with(context).load(ServerContract.getEventsImagePath()+listEvents.get(position).getImage()).into(holder.event_image);

        //holder.news_image.setImageResource(R.drawable.background);

        //Toast.makeText(this.context, "" +ServerContract.getNewsImagesUrl()+listNews.get(0).getFaculty_imageLink()  , Toast.LENGTH_LONG).show();
        holder.events_date_cal.setText(listEvents.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView events_title;
        TextView events_subtitle;
        TextView events_date_cal;
        RelativeLayout event_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.events_row_item_holder);
            itemHolder.setOnClickListener(this);

            events_date_cal= (TextView) itemView.findViewById(R.id.date);
            events_title= (TextView) itemView.findViewById(R.id.events_title);
            events_title.setSelected(true);
            events_subtitle = (TextView) itemView.findViewById(R.id.events_subtitle);
            event_image = (RelativeLayout) itemView.findViewById(R.id.event_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }
}
