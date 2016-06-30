package com.ssverma.iiitkota.admission;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.ssverma.iiitkota.R;
import com.ssverma.iiitkota.ServerConnection;
import com.ssverma.iiitkota.ServerContract;

public class FAQs extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableList_Adapter adapter;
    private ProgressBar progressBar;

    private ArrayList<String> ques_list;
    private ArrayList<String> ans_list;

    private ImageView no_internet_conn_icon;
    private TextView no_internet_conn_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        no_internet_conn_icon = (ImageView) findViewById(R.id.no_internet_connection_icon);
        no_internet_conn_text = (TextView) findViewById(R.id.no_internet_connection_text);


        ques_list = new ArrayList<>();
        ans_list = new ArrayList<>();



        if (ServerConnection.isNetworkAvailable(this)){
            new ServerAsync().execute(ServerContract.getFaqUrl());
        }else {
            progressBar.setVisibility(View.GONE);
            no_internet_conn_text.setVisibility(View.VISIBLE);
            no_internet_conn_icon.setVisibility(View.VISIBLE);
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


    public class ServerAsync extends AsyncTask<String , Void , String> {

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            progressBar.setVisibility(View.GONE);

            parseFAQsJSON(response);

            adapter = new ExpandableList_Adapter(FAQs.this , ques_list , ans_list);
            expandableListView.setAdapter(adapter);

        }

        private void parseFAQsJSON(String response) {

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ques_list.add(jsonObject.getString("Question"));
                    ans_list.add(jsonObject.getString("Answer"));

                }
            } catch (JSONException e) {

            }

        }
    }
}
