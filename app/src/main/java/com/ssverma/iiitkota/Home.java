package com.ssverma.iiitkota;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.ssverma.iiitkota.admission.Admission_Home;
import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.gcm.RegisterOnServer;
import com.ssverma.iiitkota.gcm.registrationService;
import com.ssverma.iiitkota.utils.Consts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RCVClickListener {

    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private Home_Adapter adapter;

    private String[] icon_names = {"Academics", "Admissions", "Programs",
            "Contacts", "Events", "Faculty",
            "Gallery", "Map", "News feed",
            "Placements", "Scholarship", "Administration",
            "About Us", "Campus Life", "Social Connect"};

    private int[] icons = {R.drawable.home_academics, R.drawable.home_admission, R.drawable.home_academic_programs_,
            R.drawable.home_contact_, R.drawable.home_events_, R.drawable.home_faculty_,
            R.drawable.home_gallary_, R.drawable.home_map_, R.drawable.home_newsfeed,
            R.drawable.home_placement, R.drawable.home_scholarship, R.drawable.home_administrator,
            R.drawable.home_about_us_, R.drawable.home_campus, R.drawable.home_social_connect};

    private LocationManager locationManager;
    private Timer timer;
    private int page = 0;
    private ArrayList<NewsWrapper> latest_news_list;

    private int pageSwitchDuration = 3;  //seconds

    private Handler handler = new Handler();
    private boolean doubleBackToExitPressedOnce = false;

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        if (ServerConnection.isNetworkAvailable(this)) {
            new ServerAsync_News(true).execute(ServerContract.getNewsPhpUrl(), "filter=latest");
        } else {
            //offline mode
            new ServerAsync_News(false).execute();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Drawer Header (IIITK Logo Holder)
        View headerView = navigationView.getHeaderView(0);

        LinearLayout drawer_logo_holder = (LinearLayout) headerView.findViewById(R.id.drawer_logo_holder);
        drawer_logo_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iiitkota.ac.in"));
                startActivity(browserIntent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedpreferences = getSharedPreferences("iiitkota", Context.MODE_PRIVATE);
        if (!sharedpreferences.contains("registerCheck")) {
            Intent intent = new Intent(this, registrationService.class);
            startService(intent);
            new RegisterOnServer(this);
        }
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.home_screen_viewpager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(0 , 0 , 30 , 0);
        viewPager.setPageMargin(10);
        //setUpViewPager(viewPager);

        viewPager.setAdapter(new HomePagerAdapter(new ArrayList<NewsWrapper>()));

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_home);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new Home_Adapter(icon_names, icons);
        adapter.setRCVClickListener(this);
        recyclerView.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;

                if (position == 0)
                    findViewById(R.id.home_latest_from_campus_text).setVisibility(View.INVISIBLE);
                else
                    findViewById(R.id.home_latest_from_campus_text).setVisibility(View.VISIBLE);
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
            //super.onBackPressed();
            performBackPressAction();
        }
    }

    private void performBackPressAction(){

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

        handler.postDelayed(runnable, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);

//        AnimToolbarSyncIcon obj = new AnimToolbarSyncIcon(this);
//        obj.setRefreshItem(menu.findItem(R.id.sync));
//        obj.runRefresh();
//        obj.stopRefresh();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.sync) {
            Bundle settingsBundle = new Bundle();
            settingsBundle.putBoolean(
                    ContentResolver.SYNC_EXTRAS_MANUAL, true);
            settingsBundle.putBoolean(
                    ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

            ContentResolver.requestSync(createDummyAccount(this), DatabaseContract.AUTHORITY, settingsBundle);
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_admission) {
            startActivity(new Intent(this, Admission_Home.class));
        } else if (id == R.id.nav_contact) {
            startActivity(new Intent(this, Contact.class));
        } else if (id == R.id.nav_events) {
            startActivity(new Intent(this, Events.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, Gallery.class));
        } else if (id == R.id.nav_share) {
            shareApp();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, About_App.class));
        } else if (id == R.id.nav_activities) {
            startActivity(new Intent(this, Fest.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void shareApp() {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "IIIT Kota");
            String msg = "\nDownload IIIT Kota Android application from here \n\n";
            msg = msg + "https://play.google.com\n\n";
            i.putExtra(Intent.EXTRA_TEXT, msg);
            startActivity(Intent.createChooser(i, "Choose your action"));
        } catch (Exception e) {

        }
    }

    private Account createDummyAccount(Context context) {
        Account dummyAccount = new Account("IIIT KOTA", "com.ssverma.iiitk");  // Acc , Acc Type
        AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(dummyAccount, null, null);

        return dummyAccount;
    }


    @Override
    public void onRCVClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, Academic_Home.class));
                break;
            case 1:
                startActivity(new Intent(this, Admission_Home.class));
                break;
            case 2:
                startActivity(new Intent(this, Programs.class));
                break;
            case 3:
                startActivity(new Intent(this, Contact.class));
                break;
            case 4:
                startActivity(new Intent(this, Events.class));
                break;
            case 5:
                startActivity(new Intent(this, Faculty.class));
                break;
            case 6:
                startActivity(new Intent(this, Gallery.class));
                break;
            case 7:
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Intent intent = new Intent(this, mapIIITK.class);
                    startActivity(intent);
                } else {
                    alertPopup();
                }

                break;
            case 8:
                startActivity(new Intent(this, NewsFeed.class));
                break;
            case 9:
                startActivity(new Intent(this, Placement.class));
                break;
            case 10:
                startActivity(new Intent(this, Scholarship.class));
                break;
            case 11:
                startActivity(new Intent(this, Administration.class));
                break;
            case 12:
                startActivity(new Intent(this, AboutUs.class));
                break;
            case 13:
                startActivity(new Intent(this, Campus_life.class));
                break;
            case 14:
                startActivity(new Intent(this, SocialConnect.class));
                break;
            case 15:
                startActivity(new Intent(this, Fest.class));
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
            return rootView;
        }

        @Override
        public void onRCVClick(View view, int position) {

        }

    }


    public void alertPopup() {
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

    public class HomePagerAdapter extends PagerAdapter {

        private ArrayList<NewsWrapper> latest_news_list;
        private boolean isListSizeZero;

        HomePagerAdapter(ArrayList<NewsWrapper> latest_news_list) {
            this.latest_news_list = latest_news_list;
        }


        @Override
        public int getCount() {
            if (latest_news_list == null) {
                return 1;
            } else if (latest_news_list.size() == 0) {
                isListSizeZero = true;
                return 1;
            } else {
                return latest_news_list.size() + 1;
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            final ImageView news_image;
            TextView news_title;
            TextView news_subtitle;
            TextView news_date;

            View rootView = LayoutInflater.from(container.getContext()).inflate(R.layout.home_viewpager_item, container, false);

            news_image = (ImageView) rootView.findViewById(R.id.home_viewpager_news_image);
            news_title = (TextView) rootView.findViewById(R.id.home_viewpager_news_title);
            news_subtitle = (TextView) rootView.findViewById(R.id.home_viewpager_news_subtitle);
            //news_date = (TextView) rootView.findViewById(R.id.home_viewpager_date);


            if (isListSizeZero) {
                news_title.setMaxLines(5);
                news_title.setTypeface(Typeface.DEFAULT_BOLD);
                news_subtitle.setVisibility(View.INVISIBLE);
                news_image.setVisibility(View.INVISIBLE);
                //rootView.findViewById(R.id.date_holder).setVisibility(View.INVISIBLE);
                container.addView(rootView);
                return rootView;
            }

            if (position == 0) {
                news_title.setMaxLines(5);
                news_title.setTypeface(Typeface.DEFAULT_BOLD);
                news_subtitle.setVisibility(View.INVISIBLE);
                news_image.setVisibility(View.INVISIBLE);
                findViewById(R.id.home_latest_from_campus_text).setVisibility(View.INVISIBLE);
                //rootView.findViewById(R.id.date_holder).setVisibility(View.INVISIBLE);
                container.addView(rootView);
                return rootView;
            }


            news_title.setText(latest_news_list.get(position - 1).getNews_tittle());
            news_subtitle.setText(latest_news_list.get(position - 1).getNews_subtitle());

            Picasso.with(Home.this).load(ServerContract.getNewsImagePath() + "/" + latest_news_list.get(position - 1).getNews_imageLink()).into(news_image);

            container.addView(rootView);


            rootView.findViewById(R.id.home_viewpager_row_item_holder).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(Home.this , "Clicked" + position , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Home.this, NewsFeed_DetialedView.class);
                    intent.putExtra("title", latest_news_list.get(position - 1).getNews_tittle());
                    intent.putExtra("subtitle", latest_news_list.get(position - 1).getNews_subtitle());
                    intent.putExtra("date", latest_news_list.get(position - 1).getNews_date());
                    intent.putExtra("image_link", latest_news_list.get(position - 1).getNews_imageLink());
                    intent.putExtra("detail", latest_news_list.get(position - 1).getNews_description());

//                    Pair<View, String> imagePair = Pair.create((View) news_image, "tImage");
//
//                    ActivityOptionsCompat options = ActivityOptionsCompat.
//                            makeSceneTransitionAnimation(Home.this, imagePair);
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                        startActivity(intent, options.toBundle());
//                    } else {
                        startActivity(intent);
//                    }


                }
            });

            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((android.support.v7.widget.CardView) object);
        }

    }

    public class ServerAsync_News extends AsyncTask<String, Void, String> {

        private boolean isOnline;

        ServerAsync_News(boolean isOnline) {
            this.isOnline = isOnline;
        }

        @Override
        protected String doInBackground(String... params) {
            if (isOnline) {
                return ServerConnection.obtainServerResponse(params[0], params[1]);
            } else {
                latest_news_list = fetchDatabaseList_News(new String[]{Consts.News_Constants.NEWS_LATEST});
                return null;
            }

        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            //Toast.makeText(getContext(),response+" ",Toast.LENGTH_LONG).show();
            if (isOnline) {
                latest_news_list = parseNewsJSON(response);
            }

            viewPager.setAdapter(new HomePagerAdapter(latest_news_list));
            pageSwitcher(pageSwitchDuration); // three seconds delay

        }


        private ArrayList<NewsWrapper> parseNewsJSON(String response) {

            ArrayList<NewsWrapper> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    NewsWrapper news = new NewsWrapper();

                    news.setNews_tittle(jsonObject.getString("Title"));
                    news.setNews_subtitle(jsonObject.getString("Subtitle"));
                    news.setNews_imageLink(jsonObject.getString("Image"));
                    news.setNews_description(jsonObject.getString("Detail"));

                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObject.getString("Date"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    news.setNews_date(new SimpleDateFormat("MMM dd ,yy").format(date));

                    list.add(news);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }


        //offline
        private ArrayList<NewsWrapper> fetchDatabaseList_News(String[] selectionArgs) {
            ArrayList<NewsWrapper> list = new ArrayList<>();

            Cursor cursor = getContentResolver().query(DatabaseContract.NEWS_CONTENT_URI,
                    null, DatabaseContract.NewsTable.NEWS_FLAG + " = ?",//
                    selectionArgs, null);

            while (cursor.moveToNext()) {
                NewsWrapper news = new NewsWrapper();

                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DATE)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                news.setNews_date(new SimpleDateFormat("MMM dd ,yy").format(date));

                news.setNews_tittle(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_TITLE)));
                news.setNews_subtitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_SUBTITLE)));
                news.setNews_imageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_IMAGE)));
                //news.setNews_imageLink(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DETAIL)));
                news.setNews_description(cursor.getString(cursor.getColumnIndex(DatabaseContract.NewsTable.NEWS_DETAIL)));

                list.add(news);

            }

            return list;
        }

    }

    public void pageSwitcher(int seconds) {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run() {

                    if (page > latest_news_list.size()) { // In my case the number of pages are 5
                        //timer.cancel();
                        // Showing a toast for just testing purpose
                        page = 0;
                    } else {
                        viewPager.setCurrentItem(page++);
                    }
                }
            });

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //page = 0;
        pageSwitcher(3);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
//            Toast.makeText(getApplicationContext(), "Timer stoped",
//                    Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null){
            handler.removeCallbacks(runnable);
        }
    }
}
