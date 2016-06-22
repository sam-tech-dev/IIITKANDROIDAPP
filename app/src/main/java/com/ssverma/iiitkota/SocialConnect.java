package com.ssverma.iiitkota;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class SocialConnect extends AppCompatActivity implements RCVClickListener {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_connect);

        recyclerView=(RecyclerView)findViewById(R.id.container);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SocialConnectAdpter mAdapter=new SocialConnectAdpter(SocialConnect.this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnRCVClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRCVClick(View view, int position) {

        if (view.getId() == R.id.externalConnect){

            if(SocialConnectAdpter.socialList.get(position).name.equals("Facebook")){

                    String url=SocialConnectAdpter.socialList.get(position).url;

                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(SocialConnect.this,url);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
            }


            if(SocialConnectAdpter.socialList.get(position).name.equals("Google Plus")){


                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(SocialConnectAdpter.socialList.get(position).url));
                    intent.setPackage("com.google.android.apps.plus"); // don't open the browser, make sure it opens in Google+ app
                    startActivity(intent);

                }catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(SocialConnectAdpter.socialList.get(position).url)));
                }


            }

            if(SocialConnectAdpter.socialList.get(position).name.equals("Youtube")){

                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(SocialConnectAdpter.socialList.get(position).url));
                    startActivity(intent);

                }catch (Exception e){
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(SocialConnectAdpter.socialList.get(position).url)));
                }


            }


            if(SocialConnectAdpter.socialList.get(position).name.equals("Linkedin")){

                try {

                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("linkedin://iiit-delhi/"));
                    startActivity(intent);

                }catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(SocialConnectAdpter.socialList.get(position).url)));
                }

            }

            if(SocialConnectAdpter.socialList.get(position).name.equals("Twitter")){

                    try {

                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("twitter://user?screen_name=[user_name]"));
                        startActivity(intent);

                    }catch (Exception e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(SocialConnectAdpter.socialList.get(position).url)));
                    }
            }





        }else {

            Intent intent = new Intent(this, SocialViews.class);
            intent.putExtra("url", SocialConnectAdpter.socialList.get(position).url);
            startActivity(intent);
        }
    }



    public String getFacebookPageURL(Context context,String url) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href="+url;
            } else { //older versions of fb app
                return "fb://page/HgSmiiitians";
            }
        } catch (PackageManager.NameNotFoundException e) {
            return url;
        }
    }


}
