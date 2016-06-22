package com.ssverma.iiitkota;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.utils.Consts;

import java.util.ArrayList;


public class Contact extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private KenBurnsView kenBurnsView;

    private int[] ken_burns_bg = {R.drawable.contact, R.drawable.contact, R.drawable.contact, R.drawable.contact, R.drawable.contact};
    static int tab_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contacts");

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

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment implements RCVClickListener {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private RecyclerView recyclerView;
        private Contact_Adapter adapter;
        private ProgressBar progressBar;

        private ArrayList<ContactsWrapper> list;


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
            final View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.contact_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            progressBar = (ProgressBar) rootView.findViewById(R.id.contact_cs_progress_bar);

            switch (getArguments().getInt(ARG_SECTION_NUMBER) - 1) {

                case 0:
                    new ServerAsync().execute(new String[]{Consts.Contact_Constants.OFFICE});
                    break;
                case 1:
                    new ServerAsync().execute(new String[]{Consts.Contact_Constants.CS_DEPARTMENT});
                    break;
                case 2:
                    new ServerAsync().execute(new String[]{Consts.Contact_Constants.ECE_DEPARTMENT});
                    break;

                case 3:
                    new ServerAsync().execute(new String[]{Consts.Contact_Constants.ADJUNCT});
                    break;

                case 4:
                    new ServerAsync().execute(new String[]{Consts.Contact_Constants.GENERAL});
                    break;

            }

            return rootView;
        }


        @Override
        public void onRCVClick(View view, int position) {

            ContactsWrapper s = list.get(position);
            String c = s.getContact_mobile_no();
            final String number = "tel:" + c;

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            alert.setTitle("Proceed Call");
            alert.setMessage("Do you want to proceed the call?");

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse(number));

                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);
                    dialog.dismiss();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        }


        public class ServerAsync extends AsyncTask<String[], Void, ArrayList<ContactsWrapper>> {

            @Override
            protected ArrayList<ContactsWrapper> doInBackground(String[]... params) {
                return fetchDatabaseList_Contact(params[0]);
            }


            @Override
            protected void onPostExecute(ArrayList<ContactsWrapper> result) {
                super.onPostExecute(result);

                list = result;

                adapter = new Contact_Adapter(getActivity(), list);
                recyclerView.setAdapter(adapter);
                adapter.setOnRCVClickListener(PlaceholderFragment.this);
                progressBar.setVisibility(View.GONE);
            }


            private ArrayList<ContactsWrapper> fetchDatabaseList_Contact(String[] selectionArgs) {
                ArrayList<ContactsWrapper> list = new ArrayList<>();


                Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.CONTACT_CONTENT_URI,
                        null, DatabaseContract.ContactTable.CONTACT_CATEGORY + " = ?",
                        selectionArgs, null);


                while (cursor.moveToNext()) {
                    ContactsWrapper contact = new ContactsWrapper();

                    contact.setContact_server_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ContactTable.CONTACT_SERVER_ID)));
                    contact.setContact_name(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactTable.CONTACT_NAME)));
                    contact.setContact_email(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactTable.CONTACT_EMAIL)));
                    contact.setContact_mobile_no(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactTable.CONTACT_MOBILE)));
                    contact.setContact_category(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactTable.CONTACT_CATEGORY)));
                    contact.setContact_designation(cursor.getString(cursor.getColumnIndex(DatabaseContract.ContactTable.CONTACT_DESIGNATION)));

                    list.add(contact);

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
            return Contact.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Office";
                case 1:
                    return "CS Department";
                case 2:
                    return "ECE Department";
                case 3:
                    return "Adjunct";
                case 4:
                    return "General";
            }
            return null;
        }
    }
}
