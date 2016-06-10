package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
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
 * Author-Dixit Chauhan      :08/06/2016
 */
class ScholarshipAdapter extends RecyclerView.Adapter<ScholarshipAdapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<ScholarshipWrapper> listEvents;

    private RCVClickListener listener;

    ScholarshipAdapter(Context context , ArrayList<ScholarshipWrapper> list){
        this.context = context;
        this.listEvents = list;
    }



    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.scholarship_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // String name = "<html><style type=\"text/css\">p{text-align:justify;font-size:12px;}</style></head><body>"+"<p>"+listEvents.get(position).getName()+"</p>"+"</body></html>" ;
        holder.scholarship_name.setText(listEvents.get(position).getName());
        holder.scholarship_link.setText(listEvents.get(position).getLink());

        // Toast.makeText(this.context,""+ServerContract.getNewsImagePath()+listEvents.get(position).getImage(),Toast.LENGTH_LONG).show();
        //Picasso.with(context).load(ServerContract.getEventsImagePath()+listEvents.get(position).getImage()).into(holder.event_image);

        //holder.news_image.setImageResource(R.drawable.background);

        //Toast.makeText(this.context, "" +ServerContract.getNewsImagesUrl()+listNews.get(0).getFaculty_imageLink()  , Toast.LENGTH_LONG).show();
      //  holder.events_date_cal.setText(listEvents.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView scholarship_name;
        TextView scholarship_link;
        TextView events_date_cal;
        RelativeLayout event_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.scholarship_row_item_holder);
            itemHolder.setOnClickListener(this);

            scholarship_name= (TextView) itemView.findViewById(R.id.scholarship_name);
            scholarship_name.setSelected(true);
            scholarship_link = (TextView) itemView.findViewById(R.id.scholarship_link);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }
}
