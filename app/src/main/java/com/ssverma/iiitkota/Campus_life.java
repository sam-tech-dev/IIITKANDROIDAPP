package com.ssverma.iiitkota;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.utils.Consts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Campus_life  extends AppCompatActivity implements RCVClickListener {

    private KenBurnsView kenBurnsView;
    private int[] ken_burns_bg = {R.drawable.campus1, R.drawable.campus2, R.drawable.campus3};

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Campus_life_adapter adapter;
    ArrayList<Campus_life_wrapper> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_life);

        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        // mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        // mViewPager = (ViewPager) findViewById(R.id.container);
        // mViewPager.setAdapter(mSectionsPagerAdapter);

        //tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //  tabLayout.setupWithViewPager(mViewPager);

        kenBurnsView = (KenBurnsView) findViewById(R.id.image_Ken_Burns);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new ServerAsync().execute();

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



        Intent intent = new Intent(this , Campus_life_DetialedView.class);
        intent.putExtra("Title" , list.get(position).getCampus_tittle());

        intent.putExtra("image_link" , list.get(position).getCampus_imageLink());
        intent.putExtra("Description", list.get(position).getCampus_description());
       // Toast.makeText(getApplicationContext(),"" + list.get(position).getCampus_description(),Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    public class ServerAsync extends AsyncTask<String[] , Void , ArrayList<Campus_life_wrapper>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog = ProgressDialog.show(getActivity(), "Please Wait",null, true, true);
        }

        @Override
        protected ArrayList<Campus_life_wrapper>  doInBackground(String[]... params) {
            return fetchDatabaseList_Campus();
        }

        @Override
        protected void onPostExecute(ArrayList<Campus_life_wrapper> result) {
            super.onPostExecute(result);
            //progressDialog.dismiss();

           // Toast.makeText(Campus_life.this , "" + result.size() , Toast.LENGTH_SHORT).show();

            list = result;

            adapter = new Campus_life_adapter(Campus_life.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Campus_life.this);

            //swipeRefreshLayout.setRefreshing(false);
            //progressBar.setVisibility(View.GONE);

            // Toast.makeText(getActivity() , "Size : " + list.size() , Toast.LENGTH_SHORT).show();

        }

        private ArrayList<Campus_life_wrapper> fetchDatabaseList_Campus() {
            ArrayList<Campus_life_wrapper> list = new ArrayList<>();

            Cursor cursor = Campus_life.this.getContentResolver().query(DatabaseContract.CAMPUS_CONTENT_URI,
                    null, null,//
                    null, null);








          //  try {
           //     JSONArray jsonArray = new JSONArray(response);
            //    for (int i = 0; i < jsonArray.length(); i++) {
              //      JSONObject jsonObject = jsonArray.getJSONObject(i);


                //    Campus_life_wrapper campus = new Campus_life_wrapper();
                    //Toast.makeText(getContext(),"jhjhhf",Toast.LENGTH_SHORT).show();
                  //  campus.setCampus_tittle(jsonObject.getString("Title"));

                  //  campus.setCampus_description(jsonObject.getString("Description"));

                  //  campus.setCampus_imageLink(jsonObject.getString("image_link"));

                   // list.add(campus);
               // }
           // } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
           // }


            //tv.setText(list.get(0).getS_name());
          //  return list;



            while (cursor.moveToNext()) {
                Campus_life_wrapper news = new Campus_life_wrapper();

                news.setCampus_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_SERVER_ID)));

                news.setCampus_tittle(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_TITLE)));
                // news.setNews_date(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));

              //  Date date= null;
              //  try {
               //     date = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));
               // } catch (ParseException e) {
                //    e.printStackTrace();
                //}

                news.setCampus_description(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_DETAIL)));
                news.setCampus_imageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_IMAGE)));

                list.add(news);

            }

            return list;
        }
    }
}
