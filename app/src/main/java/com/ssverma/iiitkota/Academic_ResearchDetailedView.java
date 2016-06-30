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

public class Academic_ResearchDetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {


    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;

    private CircleImageView project_image;
    private ImageView image_bg;

    private TextView project_name_detailed;
    private TextView project_name_toolbar_fd;

    private TextView project_name;
    private TextView person_name;
    private TextView person_position;
    private TextView detail;

    private int[] ken_burns_bg = {R.drawable.academics_projects, R.drawable.academics_publications };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_research_detailed_view);

        bindActivity();

        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);

        project_name_detailed = (TextView) findViewById(R.id.project_name_project_detailed);
        project_name_toolbar_fd = (TextView) findViewById(R.id.project_name_toolbar_fd);

        project_name = (TextView) findViewById(R.id.fd_project_name);
        person_name = (TextView) findViewById(R.id.fd_person_name);
        person_position = (TextView) findViewById(R.id.fd_person_position);
        detail = (TextView) findViewById(R.id.fd_detail);

        project_image = (CircleImageView) findViewById(R.id.project_detailed_project_image);
        image_bg = (ImageView) findViewById(R.id.imageView_background_research_detailed);

        project_name_detailed.setText(getIntent().getExtras().getString("Project_name"));
        project_name_toolbar_fd.setText(getIntent().getExtras().getString("Project_name"));

        project_name.setText(getIntent().getExtras().getString("Project_name"));
        person_name.setText(getIntent().getExtras().getString("Person_name"));
        person_position.setText(getIntent().getExtras().getString("Person_position"));
        detail.setText(getIntent().getExtras().getString("Detail"));

        Picasso.with(getApplicationContext()).load(ServerContract.getAcademicResearchProjectImagePath() + getIntent().getExtras().getString("Project_image_link")).into(project_image);
        Picasso.with(getApplicationContext()).load(ServerContract.getAcademicResearchProjectImagePath() + getIntent().getExtras().getString("Project_image_link")).into(image_bg);
    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_project_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.project_name_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.research_name_data_holder_fd);
        mAppBarLayout   = (AppBarLayout) findViewById(R.id.appbar_research_detailed);
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
