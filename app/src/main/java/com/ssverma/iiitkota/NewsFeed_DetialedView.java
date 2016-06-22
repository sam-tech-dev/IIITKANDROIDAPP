package com.ssverma.iiitkota;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsFeed_DetialedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView news_image;
    private TextView news_title;
    private TextView news_date;
    private TextView news_toolbar;
    private KenBurnsView image_bg;

    private WebView news_desc;
    private TextView news_subtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed__detialed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        news_desc = (WebView) findViewById(R.id.desc);
        news_subtitle = (TextView) findViewById(R.id.subtitle);
        news_title = (TextView) findViewById(R.id.news_tittle);

        news_date = (TextView) findViewById(R.id.news_date);
        news_image = (CircleImageView) findViewById(R.id.news_detailed_news_image);
        news_toolbar = (TextView) findViewById(R.id.news_toolbar_fd);
        image_bg = (KenBurnsView) findViewById(R.id.imageView_background_news_detailed);

        String marquee_title = getIntent().getExtras().getString("title");
        news_title.setText(marquee_title);
        news_title.setSelected(true);
        news_date.setText(getIntent().getExtras().getString("date"));

        Picasso.with(getApplicationContext()).load(ServerContract.getNewsImagePath() + getIntent().getExtras().getString("image_link")).placeholder(R.drawable.news).into(news_image);
        String summary = getIntent().getExtras().getString("detail");

        news_desc.loadData(summary, "text/html", "utf-8");
        news_subtitle.setText(getIntent().getExtras().getString("subtitle"));


        news_toolbar.setText(getIntent().getExtras().getString("title"));
        Picasso.with(getApplicationContext()).load(ServerContract.getNewsImagePath() + getIntent().getExtras().getString("image_link")).placeholder(R.drawable.news).into(image_bg);

    }

    private void bindActivity() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_news_detailed);
        setSupportActionBar(mToolbar);
        mTitle = (TextView) findViewById(R.id.news_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.news_data_holder_fd);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar_news_detailed);
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
