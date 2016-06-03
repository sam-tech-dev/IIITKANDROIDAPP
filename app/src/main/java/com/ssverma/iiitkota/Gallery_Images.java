package com.ssverma.iiitkota;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Gallery_Images extends AppCompatActivity implements RCVClickListener{

    private RecyclerView recyclerView;
    private Gallery_Images_Adapter adapter;

    private int album_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery__images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        album_number = getIntent().getExtras().getInt("album_number");

        recyclerView = (RecyclerView) findViewById(R.id.gallery_images_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));
        adapter = new Gallery_Images_Adapter(Gallery_Images.this , Gallery.getAlbum_map() , album_number);
        recyclerView.setAdapter(adapter);

        adapter.setRCVClickListener(this);

        getSupportActionBar().setTitle("Album Name");

    }

    @Override
    public void onRCVClick(View view, int position) {
        Intent intent = new Intent(Gallery_Images.this , Gallery_Detailed_View.class);
        intent.putExtra("cip" , position);
        intent.putExtra("album_number" , album_number);

        startActivity(intent);
    }
}
