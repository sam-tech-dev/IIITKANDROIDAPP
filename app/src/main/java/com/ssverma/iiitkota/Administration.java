package com.ssverma.iiitkota;

import android.content.Intent;
import android.database.Cursor;
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
import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.utils.Consts;

import java.util.ArrayList;

public class Administration extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.administration_gc, R.drawable.administration_ec, R.drawable.administration_adjunct};
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Administration");

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
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment implements RCVClickListener {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView recyclerView;
        private Administration_Adapter adapter;
        private ProgressBar progressBar;

        private ArrayList<AdministrationWrapper> list;

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
            final View rootView = inflater.inflate(R.layout.fragment_faculty, container, false);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.faculty_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar = (ProgressBar) rootView.findViewById(R.id.faculty_cs_progress_bar);

            switch (getArguments().getInt(ARG_SECTION_NUMBER) - 1) {
                case 0:
                    new ServerAsync().execute(new String[]{Consts.Administration_Constants.GOVERNINGCOUNCIL});
                    break;
                case 1:
                    new ServerAsync().execute(new String[]{Consts.Administration_Constants.EXECUTIVECOUNCIL});
                    break;
                case 2:
                    new ServerAsync().execute(new String[]{Consts.Administration_Constants.ADJUNCT});
                    break;
            }


            return rootView;
        }


        @Override
        public void onRCVClick(View view, int position) {

            int temp = getArguments().getInt(ARG_SECTION_NUMBER);

            if ((temp == 2 || temp == 3) && (position == 0)) {

                Intent intent = new Intent(getActivity(), Administration_DetailedView.class);
                intent.putExtra("admin_id", list.get(position).getAdmin_id());
                intent.putExtra("admin_name", list.get(position).getAdmin_name());
                intent.putExtra("admin_designation", list.get(position).getAdmin_designation());

                intent.putExtra("admin_category", list.get(position).getAdmin_category());
                intent.putExtra("admin_fragment_no", temp);


                intent.putExtra("tab_position", Faculty.tab_position);


                startActivity(intent /*, options.toBundle()*/);
            }
        }

        public class ServerAsync extends AsyncTask<String[], Void, ArrayList<AdministrationWrapper>> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected ArrayList<AdministrationWrapper> doInBackground(String[]... params) {
                return fetchDatabaseList_Administration(params[0]);
            }


            @Override
            protected void onPostExecute(ArrayList<AdministrationWrapper> result) {
                super.onPostExecute(result);


                list = result;
                adapter = new Administration_Adapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
                adapter.setOnRCVClickListener(PlaceholderFragment.this);
                progressBar.setVisibility(View.GONE);
            }

            private ArrayList<AdministrationWrapper> fetchDatabaseList_Administration(String[] selectionArgs) {
                ArrayList<AdministrationWrapper> list = new ArrayList<>();


                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.ADMINISTRATION_CONTENT_URI,
                        null, DatabaseContract.AdministrationTable.ADMINISTRATION_CATEGORY + " = ?",
                        selectionArgs, null);

                while (cursor.moveToNext()) {
                    AdministrationWrapper administration = new AdministrationWrapper();

                    administration.setAdministration_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.AdministrationTable.ADMINISTRATION_SERVER_ID)));
                    administration.setAdmin_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.AdministrationTable.ADMINISTRATION_NAME)));
                    administration.setAdmin_designation(cursor.getString(cursor.getColumnIndex(DatabaseContract.AdministrationTable.ADMINISTRATION_DESIGNATION)));
                    administration.setAdmin_category(cursor.getString(cursor.getColumnIndex(DatabaseContract.AdministrationTable.ADMINISTRATION_CATEGORY)));


                    System.out.print("\n\n\n\n\nCategory = " + DatabaseContract.AdministrationTable.ADMINISTRATION_CATEGORY + "\n\n\n\n\n");
                    list.add(administration);

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
                    return "Governing Council";
                case 1:
                    return "Executive Council";
                case 2:
                    return "Adjunct";
            }
            return null;
        }
    }
}
