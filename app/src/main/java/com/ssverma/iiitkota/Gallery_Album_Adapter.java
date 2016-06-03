package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IIITK on 6/1/2016.
 */
public class Gallery_Album_Adapter extends RecyclerView.Adapter<Gallery_Album_Adapter.ViewHolder>{

    private ArrayList<Integer> uniqueAlbumList;
    private Context context;
    private HashMap<Integer , ArrayList<Gallery_Album_Wrapper>> album_map;

    Gallery_Album_Adapter(Context context , ArrayList<Integer> uniqueAlbumList , HashMap<Integer , ArrayList<Gallery_Album_Wrapper>> album_map){
        this.context = context;
        this.uniqueAlbumList = uniqueAlbumList;
        this.album_map = album_map;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_album_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.album_name.setText(album_map.get(uniqueAlbumList.get(position)).get(position).getAlbum_thumbnail_link());
        holder.no_of_images.setText(album_map.get(uniqueAlbumList.get(position)).size() + " image(s)");
        Picasso.with(context).load(ServerContract.getGalleryImagesPath() + uniqueAlbumList.get(position) + "/" + album_map.get(uniqueAlbumList.get(position)).get(position).getAlbum_thumbnail_link()).resize(500 , 500).placeholder(R.drawable.gallery_album_placeholder).into(holder.album_thumbnail);
        //Toast.makeText(context , ServerContract.getGalleryImagesPath() + uniqueAlbumList.get(position) + "/" + album_map.get(uniqueAlbumList.get(position)).get(position).getAlbum_thumbnail_link() + " : Link" , Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return album_map.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView album_name;
        TextView no_of_images;
        ImageView album_thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);

            album_name = (TextView) itemView.findViewById(R.id.gallery_album_name);
            no_of_images = (TextView) itemView.findViewById(R.id.gallery_album_no_of_images);
            album_thumbnail = (ImageView) itemView.findViewById(R.id.gallery_album_thumbnail);
        }
    }
}
