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

public class  Faculty_DetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView faculty_image;
    private TextView faculty_name;
    private TextView faculty_email;
    private TextView faculty_name_toolbar;
    private TextView faculty_qualification;
    private TextView faculty_designation;
    private TextView faculty_research_area;
    private TextView faculty_hometown;
    private TextView faculty_summary;
    private ImageView image_bg;

    private int[] ken_burns_bg = {R.drawable.faculty_cs_, R.drawable.faculty_ee , R.drawable.faculty_electronics_engineering};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        faculty_name = (TextView) findViewById(R.id.faculty_name_faculty_detailed);
        faculty_designation = (TextView) findViewById(R.id.faculty_designation_fd);
        faculty_image = (CircleImageView) findViewById(R.id.faculty_image);
        faculty_name_toolbar = (TextView) findViewById(R.id.faculty_name_toolbar_fd);
        image_bg = (ImageView) findViewById(R.id.imageView_background_faculty_detailed);
        faculty_qualification = (TextView) findViewById(R.id.fd_qualifications);
        faculty_email = (TextView) findViewById(R.id.fd_email);
        faculty_research_area = (TextView) findViewById(R.id.fd_research_area);
        faculty_hometown = (TextView) findViewById(R.id.fd_home_town);
        faculty_summary = (TextView) findViewById(R.id.fd_other);

        faculty_name.setText(getIntent().getExtras().getString("faculty_name"));
        faculty_email.setText(getIntent().getExtras().getString("faculty_email"));
        faculty_designation.setText(getIntent().getExtras().getString("faculty_designation"));
        faculty_qualification.setText(getIntent().getExtras().getString("faculty_qualification"));
        faculty_research_area.setText(getIntent().getExtras().getString("faculty_research_area"));
        faculty_hometown.setText(getIntent().getExtras().getString("faculty_hometown"));
        faculty_summary.setText(getIntent().getExtras().getString("faculty_summary"));

        Picasso.with(getApplicationContext()).load(ServerContract.getFacultyImagesPath() + getIntent().getExtras().getString("faculty_image_link")).into(faculty_image);

        //Toast.makeText(getApplicationContext() , getIntent().getExtras().getString("faculty_image_link") + "" , Toast.LENGTH_SHORT).show();
        faculty_name_toolbar.setText(getIntent().getExtras().getString("faculty_name"));
        image_bg.setImageResource(ken_burns_bg[getIntent().getExtras().getInt("tab_position")]);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_faculty_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.faculty_name_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.faculty_name_data_holder_fd);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.appbar_faculty_detailed);
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