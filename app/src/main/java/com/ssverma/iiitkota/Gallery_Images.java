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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class Gallery_Images extends AppCompatActivity implements RCVClickListener {

    private RecyclerView recyclerView;
    private Gallery_Images_Adapter adapter;
    private StaggeredGridLayoutManager layoutManager;

    private int album_number;

    private boolean isGrid = true;

    private boolean isAlbumInfoClicked;

    private String album_name;
    private String album_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery__images);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        album_number = getIntent().getExtras().getInt("album_number");

        recyclerView = (RecyclerView) findViewById(R.id.gallery_images_recycler_view);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Gallery_Images_Adapter(Gallery_Images.this, Gallery.getAlbum_map(), album_number);
        recyclerView.setAdapter(adapter);
        adapter.setRCVClickListener(this);

        album_name = getIntent().getExtras().getString("album_name");
        album_summary = getIntent().getExtras().getString("album_summary");

        getSupportActionBar().setTitle(album_name);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isGrid) {
                    layoutManager.setSpanCount(3);
                    isGrid = false;
                    fab.setImageResource(R.drawable.linear_icon);
                } else {
                    layoutManager.setSpanCount(2);
                    isGrid = true;
                    fab.setImageResource(R.drawable.grid_icon);
                }


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
            view.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            view.setVisibility(View.VISIBLE);
        }

    }

    public void hideDetails(final View view) {
        int cx = view.getRight() - 30;
        int cy = view.getBottom() - 60;
        int initialRadius = view.getWidth();
        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();
        } else {
            view.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void onRCVClick(View view, int position) {
        Intent intent = new Intent(Gallery_Images.this, Gallery_Detailed_View.class);
        intent.putExtra("cip", position);
        intent.putExtra("album_number", album_number);

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_gallery__images, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.album_info) {

            WebView album_summary_WV;

            if (!isAlbumInfoClicked) {
                isAlbumInfoClicked = true;

                TextView album_title = (TextView) findViewById(R.id.album_title);
                album_title.setText("");
                //Toast.makeText(getApplicationContext() , "Clicked" , Toast.LENGTH_SHORT).show();

                revealDetails(findViewById(R.id.album_details_holder));

                album_title.setText(album_name);

                album_summary_WV = (WebView) findViewById(R.id.album_summary);
                String webView_string = "<html><head>"
                        + "<style type=\"text/css\">body{color: #ffffff; background-color: #99444444;}"
                        + "</style></head>"
                        + "<body>"
                        + album_summary
                        + "</body></html>";

                album_summary_WV.loadData(webView_string, "text/html", "utf-8");

                album_summary_WV.setBackgroundColor(99444444);
                //getSupportActionBar().setTitle("");

                recyclerView.setVisibility(View.INVISIBLE);

            } else {
                hideDetails(findViewById(R.id.album_details_holder));
                recyclerView.setVisibility(View.VISIBLE);
                isAlbumInfoClicked = false;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
