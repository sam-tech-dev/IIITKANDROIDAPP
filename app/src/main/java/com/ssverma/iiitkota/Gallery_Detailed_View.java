package com.ssverma.iiitkota;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Gallery_Detailed_View extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery__derailed__view);

        int clicked_image_pos = getIntent().getExtras().getInt("cip");
        int album_number = getIntent().getExtras().getInt("album_number");

        viewPager = (ViewPager) findViewById(R.id.gallery_detailed_view_viewpager);

        GalleryDetailedView_Adapter adapter = new GalleryDetailedView_Adapter(Gallery_Detailed_View.this , album_number);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(clicked_image_pos);

    }
}
