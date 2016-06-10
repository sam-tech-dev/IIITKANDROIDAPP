package com.ssverma.iiitkota;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.utils.Consts;

import java.util.ArrayList;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  , RCVClickListener{

    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private Home_Adapter adapter;

    private String[] icon_names = {"Join IIITK" , "Admissions" , "Programs" ,
            "Contacts" , "Events" , "Faculty" ,
            "Gallery" , "Map" , "News feed",
            "Placements" , "Scholarship" , "Administration" ,
            "About Us" , "Campus Life" , "Social Connect"};

    private int[] icons = {R.drawable.home_join_ , R.drawable.home_map_ , R.drawable.home_academic_programs_ ,
            R.drawable.home_contact_, R.drawable.home_events_ , R.drawable.home_faculty_ ,
            R.drawable.home_gallary_ , R.drawable.home_map_ , R.drawable.home_news_feed_ ,
            R.drawable.home_map_ , R.drawable.home_map_ , R.drawable.home_map_ ,
            R.drawable.home_about_us_ , R.drawable.home_map_ , R.drawable.home_map_};

    private LocationManager locationManager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        initViews();

    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.home_screen_viewpager);
        //setUpViewPager(viewPager);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 3));

        adapter = new Home_Adapter(icon_names , icons);
        adapter.setRCVClickListener(this);
        recyclerView.setAdapter(adapter);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(Home.this , "" + position , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
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

        if (id == R.id.sync){
            Bundle settingsBundle = new Bundle();
            settingsBundle.putBoolean(
                    ContentResolver.SYNC_EXTRAS_MANUAL, true);
            settingsBundle.putBoolean(
                    ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

            ContentResolver.requestSync(createDummyAccount(this) , DatabaseContract.AUTHORITY , settingsBundle);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Account createDummyAccount(Context context) {
        Account dummyAccount = new Account("IIIT KOTA" , "com.ssverma.iiitk");  // Acc , Acc Type
        AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(dummyAccount , null , null);

        return dummyAccount;
    }

    @Override
    public void onRCVClick(View view, int position) {
        switch (position){
            case 0:
                //startActivity(new Intent(this , Programs.class));
                break;
            case 1:
                //startActivity(new Intent(this , Programs.class));
                break;
            case 2:
                startActivity(new Intent(this , Programs.class));
                break;
            case 3:
                //startActivity(new Intent(this , Contacts.class));
                break;
            case 4:
                startActivity(new Intent(this , Events.class));
                break;
            case 5:
                startActivity(new Intent(this , Faculty.class));
                break;
            case 6:
                startActivity(new Intent(this , Gallery.class));
                break;
            case 7:
                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Intent intent = new Intent(this, mapIIITK.class);
                    startActivity(intent);
                }
                else{
                    alertPopup();
                }

                break;
            case 8:
                startActivity(new Intent(this , NewsFeed.class));
                break;
            case 9:
                //startActivity(new Intent(this , Placements.class));
                break;
            case 10:
                //startActivity(new Intent(this , Scholarship.class));
                break;
            case 11:
                //startActivity(new Intent(this , Administration.class));
                break;
            case 12:
                startActivity(new Intent(this , AboutUs.class));
                break;
            case 13:
                //startActivity(new Intent(this , CampusLife.class));
                break;
            case 14:
                //tartActivity(new Intent(this , SocialConnect.class));
                break;
        }
    }


    public static class PlaceholderFragment extends Fragment implements RCVClickListener {
        
        private static final String ARG_SECTION_NUMBER = "section_number";

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
            final View rootView = inflater.inflate(R.layout.home_viewpager_item, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            //Toast.makeText(getActivity() , "CreateView" , Toast.LENGTH_SHORT).show();

            return rootView;
        }

        @Override
        public void onRCVClick(View view, int position) {
            
        }
    }


    public void  alertPopup(){
        final AlertDialog alertDialog = new AlertDialog.Builder(
                this).create();

        alertDialog.setTitle("Alert");
        alertDialog.setMessage("This application wants to change your GPS setting for location.");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });

        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        alertDialog.show();
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
                    return "CS";
                case 1:
                    return "EE";
                case 2:
                    return "ECE";
            }
            return null;
        }
    }

}
