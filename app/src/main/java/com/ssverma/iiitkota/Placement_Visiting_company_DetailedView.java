package com.ssverma.iiitkota;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Placement_Visiting_company_DetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener  {



    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView vc_image;
    private TextView vc_name;

    private TextView vc_toolbar_fd;
    private ImageView image_bg;
    private TextView vc_details;
    private TextView vc_address;
    private TextView vc_contact;
    private TextView vc_email;
    private TextView vc_domain;
    private TextView vc_strength;
    private TextView vc_turnover;
    private TextView vc_ctc;
    private TextView vc_industry;

    private int[] ken_burns_bg = {R.drawable.faculty_cs_, R.drawable.faculty_ee };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_company__detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        vc_name = (TextView) findViewById(R.id.vc_vc_detailed);
        vc_toolbar_fd = (TextView) findViewById(R.id.toolbar_vc);
        vc_details = (TextView) findViewById(R.id.vc_details);
        vc_address=(TextView) findViewById(R.id.vc_address);
        vc_ctc=(TextView) findViewById(R.id.vc_ctc);
        vc_domain=(TextView)findViewById(R.id.vc_domain);
        vc_email=(TextView)findViewById(R.id.vc_email);
        vc_strength=(TextView)findViewById(R.id.vc_strength);
        vc_turnover=(TextView)findViewById(R.id.vc_turnover);
        vc_industry=(TextView)findViewById(R.id.vc_industry);


        vc_image = (CircleImageView) findViewById(R.id.vc_image);
        image_bg = (ImageView) findViewById(R.id.imageView_background_vc_detailed);
        vc_toolbar_fd = (TextView) findViewById(R.id.toolbar_vc);
        vc_name.setText(getIntent().getExtras().getString("Name"));
        vc_details.setText(getIntent().getExtras().getString("Summary"));
        vc_turnover.setText(getIntent().getExtras().getString("turnover"));
        vc_email.setText(getIntent().getExtras().getString("email"));
        vc_strength.setText(getIntent().getExtras().getString("strength"));
        vc_domain.setText(getIntent().getExtras().getString("domain"));
        vc_address.setText(getIntent().getExtras().getString("address"));
        vc_ctc.setText(getIntent().getExtras().getString("ctc"));
        vc_industry.setText(getIntent().getExtras().getString("industry"));


        //Picasso.with(getApplicationContext()).load(ServerContract.getProgramImagesPath() + getIntent().getExtras().getString("Program_image")).into(vc_image);

       vc_toolbar_fd.setText(getIntent().getExtras().getString("Name"));
        image_bg.setImageResource(ken_burns_bg[getIntent().getExtras().getInt("tab_position")]);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_vc_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.toolbar_vc);
        mTitleContainer = (LinearLayout) findViewById(R.id.vc_data_holder);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.appbar_vc_detailed);
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
