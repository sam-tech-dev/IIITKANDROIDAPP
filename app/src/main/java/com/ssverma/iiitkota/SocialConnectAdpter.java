package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SocialConnectAdpter extends  RecyclerView.Adapter<SocialConnectAdpter.ViewHolder>{

    private Context context;
   static  ArrayList<Social> socialList;


    private RCVClickListener listener;

    SocialConnectAdpter(Context context){
        socialList=new ArrayList<>();
        this.context = context;

        Social fb=new Social("facebook","Facebook","facebook","https://www.facebook.com/HgSmiiitians/");
        Social gp=new Social("googleplus","Google Plus","googleplus","https://plus.google.com/117122396406295766249");
        Social lkd=new Social("linkedin","Linkedin","linkedin","https://www.linkedin.com/company/iiit-delhi");
        Social tw=new Social("twitter","Twitter","twitter","https://mobile.twitter.com/iiitkota");
        Social yt=new Social("youtube","Youtube","youtube","https://www.youtube.com/channel/UCEOTlGZG4nBLkbj31_1j1Ug");

        socialList.add(fb);
        socialList.add(gp);
        socialList.add(lkd);
        socialList.add(tw);
        socialList.add(yt);

    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_social_connect_adpter , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.internal_connect.setImageResource(context.getResources().getIdentifier("com.ssverma.iiitkota:drawable/" + socialList.get(position).internalImage, null, null));
        holder.connect_name.setText(socialList.get(position).name);
        //holder.external_connect.setImageResource(R.drawable.social_connect_external_icon);

    }

    @Override
    public int getItemCount() {
        return socialList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView connect_name;
        ImageView external_connect;
        CircleImageView internal_connect;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.connect_row_item_holder);
            itemHolder.setOnClickListener(this);

            connect_name = (TextView) itemView.findViewById(R.id.connectName);
            internal_connect = (CircleImageView) itemView.findViewById(R.id.internalConnect);
            external_connect = (ImageView) itemView.findViewById(R.id.externalConnect);
            external_connect.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }



    class Social{

          String internalImage,name, externalImage,url;

        public Social(String inter,String nm, String exter,String url1){
            this.internalImage=inter;
            this.name=nm;
            this.externalImage=exter;
            this.url=url1;

        }

    }
}
