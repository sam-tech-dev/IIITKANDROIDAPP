package com.ssverma.iiitkota;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class Academic_Calendar extends AppCompatActivity implements RCVClickListener{

    private RecyclerView recyclerView;
    private Academic_Calendar_Adapter adapter;
    private ArrayList<Academic_CalendarWrapper> list;

    private ProgressBar progressBar;
    private LinearLayout loadingHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loadingHolder = (LinearLayout) findViewById(R.id.loading_holder);


        if (ServerConnection.isNetworkAvailable(this)){
            try {new ServerAsync().execute(ServerContract.getAcademicCalendarPhpUrl() , "filter=" + URLEncoder.encode("prev", "UTF-8"));
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRCVClick(View view, int position) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = ServerContract.getAcademicCalendar_URL() + list.get(position).getLink();
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
            adapter = new Academic_Calendar_Adapter(Academic_Calendar.this, list);
            recyclerView.setAdapter(adapter);;
            adapter.setOnRCVClickListener(Academic_Calendar.this);

        }

        private ArrayList<Academic_CalendarWrapper> parseJSON(String response) {
            list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    Academic_CalendarWrapper academic_calendarWrapper = new Academic_CalendarWrapper();

                    academic_calendarWrapper.setName(jsonObject.getString("name"));;
                    academic_calendarWrapper.setLink(jsonObject.getString("link"));
                    list.add(academic_calendarWrapper);
                }
            } catch (JSONException e) {

            }

            return list;

        }
    }

}
