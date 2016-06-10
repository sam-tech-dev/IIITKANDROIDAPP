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

public class Events extends AppCompatActivity{


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.event_latest, R.drawable.event_past , R.drawable.event_upcoming };
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        kenBurnsView = (KenBurnsView) findViewById(R.id.image_Ken_Burns);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(getApplicationContext() , "Page : " + position , Toast.LENGTH_SHORT).show();
                kenBurnsView.setImageResource(ken_burns_bg[position]);
                tab_position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements RCVClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private EventsAdapter adapter;

        private String url;
        private String urlParameters = null;

        private SwipeRefreshLayout swipeRefreshLayout;
        private ProgressBar progressBar;

        private ArrayList<EventsWrapper> list;

        private String dummy[] = {"A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" , "I" , "J" , "K" , "L" , "M"};

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            recyclerView = (RecyclerView) rootView.findViewById(R.id.events_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.latest_events_swipe_refresh_layout);
            progressBar = (ProgressBar) rootView.findViewById(R.id.latest_events_progress_bar);


            switch (getArguments().getInt(ARG_SECTION_NUMBER) -1){
                case 0:
                    //Latest Events- First Tab
                    new ServerAsync().execute(new String[]{Consts.Events_Constants.EVENTS_LATEST});
                    break;
                case 1:
                    //Past Events - Second Tab
                    new ServerAsync().execute(new String[]{Consts.Events_Constants.EVENTS_PREV});
                    break;
                case 2:
                    //Upcoming - Third Tab
                    new ServerAsync().execute(new String[]{Consts.Events_Constants.EVENTS_UPCOMING});
                    break;
            }



            return rootView;
        }


        public void onRCVClick(View view, int position) {

            Intent intent = new Intent(getActivity() ,EventsDetailedView.class);
            intent.putExtra("tittle" , list.get(position).getTitle());
            intent.putExtra("subtitle" , list.get(position).getSubtitle());
            intent.putExtra("date" , list.get(position).getDate());
            intent.putExtra("faculty_image_link" , list.get(position).getImage());
            intent.putExtra("detail" , list.get(position).getDetail());
            intent.putExtra("tab_position" , Events.tab_position);

            startActivity(intent);
        }

        public class ServerAsync extends AsyncTask<String[] , Void , ArrayList<EventsWrapper>> {

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressDialog = ProgressDialog.show(getActivity(), "Please Wait",null, true, true);
            }

            @Override
            protected ArrayList<EventsWrapper>  doInBackground(String[]... params) {
                return fetchDatabaseList_Events(params[0]);
            }


            @Override
            protected void onPostExecute(ArrayList<EventsWrapper> result) {
                super.onPostExecute(result);
                //progressDialog.dismiss();

               // Toast.makeText(getActivity() , "" + result.size() , Toast.LENGTH_SHORT).show();

                list = result;

                adapter = new EventsAdapter(getActivity() , list);
                recyclerView.setAdapter(adapter);
                adapter.setOnRCVClickListener(PlaceholderFragment.this);

                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }

            private ArrayList<EventsWrapper> fetchDatabaseList_Events(String[] selectionArgs) {
                ArrayList<EventsWrapper> list = new ArrayList<>();

                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.EVENTS_CONTENT_URI,
                        null, DatabaseContract.EventsTable.EVENTS_FLAG + " = ?" ,//
                        selectionArgs, null);

                while (cursor.moveToNext()) {
                    EventsWrapper events = new EventsWrapper();

                    events.setEvents_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_SERVER_ID)));

                    events.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_TITLE)));
                    events.setDate(cursor.getString(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_DATE)));
                    events.setSubtitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_SUBTITLE)));
                    events.setDetail(cursor.getString(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_DETAIL)));
                    events.setAuthor(cursor.getString(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_AUTHOR)));
                    events.setImage(cursor.getString(cursor.getColumnIndex(DatabaseContract.EventsTable.EVENTS_IMAGE)));

                    list.add(events);

                }

                return list;
            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Latest";
                case 1:
                    return "Past";
                case 2:
                    return "Upcoming";
            }
            return null;
        }
    }
}
