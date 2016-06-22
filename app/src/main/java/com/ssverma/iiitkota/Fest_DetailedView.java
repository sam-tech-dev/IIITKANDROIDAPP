package com.ssverma.iiitkota;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fest_DetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView fest_image;
    private TextView fest_title;

    private TextView fest_toolbar;
    private KenBurnsView image_bg;

    private WebView fest_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fest_detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        fest_desc = (WebView) findViewById(R.id.desc);

        fest_title = (TextView) findViewById(R.id.fest_tittle);


        fest_toolbar = (TextView) findViewById(R.id.fest_toolbar_fd);
        image_bg = (KenBurnsView) findViewById(R.id.imageView_background_fest_detailed);
        fest_image = (CircleImageView) findViewById(R.id.fest_detailed_fest_image);
        String title = getIntent().getExtras().getString("Name");
        fest_title.setText(title);

        String summary = getIntent().getExtras().getString("Description");
        fest_desc.loadData(summary, "text/html", "utf-8");
        fest_toolbar.setText(getIntent().getExtras().getString("Name"));

        Picasso.with(getApplicationContext()).load(ServerContract.getFestImagesUrl() + getIntent().getExtras().getString("ImageLink")).into(image_bg);
        Picasso.with(getApplicationContext()).load(ServerContract.getFestImagesUrl() + getIntent().getExtras().getString("ImageLink")).into(fest_image);

    }

    private void bindActivity() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_fest_detailed);
        setSupportActionBar(mToolbar);
        mTitle = (TextView) findViewById(R.id.fest_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.fest_data_holder_fd);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_fest_detailed);
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
