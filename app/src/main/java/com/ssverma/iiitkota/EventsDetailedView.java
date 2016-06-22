package com.ssverma.iiitkota;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

//Author-Dixit Chauhan      :03/06/2016

public class EventsDetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView faculty_image;
    private TextView news_tittle;
    private TextView news_subtitle;
    private TextView news_date;
    private TextView news_toolbar;
    private ImageView image_bg;

    private WebView news_desc;
    private int[] ken_burns_bg = {R.drawable.event_latest, R.drawable.event_past, R.drawable.event_upcoming};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        news_desc = (WebView) findViewById(R.id.desc);
        news_subtitle = (TextView) findViewById(R.id.subtitle);
        news_tittle = (TextView) findViewById(R.id.news_tittle);
        news_date = (TextView) findViewById(R.id.news_date);
        faculty_image = (CircleImageView) findViewById(R.id.faculty_detailed_faculty_image);
        news_toolbar = (TextView) findViewById(R.id.faculty_name_toolbar_fd);
        image_bg = (ImageView) findViewById(R.id.imageView_background_faculty_detailed);

        news_tittle.setText(getIntent().getExtras().getString("tittle"));
        news_subtitle.setText(getIntent().getExtras().getString("subtitle"));
        news_date.setText(getIntent().getExtras().getString("date"));


        Picasso.with(getApplicationContext()).load(ServerContract.getEventsImagePath() + getIntent().getExtras().getString("faculty_image_link")).into(faculty_image);
        String summary = getIntent().getExtras().getString("detail");
        String desc = "<html><style type=\"text/css\">p{text-align:justify;font-size:16px;}</style></head><body>" + "<p>" + summary + "</p>" + "</body></html>";
        news_desc.loadData(desc, "text/html", "utf-8");
        news_toolbar.setText(getIntent().getExtras().getString("tittle"));

        Picasso.with(getApplicationContext()).load(ServerContract.getEventsImagePath() + getIntent().getExtras().getString("faculty_image_link")).into(image_bg);

    }

    private void bindActivity() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_faculty_detailed);
        setSupportActionBar(mToolbar);
        mTitle = (TextView) findViewById(R.id.faculty_name_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.faculty_name_data_holder_fd);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_faculty_detailed);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
