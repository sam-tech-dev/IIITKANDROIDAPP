package com.ssverma.iiitkota;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IIITK on 5/31/2016.
 */
public class Program_DetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView program_image;
    private TextView program_name;
    private TextView program_desc;
    private TextView program_eligi;
    private TextView program_name_toolbar_fd;
    private ImageView image_bg;

    private int[] ken_burns_bg = {R.drawable.faculty_cs_, R.drawable.faculty_ee };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        program_name = (TextView) findViewById(R.id.program_name_program_detailed);
        program_desc = (TextView) findViewById(R.id.program_designation_fd);
        program_image = (CircleImageView) findViewById(R.id.program_detailed_program_image);
       // program_eligi = (TextView) findViewById(R.id.program_name_toolbar_fd);
        image_bg = (ImageView) findViewById(R.id.imageView_background_program_detailed);


        program_name_toolbar_fd = (TextView) findViewById(R.id.program_name_toolbar_fd);
        program_name.setText(getIntent().getExtras().getString("Program_name"));
        program_desc.setText(getIntent().getExtras().getString("Program_desc"));


        Picasso.with(getApplicationContext()).load(getIntent().getExtras().getString("Program_image")).into(program_image);

        program_name_toolbar_fd.setText(getIntent().getExtras().getString("Program_name"));
        image_bg.setImageResource(ken_burns_bg[getIntent().getExtras().getInt("tab_position")]);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_program_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.program_name_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.program_name_data_holder_fd);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.appbar_program_detailed);
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