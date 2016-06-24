package com.ssverma.iiitkota;

import android.content.Intent;
import android.net.Uri;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;


public class Academic_Timetable extends AppCompatActivity implements RCVClickListener{

    private RecyclerView recyclerView;
    private Academic_Timetable_Adapter adapter;
    private ArrayList<Academic_TimetableWrapper> list;

    private ProgressBar progressBar;
    private LinearLayout loadingHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_timetable);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loadingHolder = (LinearLayout) findViewById(R.id.loading_holder);


        if (ServerConnection.isNetworkAvailable(this)){
            try {new ServerAsync().execute(ServerContract.getAcademicTimetablePhpUrl(), "filter=" + URLEncoder.encode("prev", "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            Toast.makeText(this , "No Internet Connection" , Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRCVClick(View view, int position) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ServerContract.getAcademicTimetableDocs() + list.get(position).getLink();
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public class ServerAsync extends AsyncTask<String , Void , String> {

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);


            list = parseJSON(response);
            adapter = new Academic_Timetable_Adapter(Academic_Timetable.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Academic_Timetable.this);

        }

        private ArrayList<Academic_TimetableWrapper> parseJSON(String response) {
            ArrayList<Academic_TimetableWrapper> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Academic_TimetableWrapper academic_timetableWrapper = new Academic_TimetableWrapper();

                    academic_timetableWrapper.setName(jsonObject.getString("name"));;
                    academic_timetableWrapper.setLink(jsonObject.getString("link"));
                    list.add(academic_timetableWrapper);
                }
            } catch (JSONException e) {

            }

            return list;

        }
    }

}
