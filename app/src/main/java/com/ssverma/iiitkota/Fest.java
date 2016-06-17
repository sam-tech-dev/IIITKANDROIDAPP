package com.ssverma.iiitkota;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.flaviofaria.kenburnsview.KenBurnsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fest extends AppCompatActivity implements RCVClickListener {


    private KenBurnsView kenBurnsView;
    private int[] ken_burns_bg = {R.drawable.campus1, R.drawable.campus2, R.drawable.campus3};

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Fest_Adapter adapter;
    ArrayList<FestWrapper> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fest);

        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Fests");


        kenBurnsView = (KenBurnsView) findViewById(R.id.image_Ken_Burns);

        recyclerView = (RecyclerView) findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //   new ServerAsync().execute();

        new ServerAsync().execute(ServerContract.getFestPhpUrl());
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


    public void onRCVClick(View view, int position) {
        // start new Activity here



        Intent intent = new Intent(this , Fest_DetailedView.class);
        intent.putExtra("Name" , list.get(position).getFest_name());

       intent.putExtra("ImageLink" , list.get(position).getFest_image_link());
        intent.putExtra("Description", list.get(position).getFest_description());
      //  Toast.makeText(getApplicationContext(),"" + list.get(position).getFest_description(), Toast.LENGTH_SHORT).show();
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
            return ServerConnection.obtainServerResponse(params[0]);
          //  return ServerConnection.obtainServerResponse(params[0], params[1]);

        }



        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            //progressDialog.dismiss();

            // Toast.makeText(Fest.this , "" + result.size() , Toast.LENGTH_SHORT).show();


            list = parseJSON(response);

            adapter = new Fest_Adapter(Fest.this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnRCVClickListener(Fest.this);

            //swipeRefreshLayout.setRefreshing(false);
            //progressBar.setVisibility(View.GONE);

            // Toast.makeText(getActivity() , "Size : " + list.size() , Toast.LENGTH_SHORT).show();

        }

        private ArrayList<FestWrapper> parseJSON(String response) {
         //   ArrayList<FestWrapper> list = new ArrayList<>();
            list = new ArrayList<>();

//            Cursor cursor = Fest.this.getContentResolver().query(DatabaseContract.FEST_CONTENT_URI,
//                    null, null,//
//                    null, null);

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    FestWrapper festWrapper = new FestWrapper();

                    //Toast.makeText(getContext(),"jhjhhf",Toast.LENGTH_SHORT).show();
                    festWrapper.setFest_name(jsonObject.getString("name"));
          //          festWrapper.setFest_date(jsonObject.getString("date"));
                    festWrapper.setFest_description(jsonObject.getString("description"));
                    festWrapper.setFest_image_link(jsonObject.getString("image_link"));

//                    scholarshipWrapper.setIncome(jsonObject.getString("criteria_total_parental_income_rs"));
//                    scholarshipWrapper.setAcademic(jsonObject.getString("criteria_academic"));
//                    scholarshipWrapper.setCategory(jsonObject.getString("criteria_category"));
//                    scholarshipWrapper.setValue(jsonObject.getString("value"));
//                    scholarshipWrapper.setProcedure(jsonObject.getString("procedure_for_application"));
//                    scholarshipWrapper.setRemark(jsonObject.getString("remark"));
//                    scholarshipWrapper.setLink(jsonObject.getString("link"));
//                    scholarshipWrapper.setImage(jsonObject.getString("image"));
                    list.add(festWrapper);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }





//            while (cursor.moveToNext()) {
//                FestWrapper news = new FestWrapper();
//
//                news.setFest_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.FestTable.FEST_SERVER_ID)));
//
//                news.setFest_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.FestTable.FEST_NAME)));
//                // news.setNews_date(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));
//
//                //  Date date= null;
//                //  try {
//                //     date = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));
//                // } catch (ParseException e) {
//                //    e.printStackTrace();
//                //}
//                news.setFest_date(cursor.getString(cursor.getColumnIndex(DatabaseContract.FestTable.FEST_DATE)));
//
//                news.setFest_description(cursor.getString(cursor.getColumnIndex(DatabaseContract.FestTable.FEST_DESCRIPTION)));
//           //     news.setCampus_imageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.CampusTable.CAMPUS_IMAGE)));
//
//                list.add(news);
//
//            }

            return list;
        }
    }


}

