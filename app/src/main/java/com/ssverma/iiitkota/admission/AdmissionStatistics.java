package com.ssverma.iiitkota.admission;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import com.ssverma.iiitkota.R;
import com.ssverma.iiitkota.ServerConnection;
import com.ssverma.iiitkota.R;
import com.ssverma.iiitkota.ServerContract;

public class AdmissionStatistics extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Admission_Statistics_Adapter adapter;
    private ProgressBar progressBar;

    private ArrayList<String> branch_list;

    private RelativeLayout no_internet_conn;
    private LinearLayout terms_holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(false);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_as);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        no_internet_conn = (RelativeLayout) findViewById(R.id.no_internet_connection_layout);
        terms_holder = (LinearLayout) findViewById(R.id.terms_holder);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setImageResource(R.drawable.copyright_symbol);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "IIITK all rights reserved", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        if (ServerConnection.isNetworkAvailable(this)){
            new ServerAsync().execute(ServerContract.getAdmissionStatisticsUrl());
        }else {
            progressBar.setVisibility(View.GONE);
            no_internet_conn.setVisibility(View.VISIBLE);
            //fab.setVisibility(View.INVISIBLE);
            findViewById(R.id.terms_holder).setVisibility(View.INVISIBLE);
            terms_holder.setVisibility(View.INVISIBLE);
            appBarLayout.setExpanded(false);
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


    public class ServerAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            progressBar.setVisibility(View.GONE);
            terms_holder.setVisibility(View.VISIBLE);

            ArrayList<AdmissionStatisticsWrapper> rank_list = parseASJSON(response);
            HashMap<String , ArrayList<AdmissionStatisticsWrapper>> rank_map = createMap(rank_list);

            //Toast.makeText(FAQs.this , "" + response , Toast.LENGTH_LONG).show();

            adapter = new Admission_Statistics_Adapter(branch_list , rank_map );
            recyclerView.setAdapter(adapter);

        }

        private HashMap<String,ArrayList<AdmissionStatisticsWrapper>> createMap(ArrayList<AdmissionStatisticsWrapper> rank_list) {
            HashMap<String,ArrayList<AdmissionStatisticsWrapper>> map = new HashMap<>();

            for (String branch : branch_list){
                ArrayList<AdmissionStatisticsWrapper> refined_list = new ArrayList<>();
                for (AdmissionStatisticsWrapper data : rank_list){

                    if (branch.equals(data.getBranch())){
                        refined_list.add(data);
                    }
                }

                map.put(branch , refined_list);
            }

            return map;
        }

        private ArrayList<AdmissionStatisticsWrapper> parseASJSON(String response) {

            ArrayList<AdmissionStatisticsWrapper> list = new ArrayList<>();
            branch_list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    AdmissionStatisticsWrapper statisticsWrapper = new AdmissionStatisticsWrapper();

                    statisticsWrapper.setBranch(jsonObject.getString("Branch"));
                    statisticsWrapper.setCategory(jsonObject.getString("Category"));
                    statisticsWrapper.setSession(jsonObject.getString("Session"));
                    statisticsWrapper.setStartingRank(jsonObject.getString("Starting_rank"));
                    statisticsWrapper.setClosingRank(jsonObject.getString("Closing_rank"));

                    if (!branch_list.contains(jsonObject.getString("Branch")))
                    branch_list.add(jsonObject.getString("Branch"));

                    list.add(statisticsWrapper);

                }
            } catch (JSONException e) {

            }

            return list;

        }
    }

}
