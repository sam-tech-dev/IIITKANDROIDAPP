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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by DIXIT on 12/06/2016.
 */

public class Academic_Calendar extends AppCompatActivity implements RCVClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Academic_Calendar_Adapter adapter;
    ArrayList<Academic_CalendarWrapper> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {new ServerAsync().execute(ServerContract.getAcademicCalendarPhpUrl() , "filter=" + URLEncoder.encode("prev", "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    @Override
    public void onRCVClick(View view, int position) {
        // start new Activity here
        ;
        // intent.putExtra("tab_position" , Events.tab_position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String url = "http://172.16.1.231/iiitk/android/assets/documents/academic_calendar/" + list.get(position).getLink();
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public class ServerAsync extends AsyncTask<String , Void , String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog = ProgressDialog.show(getActivity(), "Please Wait",null, true, true);
        }

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //progressDialog.dismiss();

           // Toast.makeText(Scholarship.this , "" + response , Toast.LENGTH_SHORT).show();

            list = parseJSON(response);
            adapter = new Academic_Calendar_Adapter(Academic_Calendar.this, list);
            recyclerView.setAdapter(adapter);;
            adapter.setOnRCVClickListener(Academic_Calendar.this);

            //swipeRefreshLayout.setRefreshing(false);
            //progressBar.setVisibility(View.GONE);

            // Toast.makeText(getActivity() , "Size : " + list.size() , Toast.LENGTH_SHORT).show();

        }

        private ArrayList<Academic_CalendarWrapper> parseJSON(String response) {
            list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    Academic_CalendarWrapper academic_calendarWrapper = new Academic_CalendarWrapper();

                    //Toast.makeText(getContext(),"jhjhhf",Toast.LENGTH_SHORT).show();
                    academic_calendarWrapper.setName(jsonObject.getString("name"));;
                    academic_calendarWrapper.setLink(jsonObject.getString("link"));
                    list.add(academic_calendarWrapper);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            //tv.setText(list.get(0).getS_name());
            return list;

        }
    }

}
