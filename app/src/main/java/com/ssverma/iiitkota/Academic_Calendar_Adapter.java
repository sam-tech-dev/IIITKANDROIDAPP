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

/**
 * Author-Dixit Chauhan      :08/06/2016
 */
class Academic_Calendar_Adapter extends RecyclerView.Adapter<Academic_Calendar_Adapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<Academic_CalendarWrapper> list;

    private RCVClickListener listener;

    Academic_Calendar_Adapter(Context context , ArrayList<Academic_CalendarWrapper> list){
        this.context = context;
        this.list = list;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.academic_calendar_row_item, parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // String name = "<html><style type=\"text/css\">p{text-align:justify;font-size:12px;}</style></head><body>"+"<p>"+listEvents.get(position).getName()+"</p>"+"</body></html>" ;
        holder.name.setText(list.get(position).getName());
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
        TextView name;
     //   TextView link;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.academic_calendar__row_item_holder);
            itemHolder.setOnClickListener(this);


            name = (TextView) itemView.findViewById(R.id.name);
            name.setSelected(true);
        //    link = (TextView) itemView.findViewById(R.id.link);
         //   link.setSelected(true);

         /*   link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "http://172.16.1.231/iiitk/android/assets/documents/academic_calendar/" +link.getText().toString();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
            });*/
        }

            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onRCVClick(v , getAdapterPosition());
                }
            }

    }
}
