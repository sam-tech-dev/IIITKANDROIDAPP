package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IIITK on 6/1/2016.
 */
public class Gallery_Images_Adapter extends RecyclerView.Adapter<Gallery_Images_Adapter.ViewHolder>{

    private Context context;
    private HashMap<Integer , ArrayList<Gallery_Album_Wrapper>> album_map;

    Gallery_Images_Adapter(Context context , HashMap<Integer , ArrayList<Gallery_Album_Wrapper>> album_map){
        this.context = context;
        this.album_map = album_map;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_images_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return album_map.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView image_name;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            image_name = (TextView) itemView.findViewById(R.id.gallery_image_name);
            image = (ImageView) itemView.findViewById(R.id.gallery_image_thumbnail);

        }
    }
}
