package com.ssverma.iiitkota;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.flaviofaria.kenburnsview.KenBurnsView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Author-Dixit Chauhan : 16/06/2016
 */
public class Academic_Courses extends AppCompatActivity{


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.courses, R.drawable.courses ,
                                     R.drawable.courses, R.drawable.courses  };
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_courses);

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
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private Academic_CoursesAdapter adapter;

        private String url;
        private String urlParameters = null;

        private SwipeRefreshLayout swipeRefreshLayout;
        private ProgressBar progressBar;

        private ArrayList<Academic_CoursesWrapper> list;

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
            View rootView = inflater.inflate(R.layout.fragment_academic_courses, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            recyclerView = (RecyclerView) rootView.findViewById(R.id.courses_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.courses_swipe_refresh_layout);
            progressBar = (ProgressBar) rootView.findViewById(R.id.courses_progress_bar);

            url = ServerContract.getAcademicCoursesPhpUrl();

            switch (getArguments().getInt(ARG_SECTION_NUMBER) -1){
                case 0:
                    progressBar.setVisibility(View.VISIBLE);

                    urlParameters = "filter=1";

                    fetchListFromServer(url , urlParameters);

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            progressBar.setVisibility(View.VISIBLE);
                            fetchListFromServer(url , urlParameters);
                        }
                    });

                    break;
                case 1:
                    progressBar.setVisibility(View.VISIBLE);

                    urlParameters = "filter=2";

                    fetchListFromServer(url , urlParameters);

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            progressBar.setVisibility(View.VISIBLE);
                            fetchListFromServer(url , urlParameters);
                        }
                    });
                    break;
                case 2:
                    progressBar.setVisibility(View.VISIBLE);

                    urlParameters = "filter=3";

                    fetchListFromServer(url , urlParameters);

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            progressBar.setVisibility(View.VISIBLE);
                            fetchListFromServer(url , urlParameters);
                        }
                    });
                    break;
                case 3:
                    progressBar.setVisibility(View.VISIBLE);

                    urlParameters = "filter=4";

                    fetchListFromServer(url , urlParameters);

                    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            progressBar.setVisibility(View.VISIBLE);
                            fetchListFromServer(url , urlParameters);
                        }
                    });
                    break;
            }



            return rootView;
        }

        private void fetchListFromServer(String url , String urlParameters) {

            new ServerAsync().execute(url , urlParameters);

        }

        public class ServerAsync extends AsyncTask<String , Void , String>{

            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //progressDialog = ProgressDialog.show(getActivity(), "Please Wait",null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {
                return ServerConnection.obtainServerResponse(params[0] , params[1]);
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                //progressDialog.dismiss();

                //Toast.makeText(getActivity() , "" + response , Toast.LENGTH_SHORT).show();

                list = parseJSON(response);
                adapter = new Academic_CoursesAdapter(getContext() , list);
                recyclerView.setAdapter(adapter);

                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);

                // Toast.makeText(getActivity() , "Size : " + list.size() , Toast.LENGTH_SHORT).show();

            }

            private ArrayList<Academic_CoursesWrapper> parseJSON(String response) {
                ArrayList<Academic_CoursesWrapper> list = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        Academic_CoursesWrapper academic_coursesWrapper = new Academic_CoursesWrapper();
                        //Toast.makeText(getContext(),"jhjhhf",Toast.LENGTH_SHORT).show();
                        academic_coursesWrapper.setCourse_name(jsonObject.getString("Name"));
                        academic_coursesWrapper.setCode(jsonObject.getString("Code"));
                        academic_coursesWrapper.setAbbr(jsonObject.getString("Abbreviation"));

                      //  events.setDate(jsonObject.getString("Date"));

                        academic_coursesWrapper.setCredit(jsonObject.getString("Credit"));
                        academic_coursesWrapper.setSemester(jsonObject.getString("Semester"));

                        list.add(academic_coursesWrapper);
                    }
                } catch (JSONException e) {
                    //tv.setText("JSON E:" + e);
                }

                //tv.setText(list.get(0).getS_name());
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
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Semester 1";
                case 1:
                    return "Semester 2";
                case 2:
                    return "Semester 3";
                case 3:
                    return "Semester 4";
            }
            return null;
        }
    }
}
