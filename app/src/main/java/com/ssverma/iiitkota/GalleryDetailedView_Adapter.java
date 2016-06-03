package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by IIITK on 6/2/2016.
 */
public class GalleryDetailedView_Adapter extends PagerAdapter{

    private Context context;
    private int album_number;

    GalleryDetailedView_Adapter(Context context , int album_number){
        this.context = context;
        this.album_number = album_number;
    }

    @Override
    public int getCount() {
        return Gallery.getAlbum_map().get(album_number).size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final ImageView gdv_imageView;

        View rootView = LayoutInflater.from(container.getContext()).inflate(R.layout.gallery_detailed_view_row_item , container , false);

        gdv_imageView = (ImageView) rootView.findViewById(R.id.gdv_full_screen_image);
        Picasso.with(context).load(ServerContract.getGalleryImagesPath() + album_number + "/" + Gallery.getAlbum_map().get(album_number).get(position).getImageLink()).resize(1366,1000).placeholder(R.drawable.loading_image).into(gdv_imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(context).load(ServerContract.getGalleryImagesPath() + album_number + "/" + Gallery.getAlbum_map().get(album_number).get(position).getImageLink()).placeholder(R.drawable.loading_image).into(gdv_imageView);
            }
        });

        container.addView(rootView);

        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
