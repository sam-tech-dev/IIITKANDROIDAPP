package com.ssverma.iiitkota;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author-Dixit Chauhan      :08/06/2016
 */

public class ScholarshipDetailedView extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

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
    private TextView income;
    private TextView academic;
    private TextView category;
    private TextView value,procedure,remark,toolbar,toolbar_title,title,site_link;
    private ImageView image_bg;

    private WebView news_desc;
    private int[] ken_burns_bg = {R.drawable.event_latest, R.drawable.event_past , R.drawable.event_upcoming};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship__detailed_view);

        bindActivity();
        mAppBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(mTitle, 0, View.INVISIBLE);


        title = (TextView) findViewById(R.id.fd_name);
        income = (TextView) findViewById(R.id.fd_income);
        academic = (TextView) findViewById(R.id.fd_academic);
        category = (TextView) findViewById(R.id.fd_category);
        value = (TextView) findViewById(R.id.fd_value);
        procedure = (TextView) findViewById(R.id.fd_procedure);
        remark = (TextView) findViewById(R.id.fd_remarks);
        faculty_image = (CircleImageView) findViewById(R.id.faculty_image);
        toolbar = (TextView) findViewById(R.id.scholarship_name_scholarship_detailed);
        toolbar_title = (TextView) findViewById(R.id.scholarship_name_toolbar_fd);
        image_bg = (ImageView) findViewById(R.id.imageView_background_faculty_detailed);
        site_link = (TextView) findViewById(R.id.fd_link);


        title.setText(getIntent().getExtras().getString("name"));
        income.setText(getIntent().getExtras().getString("income"));
        academic.setText(getIntent().getExtras().getString("academic"));
        category.setText(getIntent().getExtras().getString("category"));
        value.setText(getIntent().getExtras().getString("value"));
        procedure.setText(getIntent().getExtras().getString("procedure"));
        remark.setText(getIntent().getExtras().getString("remark"));
        site_link.setText(getIntent().getExtras().getString("link"));

        site_link.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                Uri adress= Uri.parse(getIntent().getExtras().getString("link"));
                Intent browser= new Intent(Intent.ACTION_VIEW, adress);
                startActivity(browser);
            }

        });


        // Picasso.with(getApplicationContext()).load(ServerContract.getNewsImagesUrl()+getIntent().getExtras().getString("faculty_image_link")).into(faculty_image);
        // news_desc.loadData(getIntent().getExtras().getString("description"));
        //
      Picasso.with(getApplicationContext()).load(ServerContract.getScholarshipImagePath()+getIntent().getExtras().getString("faculty_image_link")).into(faculty_image);
        //String summary=getIntent().getExtras().getString("detail");
        //String desc = "<html><style type=\"text/css\">p{text-align:justify;font-size:12px;}</style></head><body>"+"<p>"+summary+"</p>"+"</body></html>" ;
       // news_desc.loadData(desc, "text/html", "utf-8");

        toolbar.setText(getIntent().getExtras().getString("name"));
        toolbar_title.setText(getIntent().getExtras().getString("name"));
        //image_bg.setImageResource(ken_burns_bg[getIntent().getExtras().getInt("tab_position")]);
        Picasso.with(getApplicationContext()).load(ServerContract.getScholarshipImagePath()+getIntent().getExtras().getString("faculty_image_link")).into(image_bg);

    }

    private void bindActivity() {
        mToolbar        = (Toolbar) findViewById(R.id.toolbar_faculty_detailed);
        setSupportActionBar(mToolbar);
        mTitle          = (TextView) findViewById(R.id.scholarship_name_toolbar_fd);
        mTitleContainer = (LinearLayout) findViewById(R.id.scholarship_name_data_holder_fd);
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
