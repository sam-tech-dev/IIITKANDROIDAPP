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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Author-Dixit Chauhan      :08/06/2016
 */

public class Scholarship  extends AppCompatActivity implements RCVClickListener {

   // private KenBurnsView kenBurnsView;
   // private int[] ken_burns_bg = {R.drawable.newsfeed_prev, R.drawable.newsfeed_latest, R.drawable.newsfeed_upcoming};

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ScholarshipAdapter adapter;
    ArrayList<ScholarshipWrapper> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);

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

       // kenBurnsView = (KenBurnsView) findViewById(R.id.image_Ken_Burns);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {new ServerAsync().execute((String[][]) null);
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



        Intent intent = new Intent(this , ScholarshipDetailedView.class);
        intent.putExtra("name" , list.get(position).getName());
        intent.putExtra("income" , list.get(position).getIncome());
        intent.putExtra("academic" , list.get(position).getAcademic());
        intent.putExtra("category" , list.get(position).getCategory());
        intent.putExtra("value" , list.get(position).getValue());
        intent.putExtra("procedure" , list.get(position).getProcedure());
        intent.putExtra("remark" , list.get(position).getRemark());
        intent.putExtra("link" , list.get(position).getLink());
        intent.putExtra("faculty_image_link" , list.get(position).getImage());
       // intent.putExtra("tab_position" , Events.tab_position);

        startActivity(intent);
    }


    public class ServerAsync extends AsyncTask<String[] , Void , ArrayList<ScholarshipWrapper>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog = ProgressDialog.show(getActivity(), "Please Wait",null, true, true);
        }

        @Override
        protected ArrayList<ScholarshipWrapper> doInBackground(String[]... params) {
            return fetchDatabaseList_Scholarship(null);
        }

        @Override
        protected void onPostExecute(ArrayList<ScholarshipWrapper> result) {
            super.onPostExecute(result);
            //progressDialog.dismiss();

           // Toast.makeText(Scholarship.this , "" + response , Toast.LENGTH_SHORT).show();

            list = result;
            adapter = new ScholarshipAdapter(Scholarship.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Scholarship.this);

            //swipeRefreshLayout.setRefreshing(false);
            //progressBar.setVisibility(View.GONE);

            // Toast.makeText(getActivity() , "Size : " + list.size() , Toast.LENGTH_SHORT).show();

        }

        private ArrayList<ScholarshipWrapper> fetchDatabaseList_Scholarship(String[] selectionArgs) {
            ArrayList<ScholarshipWrapper> list = new ArrayList<>();

            Cursor cursor = getContentResolver().query(DatabaseContract.SCHOLARSHIP_CONTENT_URI,
                    null, null ,//
                    selectionArgs, null);

            while (cursor.moveToNext()) {
                ScholarshipWrapper scholarshipWrapper = new ScholarshipWrapper();

                scholarshipWrapper.setScholarship_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_SERVER_ID)));

                scholarshipWrapper.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_NAME)));
                scholarshipWrapper.setIncome(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_INCOME)));
                scholarshipWrapper.setAcademic(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_ACADEMIC)));
                scholarshipWrapper.setCategory(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_CATEGORY)));
                scholarshipWrapper.setValue(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_VALUE)));
                scholarshipWrapper.setProcedure(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_PROCEDURE)));
                scholarshipWrapper.setRemark(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_REMARK)));
                scholarshipWrapper.setLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_LINK)));
                scholarshipWrapper.setImage(cursor.getString(cursor.getColumnIndex(DatabaseContract.ScholarshipTable.SCHOLARSHIP_IMAGE)));

                list.add(scholarshipWrapper);

            }

            return list;
        }
    }

}
