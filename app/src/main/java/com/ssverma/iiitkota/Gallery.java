package com.ssverma.iiitkota;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Gallery extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Gallery_Album_Adapter adapter;
    private static HashMap<Integer , ArrayList<Gallery_Album_Wrapper>> album_map;
    private final int GALLERY_ALBUM_RETRIEVAL = 1;      // Use same AsyncTask for multiple network operations
    private final int GALLERY_IMAGES_RETRIEVAL = 2;

    public static HashMap<Integer, ArrayList<Gallery_Album_Wrapper>> getAlbum_map() {
        return album_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.gallery_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(Gallery.this , 2));

        performServerOperation();

    }

    private void performServerOperation() {
        String url = ServerContract.getGalleryPhpUrl();

        //new ServerGalleryAsync(GALLERY_ALBUM_RETRIEVAL).execute(url);   //Albums
        //new ServerGalleryAsync(GALLERY_IMAGES_RETRIEVAL).execute(url);  //Images
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faculty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ServerGalleryAsync extends AsyncTask<String , Void , String>{

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0] );
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<Gallery_Album_Wrapper> list = parseJSON(response);

            //Toast.makeText(Gallery.this , list.size() + "" , Toast.LENGTH_SHORT).show();
            ArrayList<Integer> unique_Albums = getUniqueAlbums(list); // Unique Album Number
            //Toast.makeText(Gallery.this , unique_Albums.size() + "" , Toast.LENGTH_SHORT).show();
            album_map = createAlbumMap(unique_Albums , list);
            //Toast.makeText(Gallery.this , album_map.get(17).get(5).getAlbum_thumbnail_link() + " : Map Size" , Toast.LENGTH_SHORT).show();

            recyclerView.setAdapter(new Gallery_Album_Adapter(Gallery.this , unique_Albums , album_map));
        }

        private HashMap<Integer,ArrayList<Gallery_Album_Wrapper>> createAlbumMap(ArrayList<Integer> unique_album_number_list , ArrayList<Gallery_Album_Wrapper> album_list) {
            HashMap<Integer , ArrayList<Gallery_Album_Wrapper>> album_map = new HashMap<>();

            for (int i=0;i<unique_album_number_list.size();i++){
                ArrayList<Gallery_Album_Wrapper> list = new ArrayList<>();
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

        private ArrayList<Integer> getUniqueAlbums(ArrayList<Gallery_Album_Wrapper> list) {
            ArrayList<Integer> unique_album_list = new ArrayList<>();

            for (int i=0;i<list.size();i++){
                if (!unique_album_list.contains(list.get(i).getAlbum_number())){
                    unique_album_list.add(list.get(i).getAlbum_number());
                }
            }

            return unique_album_list;
        }

        private ArrayList<Gallery_Album_Wrapper> parseJSON(String response) {
            ArrayList<Gallery_Album_Wrapper> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gallery_Album_Wrapper gallery_album = new Gallery_Album_Wrapper();

                    //gallery_album.setAlbum_name(jsonObject.getString("Name"));
                    gallery_album.setAlbum_thumbnail_link(jsonObject.getString("Image"));
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
