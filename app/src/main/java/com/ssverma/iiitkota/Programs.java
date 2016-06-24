package com.ssverma.iiitkota;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import java.util.ArrayList;

public class Programs extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.programs_ug, R.drawable.programs_pg };
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment implements RCVClickListener {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView recyclerView;
        private Program_Adapter adapter;
        private ProgressBar progressBar;

        private ArrayList<ProgramWrapper> list;


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
            View rootView = inflater.inflate(R.layout.fragment_program, container, false);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.program_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar = (ProgressBar) rootView.findViewById(R.id.program_ug_progress_bar);

            switch (getArguments().getInt(ARG_SECTION_NUMBER) -1){
                case 0:
                    new ServerAsync().execute(new String[]{Consts.Program_Constants.UG_PROGRAMS});
                    break;
                case 1:
                    new ServerAsync().execute(new String[]{Consts.Program_Constants.PG_PROGRAMS});
                    break;
            }
            return rootView;
        }

        @Override
        public void onRCVClick(View view, int position) {

            Intent intent = new Intent(getActivity() , Program_DetailedView.class);
            intent.putExtra("Program_name" , list.get(position).getProgram_name());
            intent.putExtra("Program_desc" , list.get(position).getProgram_desc());
            intent.putExtra("Program_eligibility" , list.get(position).getProgram_eligibility());
            intent.putExtra("Program_image" , list.get(position).getProgram_image());
            intent.putExtra("Program_duration",list.get(position).getProgram_dur());
            intent.putExtra("Program_seats",list.get(position).getProgram_seats());
            intent.putExtra("Program_fee",list.get(position).getProgram_fee());
            intent.putExtra("tab_position" , Programs.tab_position);

            startActivity(intent);
        }

        public class ServerAsync extends AsyncTask<String[] , Void , ArrayList<ProgramWrapper> > {

            protected ArrayList<ProgramWrapper>  doInBackground(String[]... params) {
                return fetchDatabaseList_Program(params[0]);
            }
            protected void onPostExecute(ArrayList<ProgramWrapper> result) {
                super.onPostExecute(result);

                list = result;

                adapter = new Program_Adapter(getActivity() , list);
                recyclerView.setAdapter(adapter);
                adapter.setOnRCVClickListener(PlaceholderFragment.this);
                progressBar.setVisibility(View.GONE);
            }

            private ArrayList<ProgramWrapper> fetchDatabaseList_Program(String[] selectionArgs) {
                ArrayList<ProgramWrapper> list = new ArrayList<>();

                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.PROGRAM_CONTENT_URI ,
                        null , DatabaseContract.ProgramTable.PROGRAM_Type + " = ?" ,
                        selectionArgs , null);

                while (cursor.moveToNext()){
                    ProgramWrapper program = new ProgramWrapper();
                    program.setProgram_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_SERVER_ID)));
                    program.setProgram_seats(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_SEAT)));
                    program.setProgram_fee(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_FEE)));
                    program.setProgram_dur(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_DURATION)));
                    program.setProgram_desc(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_DESC)));
                    program.setProgram_eligibility(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_ELIGIBILITY)));
                    program.setProgram_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_NAME)));
                    program.setProgram_type(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_Type)));
                    program.setProgram_image(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProgramTable.PROGRAM_IMAGE)));
                    list.add(program);
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
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "UG";
                case 1:
                    return "PG";
            }
            return null;
        }
    }
}
