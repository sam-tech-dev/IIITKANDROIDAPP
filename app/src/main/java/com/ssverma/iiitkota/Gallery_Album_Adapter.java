package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IIITK on 6/1/2016.
 */
public class Gallery_Album_Adapter extends RecyclerView.Adapter<Gallery_Album_Adapter.ViewHolder>{

    //private ArrayList<Integer> uniqueAlbumList;
    private Context context;
    private HashMap<Integer , ArrayList<Gallery_Images_Wrapper>> album_map;
    private ArrayList<Gallery_Album_Wrapper> albumList;
    private RCVClickListener listener;

    Gallery_Album_Adapter(Context context , ArrayList<Gallery_Album_Wrapper> albumList , HashMap<Integer , ArrayList<Gallery_Images_Wrapper>> album_map){
        this.context = context;
        //this.uniqueAlbumList = uniqueAlbumList;
        this.album_map = album_map;
        this.albumList = albumList;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_album_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.album_name.setText(albumList.get(position).getAlbum_title());
        holder.no_of_images.setText(album_map.get(albumList.get(position).getAlbum_number()).size() + "  image(s)");
        Picasso.with(context).load(ServerContract.getGalleryAlbumThumbnailPath() + albumList.get(position).getAlbum_number() + "/" + albumList.get(position).getAlbum_thumbnail()).resize(1000 , 500).placeholder(R.drawable.gallery_album_placeholder).into(holder.album_thumbnail);
        //Toast.makeText(context , ServerContract.getGalleryAlbumThumbnailPath() + albumList.get(position).getAlbum_number() + "/" + albumList.get(position).getAlbum_thumbnail() , Toast.LENGTH_LONG).show();
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView album_name;
        TextView no_of_images;
        ImageView album_thumbnail;

        LinearLayout album_data_holder;

        public ViewHolder(View itemView) {
            super(itemView);

            album_name = (TextView) itemView.findViewById(R.id.gallery_album_name);
            no_of_images = (TextView) itemView.findViewById(R.id.gallery_album_no_of_images);
            album_thumbnail = (ImageView) itemView.findViewById(R.id.gallery_album_thumbnail);

            album_data_holder = (LinearLayout) itemView.findViewById(R.id.gallery_albums_holder);

            album_data_holder.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }
}
