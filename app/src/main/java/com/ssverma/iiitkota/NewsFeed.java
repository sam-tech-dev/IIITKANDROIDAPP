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

public class NewsFeed extends AppCompatActivity{


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.event_latest, R.drawable.event_past , R.drawable.event_upcoming };
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

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
        private News_Adapter adapter;

        private String url;
        private String urlParameters = null;

        private SwipeRefreshLayout swipeRefreshLayout;
        private ProgressBar progressBar;

        private ArrayList<NewsWrapper> list;

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
            View rootView = inflater.inflate(R.layout.fragment_newsfeed, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            recyclerView = (RecyclerView) rootView.findViewById(R.id.newsfeed_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.news_swipe_refresh_layout);
            progressBar = (ProgressBar) rootView.findViewById(R.id.news_progress_bar);


            switch (getArguments().getInt(ARG_SECTION_NUMBER) -1){


                case 0:
                    //Past News- Second Tab
                    new ServerAsync().execute(new String[]{Consts.News_Constants.NEWS_PREV});
                    break;


                case 1:
                    //Latest News- First Tab
                    new ServerAsync().execute(new String[]{Consts.News_Constants.NEWS_LATEST});
                    break;

                case 2:
                    //Upcoming - Third Tab
                    new ServerAsync().execute(new String[]{Consts.News_Constants.NEWS_UPCOMING});
                    break;
            }



            return rootView;
        }


        public void onRCVClick(View view, int position) {

            Intent intent = new Intent(getActivity() ,NewsFeed_DetialedView.class);
            intent.putExtra("title" , list.get(position).getNews_tittle());
            intent.putExtra("subtitle" , list.get(position).getNews_subtitle());
            intent.putExtra("date" , list.get(position).getNews_date());
            intent.putExtra("image_link" , list.get(position).getNews_imageLink());
            intent.putExtra("detail" , list.get(position).getNews_description());
            intent.putExtra("tab_position" , NewsFeed.tab_position);

            startActivity(intent);
        }

        public class ServerAsync extends AsyncTask<String[] , Void , ArrayList<NewsWrapper>> {

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressDialog = ProgressDialog.show(getActivity(), "Please Wait",null, true, true);
            }

            @Override
            protected ArrayList<NewsWrapper>  doInBackground(String[]... params) {
                return fetchDatabaseList_News(params[0]);
            }


            @Override
            protected void onPostExecute(ArrayList<NewsWrapper> result) {
                super.onPostExecute(result);
                //progressDialog.dismiss();

                // Toast.makeText(getActivity() , "" + result.size() , Toast.LENGTH_SHORT).show();

                list = result;

                adapter = new News_Adapter(getActivity() , list);
                recyclerView.setAdapter(adapter);
                adapter.setOnRCVClickListener(PlaceholderFragment.this);

                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }

            private ArrayList<NewsWrapper> fetchDatabaseList_News(String[] selectionArgs) {
                ArrayList<NewsWrapper> list = new ArrayList<>();

                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.NEWS_CONTENT_URI,
                        null, DatabaseContract.NewsTable.NEWS_FLAG + " = ?",//
                        selectionArgs, null);

                while (cursor.moveToNext()) {
                    NewsWrapper news = new NewsWrapper();

                    news.setNews_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_SERVER_ID)));

                    news.setNews_tittle(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_TITLE)));
                   // news.setNews_date(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));

                    Date date= null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    news.setNews_date(new SimpleDateFormat("MMM dd ,yy").format(date));

                    news.setNews_subtitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_SUBTITLE)));
                    news.setNews_description(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DETAIL)));
                   news.setAuthor(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_AUTHOR)));
                    news.setNews_imageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_IMAGE)));

                    list.add(news);

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
                    return "PREVIOUS";
                case 1:
                    return "LATEST";
                case 2:
                    return "UPCOMING";
            }
            return null;
        }
    }
}
