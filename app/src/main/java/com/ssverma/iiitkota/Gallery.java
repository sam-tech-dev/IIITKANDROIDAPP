package com.ssverma.iiitkota;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Gallery extends AppCompatActivity implements RCVClickListener{

    private RecyclerView recyclerView;
    private Gallery_Album_Adapter adapter;
    private StaggeredGridLayoutManager layoutManager;

    private static HashMap<Integer , ArrayList<Gallery_Images_Wrapper>> album_map;
    private final int GALLERY_ALBUM_RETRIEVAL = 1;      // Use same AsyncTask for multiple network operations
    private final int GALLERY_IMAGES_RETRIEVAL = 2;
    private ArrayList<Gallery_Album_Wrapper> album_list;

    private ProgressBar progressBar;
    private LinearLayout loadingHolder;

    private boolean isGrid = true;

    public static HashMap<Integer, ArrayList<Gallery_Images_Wrapper>> getAlbum_map() {
        return album_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.gallery_recycler_view);
        layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loadingHolder = (LinearLayout) findViewById(R.id.loading_holder);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isGrid){
                    layoutManager.setSpanCount(1);
                    isGrid = false;
                    fab.setImageResource(R.drawable.linear_icon);
                }else {
                    layoutManager.setSpanCount(2);
                    isGrid = true;
                    fab.setImageResource(R.drawable.grid_icon);
                }
            }
        });


        if (ServerConnection.isNetworkAvailable(this)){
            new ServerGalleryAsync(GALLERY_IMAGES_RETRIEVAL).execute(ServerContract.getGalleryPhpUrl());  //Images
            new ServerGalleryAsync(GALLERY_ALBUM_RETRIEVAL).execute(ServerContract.getGalleryAlbumPhpUrl());   //Albums

        }else {
            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            Toast.makeText(this , "No Internet Connection" , Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onRCVClick(View view, int position) {

        //Toast.makeText(Gallery.this , position + " : mapIIITK Size" , Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Gallery.this , Gallery_Images.class);
        intent.putExtra("album_number"  , album_list.get(position).getAlbum_number());
        intent.putExtra("album_name"    , album_list.get(position).getAlbum_title());
        intent.putExtra("album_summary" , album_list.get(position).getAlbum_summary());

        startActivity(intent);
    }


    class ServerGalleryAsync extends AsyncTask<String , Void , String>{

        private final int ASYNC_TASK_CODE;

        ServerGalleryAsync(int ASYNC_TASK_CODE){
            this.ASYNC_TASK_CODE = ASYNC_TASK_CODE;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("param",params[0]);
            return ServerConnection.obtainServerResponse(params[0] );
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            if (ASYNC_TASK_CODE == GALLERY_IMAGES_RETRIEVAL) {
                ArrayList<Gallery_Images_Wrapper> list = parseJSONGallery_Images(response);

                //Toast.makeText(Gallery.this , list.size() + "" , Toast.LENGTH_SHORT).show();
                ArrayList<Integer> unique_Albums = getUniqueAlbums(list); // Unique Album Number
                //Toast.makeText(Gallery.this , unique_Albums.size() + "" , Toast.LENGTH_SHORT).show();
                album_map = createAlbumMap(unique_Albums, list);
                //Toast.makeText(Gallery.this , album_map.size() + " : Map Size" , Toast.LENGTH_SHORT).show();

                //recyclerView.setAdapter(new Gallery_Album_Adapter(Gallery.this, unique_Albums, album_map));

            } else if (ASYNC_TASK_CODE == GALLERY_ALBUM_RETRIEVAL){
                album_list = parseJSONGallery_Albums(response);

                //Toast.makeText(Gallery.this , album_list.get(1).getAlbum_number() + " : Map Size" , Toast.LENGTH_SHORT).show();

                Gallery_Album_Adapter adapter = new Gallery_Album_Adapter(Gallery.this , album_list , album_map);
                recyclerView.setAdapter(adapter);

                adapter.setOnRCVClickListener(Gallery.this);
            }
        }



        private HashMap<Integer,ArrayList<Gallery_Images_Wrapper>> createAlbumMap(ArrayList<Integer> unique_album_number_list , ArrayList<Gallery_Images_Wrapper> album_list) {
            HashMap<Integer , ArrayList<Gallery_Images_Wrapper>> album_map = new HashMap<>();

            for (int i=0;i<unique_album_number_list.size();i++){
                ArrayList<Gallery_Images_Wrapper> list = new ArrayList<>();
                for (int j=0;j<album_list.size();j++){
                    if (album_list.get(j).getAlbum_number() == unique_album_number_list.get(i)){
                        list.add(album_list.get(j)); // Adding object of same album number
                    }
                }

                //Toast.makeText(Gallery.this , list.size() + " : Map Size" , Toast.LENGTH_SHORT).show();
                album_map.put(unique_album_number_list.get(i) , list);
            }

            return album_map;
        }

        private ArrayList<Integer> getUniqueAlbums(ArrayList<Gallery_Images_Wrapper> list) {
            ArrayList<Integer> unique_album_list = new ArrayList<>();

            for (int i=0;i<list.size();i++){
                if (!unique_album_list.contains(list.get(i).getAlbum_number())){
                    unique_album_list.add(list.get(i).getAlbum_number());
                }
            }

            return unique_album_list;
        }

        private ArrayList<Gallery_Album_Wrapper> parseJSONGallery_Albums(String response) {
            ArrayList<Gallery_Album_Wrapper> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gallery_Album_Wrapper gallery_album = new Gallery_Album_Wrapper();

                    //gallery_album.setAlbum_name(jsonObject.getString("Name"));
                    gallery_album.setAlbum_number(jsonObject.getInt("id"));
                    gallery_album.setAlbum_date(jsonObject.getString("Date"));
                    gallery_album.setAlbum_summary(jsonObject.getString("Summary"));
                    gallery_album.setAlbum_thumbnail(jsonObject.getString("Thumbnail"));
                    gallery_album.setAlbum_title(jsonObject.getString("Title"));

                    list.add(gallery_album);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            //tv.setText(list.get(0).getS_name());
            return list;
        }

        private ArrayList<Gallery_Images_Wrapper> parseJSONGallery_Images(String response) {
            ArrayList<Gallery_Images_Wrapper> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gallery_Images_Wrapper gallery_album = new Gallery_Images_Wrapper();

                    //gallery_album.setAlbum_name(jsonObject.getString("Name"));
                    gallery_album.setImageLink(jsonObject.getString("Image"));
                    gallery_album.setAlbum_number(jsonObject.getInt("Album"));

                    list.add(gallery_album);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            //tv.setText(list.get(0).getS_name());
            return list;
        }
    }

}
