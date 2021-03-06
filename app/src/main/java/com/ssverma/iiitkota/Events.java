package com.ssverma.iiitkota;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.utils.Consts;

import java.util.ArrayList;

public class Events extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.events, R.drawable.events, R.drawable.events};
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment implements RCVClickListener {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView recyclerView;
        private EventsAdapter adapter;
        private ProgressBar progressBar;

        private TextView nothingToShow;

        private ArrayList<EventsWrapper> list;


        public PlaceholderFragment() {
        }


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

            recyclerView = (RecyclerView) rootView.findViewById(R.id.events_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            progressBar = (ProgressBar) rootView.findViewById(R.id.latest_events_progress_bar);

            nothingToShow = (TextView) rootView.findViewById(R.id.nothingToShow);


            switch (getArguments().getInt(ARG_SECTION_NUMBER) - 1) {
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

            Intent intent = new Intent(getActivity(), EventsDetailedView.class);
            intent.putExtra("tittle", list.get(position).getTitle());
            intent.putExtra("subtitle", list.get(position).getSubtitle());
            intent.putExtra("date", list.get(position).getDate());
            intent.putExtra("faculty_image_link", list.get(position).getImage());
            intent.putExtra("detail", list.get(position).getDetail());
            intent.putExtra("tab_position", Events.tab_position);

            startActivity(intent);
        }

        public class ServerAsync extends AsyncTask<String[], Void, ArrayList<EventsWrapper>> {

            @Override
            protected ArrayList<EventsWrapper> doInBackground(String[]... params) {
                return fetchDatabaseList_Events(params[0]);
            }


            @Override
            protected void onPostExecute(ArrayList<EventsWrapper> result) {
                super.onPostExecute(result);

                list = result;

                if (list.size() == 0) {
                    nothingToShow.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                adapter = new EventsAdapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
                adapter.setOnRCVClickListener(PlaceholderFragment.this);

                progressBar.setVisibility(View.GONE);
            }

            private ArrayList<EventsWrapper> fetchDatabaseList_Events(String[] selectionArgs) {
                ArrayList<EventsWrapper> list = new ArrayList<>();

                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.EVENTS_CONTENT_URI,
                        null, DatabaseContract.EventsTable.EVENTS_FLAG + " = ?",
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
