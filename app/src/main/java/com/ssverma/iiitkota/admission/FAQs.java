package com.ssverma.iiitkota.admission;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.ssverma.iiitkota.R;
import com.ssverma.iiitkota.ServerConnection;

public class FAQs extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ExpandableList_Adapter adapter;
    private ProgressBar progressBar;

    private ArrayList<String> ques_list;
    private ArrayList<String> ans_list;

    private static String SERVER_URL = "http://172.16.1.231/iiitk/android";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = (ExpandableListView) findViewById(R.id.expandable_list);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);


        ques_list = new ArrayList<>();
        ans_list = new ArrayList<>();

        new ServerAsync().execute(SERVER_URL + "/faq.php");

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

            //Toast.makeText(FAQs.this , "" + response , Toast.LENGTH_LONG).show();

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
