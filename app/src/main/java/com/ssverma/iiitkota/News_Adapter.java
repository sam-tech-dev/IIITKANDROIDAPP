package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IIITK on 5/27/2016.
 */
class News_Adapter extends RecyclerView.Adapter<News_Adapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<NewsWrapper> listNews;

    private RCVClickListener listener;

    News_Adapter(Context context , ArrayList<NewsWrapper> listNews){
        this.context = context;
        this.listNews = listNews;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.news_tittle.setText(listNews.get(position).getNews_tittle());
        holder.news_date.setText(listNews.get(position).getNews_subtitle());
        //Toast.makeText(this,ServerContract.getNewsImagesUrl()+listNews.get(position).getNews_imageLink(),Toast.LENGTH_SHORT).show();
       // Picasso.with(context).load(ServerContract.getNewsImagesUrl()+listNews.get(position).getNews_imageLink()).into(holder.news_image);

        //holder.news_image.setImageResource(R.drawable.background);

        //Toast.makeText(this.context, "" +ServerContract.getNewsImagesUrl()+listNews.get(0).getNews_imageLink()  , Toast.LENGTH_LONG).show();
        holder.newsdatecal.setText(listNews.get(position).getNews_date());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView news_tittle;
        TextView news_date;
        RelativeLayout news_image;
         TextView newsdatecal;
        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.news_row_item_holder);
            itemHolder.setOnClickListener(this);
            newsdatecal= (TextView) itemView.findViewById(R.id.date);
            news_tittle= (TextView) itemView.findViewById(R.id.newsfeed);
            news_date = (TextView) itemView.findViewById(R.id.newsfeed_date);
            news_image = (RelativeLayout) itemView.findViewById(R.id.news_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }
}
