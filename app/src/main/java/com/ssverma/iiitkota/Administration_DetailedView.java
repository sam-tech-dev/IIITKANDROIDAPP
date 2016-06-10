package com.ssverma.iiitkota;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

public class  Administration_DetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;


    private TextView admin_name;
    private TextView admin_designation;
    private TextView admin_category;
    private TextView admin_qualification;
    private TextView admin_positionheld;



      private TextView admin_name_toolbar;

    private int[] ken_burns_bg = {R.drawable.faculty_cs_, R.drawable.faculty_ee , R.drawable.faculty_electronics_engineering};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String str1,str2;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        admin_name = (TextView) findViewById(R.id.admin_name_detailed);
        admin_designation = (TextView) findViewById(R.id.admin_designation_admin);
        admin_category = (TextView) findViewById(R.id.admin_designation_admin);
        admin_qualification = (TextView) findViewById(R.id.admin_qualifications);
        admin_positionheld = (TextView) findViewById(R.id.admin_positions);

        admin_name_toolbar = (TextView) findViewById(R.id.admin_name_toolbar_admin);

        admin_name.setText(getIntent().getExtras().getString("admin_name"));
        admin_designation.setText(getIntent().getExtras().getString("admin_designation"));
        admin_category.setText(getIntent().getExtras().getString("admin_category"));
     int temp = getIntent().getExtras().getInt("admin_fragment_no");
if(temp==2)
{
     str1=getString(R.string.q_director);
     str2=getString(R.string.p_director);
     admin_qualification.setText(str1);
    admin_positionheld.setText(str2);
}
if(temp==3)
{
    str1=getString(R.string.q_coordinator);
    admin_qualification.setText(str1);


}

        admin_name_toolbar.setText(getIntent().getExtras().getString("admin_name"));
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_admin_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.admin_name_toolbar_admin);
        mTitleContainer = (LinearLayout) findViewById(R.id.admin_name_data_holder_admin);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.appbar_admin_detailed);
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
