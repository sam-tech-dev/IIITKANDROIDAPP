package com.ssverma.iiitkota;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IIITK on 6/1/2016.
 */
public class Gallery_Images_Adapter extends RecyclerView.Adapter<Gallery_Images_Adapter.ViewHolder>{

    private Context context;
    private HashMap<Integer , ArrayList<Gallery_Images_Wrapper>> album_map;
    private int album_number;

    private RCVClickListener listener;

    Gallery_Images_Adapter(Context context , HashMap<Integer , ArrayList<Gallery_Images_Wrapper>> album_map , int album_number){
        this.context = context;
        this.album_map = album_map;
        this.album_number = album_number;
    }

    public void setRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_images_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //holder.image_name.setText(album_map.get(album_number).get(position).getImageLink());
        Picasso.with(context).load(ServerContract.getGalleryImagesPath() + album_number + "/" + album_map.get(album_number).get(position).getImageLink()).resize(1000 , 1000).placeholder(R.drawable.gallery_album_placeholder).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return album_map.get(album_number).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView image_name;
        ImageView image;
        FrameLayout gallery_images_itemHolder;

        public ViewHolder(View itemView) {
            super(itemView);

            //image_name = (TextView) itemView.findViewById(R.id.gallery_image_name);
            image = (ImageView) itemView.findViewById(R.id.gallery_image_thumbnail);

            gallery_images_itemHolder = (FrameLayout) itemView.findViewById(R.id.gallery_images_holder);

            gallery_images_itemHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }

    }

}
