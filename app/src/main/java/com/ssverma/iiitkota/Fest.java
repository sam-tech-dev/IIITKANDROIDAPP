package com.ssverma.iiitkota;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fest extends AppCompatActivity implements RCVClickListener {


    private KenBurnsView kenBurnsView;

    private RecyclerView recyclerView;
    private Fest_Adapter adapter;
    private ArrayList<FestWrapper> list;

    private ProgressBar progressBar;
    private LinearLayout loadingHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kenBurnsView = (KenBurnsView) findViewById(R.id.image_Ken_Burns);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loadingHolder = (LinearLayout) findViewById(R.id.loading_holder);



        if (ServerConnection.isNetworkAvailable(this)){
            new ServerAsync().execute(ServerContract.getFestPhpUrl());
        }else {
            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            Toast.makeText(this , "No Internet Connection" , Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onRCVClick(View view, int position) {

        Intent intent = new Intent(this, Fest_DetailedView.class);
        intent.putExtra("Name", list.get(position).getFest_name());

        intent.putExtra("ImageLink", list.get(position).getFest_image_link());
        intent.putExtra("Description", list.get(position).getFest_description());
        startActivity(intent);
    }


    public class ServerAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            list = parseJSON(response);

            adapter = new Fest_Adapter(Fest.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Fest.this);

        }

        private ArrayList<FestWrapper> parseJSON(String response) {

            list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    FestWrapper festWrapper = new FestWrapper();

                    festWrapper.setFest_name(jsonObject.getString("name"));
                    festWrapper.setFest_description(jsonObject.getString("description"));
                    festWrapper.setFest_image_link(jsonObject.getString("image_link"));

                    list.add(festWrapper);
                }
            } catch (JSONException e) {

            }


            return list;
        }
    }


}

