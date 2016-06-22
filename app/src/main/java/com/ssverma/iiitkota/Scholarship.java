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

import android.widget.LinearLayout;
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

public class Scholarship  extends AppCompatActivity implements RCVClickListener {

    private RecyclerView recyclerView;
    private ScholarshipAdapter adapter;
    private ArrayList<ScholarshipWrapper> list;

    private ProgressBar progressBar;
    private LinearLayout loadingHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);


        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loadingHolder = (LinearLayout) findViewById(R.id.loading_holder);

        try {new ServerAsync().execute((String[][]) null);
        } catch (Exception e) {
            e.printStackTrace();
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

        startActivity(intent);
    }


    public class ServerAsync extends AsyncTask<String[] , Void , ArrayList<ScholarshipWrapper>> {

        @Override
        protected ArrayList<ScholarshipWrapper> doInBackground(String[]... params) {
            return fetchDatabaseList_Scholarship(null);
        }

        @Override
        protected void onPostExecute(ArrayList<ScholarshipWrapper> result) {
            super.onPostExecute(result);

            progressBar.setVisibility(View.GONE);
            loadingHolder.setVisibility(View.GONE);

            list = result;
            adapter = new ScholarshipAdapter(Scholarship.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Scholarship.this);

        }

        private ArrayList<ScholarshipWrapper> fetchDatabaseList_Scholarship(String[] selectionArgs) {
            ArrayList<ScholarshipWrapper> list = new ArrayList<>();

            Cursor cursor = getContentResolver().query(DatabaseContract.SCHOLARSHIP_CONTENT_URI,
                    null, null ,
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
