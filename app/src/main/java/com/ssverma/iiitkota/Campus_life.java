package com.ssverma.iiitkota;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ssverma.iiitkota.sync_adapter.DatabaseContract;

import java.util.ArrayList;


public class Campus_life extends AppCompatActivity implements RCVClickListener {

    private RecyclerView recyclerView;
    private Campus_life_adapter adapter;
    private ArrayList<Campus_life_wrapper> list;

    private ProgressBar progressBar;
    private LinearLayout loadingHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_life);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loadingHolder = (LinearLayout) findViewById(R.id.loading_holder);

        new ServerAsync().execute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRCVClick(View view, int position) {

        Intent intent = new Intent(this, Campus_life_DetialedView.class);
        intent.putExtra("Title", list.get(position).getCampus_tittle());

        intent.putExtra("image_link", list.get(position).getCampus_imageLink());
        intent.putExtra("Description", list.get(position).getCampus_description());

        startActivity(intent);
    }


    public class ServerAsync extends AsyncTask<String[], Void, ArrayList<Campus_life_wrapper>> {

        @Override
        protected ArrayList<Campus_life_wrapper> doInBackground(String[]... params) {
            return fetchDatabaseList_Campus();
        }

        @Override
        protected void onPostExecute(ArrayList<Campus_life_wrapper> result) {
            super.onPostExecute(result);

            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            list = result;

            adapter = new Campus_life_adapter(Campus_life.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Campus_life.this);

        }

        private ArrayList<Campus_life_wrapper> fetchDatabaseList_Campus() {
            ArrayList<Campus_life_wrapper> list = new ArrayList<>();

            Cursor cursor = Campus_life.this.getContentResolver().query(DatabaseContract.CAMPUS_CONTENT_URI,
                    null, null,//
                    null, null);


            while (cursor.moveToNext()) {
                Campus_life_wrapper news = new Campus_life_wrapper();

                news.setCampus_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_SERVER_ID)));

                news.setCampus_tittle(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_TITLE)));

                news.setCampus_description(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_DETAIL)));
                news.setCampus_imageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_IMAGE)));

                list.add(news);

            }

            return list;
        }
    }
}
