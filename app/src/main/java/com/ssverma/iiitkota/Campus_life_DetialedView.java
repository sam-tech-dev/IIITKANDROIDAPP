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
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Campus_life_DetialedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView campus_image;
    private TextView campus_title;

    private TextView campus_toolbar;
    private KenBurnsView image_bg;

    private WebView campus_desc;

    private int[] ken_burns_bg = {R.drawable.campus1, R.drawable.campus2, R.drawable.campus3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_life__detialed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        campus_desc = (WebView) findViewById(R.id.desc);

        campus_title = (TextView) findViewById(R.id.campus_tittle);


        campus_toolbar = (TextView) findViewById(R.id.campus_toolbar_fd);
        image_bg = (KenBurnsView) findViewById(R.id.imageView_background_campus_detailed);
        campus_image = (CircleImageView) findViewById(R.id.campus_detailed_campus_image);
        String title=getIntent().getExtras().getString("Title");
        campus_title.setText(title);



        String summary=getIntent().getExtras().getString("Description");

      //  Toast.makeText(getApplicationContext(),"" +summary ,Toast.LENGTH_SHORT).show();

        campus_desc.loadData(summary, "text/html", "utf-8");


        campus_toolbar.setText(getIntent().getExtras().getString("Title"));

        Picasso.with(getApplicationContext()).load(ServerContract.getCampusImagesUrl()+getIntent().getExtras().getString("image_link")).placeholder(R.drawable.campus1).into(image_bg);


        Picasso.with(getApplicationContext()).load(ServerContract.getCampusImagesUrl()+getIntent().getExtras().getString("image_link")).placeholder(R.drawable.campus2).into(campus_image);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_campus_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.campus_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.campus_data_holder_fd);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.appbar_campus_detailed);
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

            if(!mIsTheTitleVisible) {
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
            if(mIsTheTitleContainerVisible) {
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

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
