package com.ssverma.iiitkota;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.ssverma.iiitkota.sync_adapter.DatabaseContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Placement extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    ArrayList<Placement_module_wrapper> list;
    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.placements, R.drawable.placements, R.drawable.placements};
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Placements");

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
        private Placement_rep_Adapter adapter;
        private Placement_VisitingCompany_Adapter adapter2;
        private Placement_module_adapter adapter3;


        private final int RETRIEVE_COMPANY_LIST = 1;
        private final int RETRIEVE_REP_LIST = 2;
        private final int RETRIEVE_PLT_LIST = 3;

        private ArrayList<PlacementWrapper> list_rp;
        private ArrayList<Placement_VisitingCompany_Wrapper> list_vc;
        private ArrayList<Placement_module_wrapper> list_pm;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_placement_fragment1, container, false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.rep_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            switch (getArguments().getInt(ARG_SECTION_NUMBER) - 1) {

                case 0:
                    new ServerAsync(RETRIEVE_REP_LIST).execute();
                    break;
                case 1:
                    new ServerAsync(RETRIEVE_COMPANY_LIST).execute();
                    break;
                case 2:
                    new ServerAsync(RETRIEVE_PLT_LIST).execute(ServerContract.getPlacementDataPhpUrl());
                    break;

            }
            return rootView;

        }

        public void onRCVClick(View view, int position) {

            switch (view.getId()) {
                case R.id.vc_row_item_holder:
                    Intent intent = new Intent(getActivity(), Placement_Visiting_company_DetailedView.class);
                    intent.putExtra("Name", list_vc.get(position).getCompany_name());
                    intent.putExtra("Summary", list_vc.get(position).getCompany_summary());
                    intent.putExtra("address", list_vc.get(position).getAddress());
                    intent.putExtra("contact", list_vc.get(position).getCompany_contact());
                    intent.putExtra("email", list_vc.get(position).getCompany_email());
                    intent.putExtra("industry", list_vc.get(position).getCompany_industry());
                    intent.putExtra("strength", list_vc.get(position).getCompany_Strength());
                    intent.putExtra("turnover", list_vc.get(position).getCompany_turnover());
                    intent.putExtra("ctc", list_vc.get(position).getCompany_CTC());
                    intent.putExtra("domain", list_vc.get(position).getCompany_domain());
                    startActivity(intent);
                    break;
                case R.id.plt_row_item_holder:
                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                    String url = ServerContract.getPlacementReport_URL() + list_pm.get(position).getLink();
                    intent1.setData(Uri.parse(url));
                    startActivity(intent1);

            }
        }

        public class ServerAsync extends AsyncTask<String, Void, String> {
            private int ASYNC_CODE;

            ServerAsync(int ASYNC_CODE) {
                this.ASYNC_CODE = ASYNC_CODE;
            }

            protected String doInBackground(String... params) {

                switch (ASYNC_CODE) {
                    case RETRIEVE_REP_LIST:
                        list_rp = fetchDatabaseList_RP_Placement();
                        break;
                    case RETRIEVE_COMPANY_LIST:
                        list_vc = fetchDatabaseList_VC_Placement();
                        break;
                    case RETRIEVE_PLT_LIST:
                        return ServerConnection.obtainServerResponse(params[0]);
                }
                return null;
            }


            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);

                switch (ASYNC_CODE) {
                    case RETRIEVE_REP_LIST:
                        adapter = new Placement_rep_Adapter(getActivity(), list_rp);
                        recyclerView.setAdapter(adapter);
                        break;
                    case RETRIEVE_COMPANY_LIST:
                        adapter2 = new Placement_VisitingCompany_Adapter(getActivity(), list_vc);
                        recyclerView.setAdapter(adapter2);
                        adapter2.setOnRCVClickListener(PlaceholderFragment.this);
                        break;
                    case RETRIEVE_PLT_LIST:
                        list_pm = parsePlacementJSON(response);
                        adapter3 = new Placement_module_adapter(getActivity(), list_pm);
                        recyclerView.setAdapter(adapter3);
                        adapter3.setOnRCVClickListener(PlaceholderFragment.this);
                        break;

                }
            }


            private ArrayList<PlacementWrapper> fetchDatabaseList_RP_Placement() {
                ArrayList<PlacementWrapper> list = new ArrayList<>();
                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.RP_CONTENT_URI,
                        null, null, null, null);
                while (cursor.moveToNext()) {
                    PlacementWrapper rp = new PlacementWrapper();
                    rp.setPosition(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_RP_Table.RP_DES)));
                    rp.setImage(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_RP_Table.RP_IMAGE)));
                    rp.setMail(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_RP_Table.RP_EMAIL)));
                    rp.setContact(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_RP_Table.RP_MOB)));
                    rp.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_RP_Table.RP_NAME)));
                    list.add(rp);
                }

                return list;
            }

            private ArrayList<Placement_VisitingCompany_Wrapper> fetchDatabaseList_VC_Placement() {
                ArrayList<Placement_VisitingCompany_Wrapper> list = new ArrayList<>();
                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.VC_CONTENT_URI, null, null, null, null);

                while (cursor.moveToNext()) {
                    Placement_VisitingCompany_Wrapper vc = new Placement_VisitingCompany_Wrapper();
                    vc.setCompany_industry(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_INDUSTRY)));
                    vc.setCompany_CTC(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_CTC)));
                    vc.setCompany_turnover(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_TURNOVER)));
                    vc.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_ADDRESS)));
                    vc.setCompany_Strength(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_STRENGTH)));
                    vc.setCompany_contact(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_CONTACT)));
                    vc.setCompany_domain(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_DOMAIN)));
                    vc.setCompany_email(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_EMAIL)));
                    vc.setCompany_summary(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_SUMMARY)));
                    vc.setCompany_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.Placement_Visting_Company_Table.VC_NAME)));
                    list.add(vc);
                }
                return list;
            }

            //
            private ArrayList<Placement_module_wrapper> parsePlacementJSON(String response) {
                list_pm = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Placement_module_wrapper placement_wrapper = new Placement_module_wrapper();
                        placement_wrapper.setReport_type(jsonObject.getString("report_type"));
                        placement_wrapper.setLink(jsonObject.getString("link"));
                        list_pm.add(placement_wrapper);
                    }
                } catch (JSONException e) {

                }

                return list_pm;

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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Representative";
                case 1:
                    return "Visiting Company";
                case 2:
                    return "Placements";

            }
            return null;
        }
    }
}
