package com.ssverma.iiitkota;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class Gallery_Images extends AppCompatActivity implements RCVClickListener{

    private RecyclerView recyclerView;
    private Gallery_Images_Adapter adapter;
    private StaggeredGridLayoutManager layoutManager;

    private int album_number;

    private boolean isGrid = true;
    private boolean isLongClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery__images);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        album_number = getIntent().getExtras().getInt("album_number");

        recyclerView = (RecyclerView) findViewById(R.id.gallery_images_recycler_view);
        layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Gallery_Images_Adapter(Gallery_Images.this , Gallery.getAlbum_map() , album_number);
        recyclerView.setAdapter(adapter);
        adapter.setRCVClickListener(this);

        final String album_name = getIntent().getExtras().getString("album_name");
        final String album_summary = getIntent().getExtras().getString("album_summary");

        getSupportActionBar().setTitle(album_name);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isLongClicked){
                    isLongClicked = false;
                    hideDetails(findViewById(R.id.album_details_holder));
                    recyclerView.setVisibility(View.VISIBLE);
                }else {

                    if (isGrid) {
                        layoutManager.setSpanCount(1);
                        isGrid = false;
                        fab.setImageResource(R.drawable.linear_icon);
                    } else {
                        layoutManager.setSpanCount(2);
                        isGrid = true;
                        fab.setImageResource(R.drawable.grid_icon);
                    }
                }


            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextView album_title = (TextView) findViewById(R.id.album_title);
                album_title.setText("");
                //Toast.makeText(getApplicationContext() , "Clicked" , Toast.LENGTH_SHORT).show();

                revealDetails(findViewById(R.id.album_details_holder));
                isLongClicked = true;

                album_title.setText(album_name);

                WebView album_summary_WV = (WebView) findViewById(R.id.album_summary);
                String webView_string = "<html><head>"
                        + "<style type=\"text/css\">body{color: #ffffff; background-color: #99444444;}"
                        + "</style></head>"
                        + "<body>"
                        + album_summary
                        + "</body></html>";

                album_summary_WV.loadData(webView_string , "text/html" , "utf-8");

                album_summary_WV.setBackgroundColor(99444444);
                //getSupportActionBar().setTitle("");

                recyclerView.setVisibility(View.INVISIBLE);

                return true;
            }
        });

    }

    private void revealDetails(View view) {
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        }
        view.setVisibility(View.VISIBLE);
        //isEditTextVisible = true;
        anim.start();
    }

    public void hideDetails(final View view){
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int initialRadius = view.getWidth();
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        }
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        //isEditTextVisible = false;
        anim.start();

    }

    @Override
    public void onRCVClick(View view, int position) {
        Intent intent = new Intent(Gallery_Images.this , Gallery_Detailed_View.class);
        intent.putExtra("cip" , position);
        intent.putExtra("album_number" , album_number);

        startActivity(intent);
    }
}
