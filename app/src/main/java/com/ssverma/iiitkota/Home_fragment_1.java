package com.ssverma.iiitkota;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_fragment_1 extends Fragment implements View.OnClickListener{

    LocationManager   locationManager ;


    public Home_fragment_1() {

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        locationManager =(LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home_fragment_1, container, false);

        LinearLayout itemHolder1 = (LinearLayout) rootView.findViewById(R.id.home_row_item_one);
        ImageView icon1 = (ImageView) itemHolder1.findViewById(R.id.home_iv_row_item);
        icon1.setImageResource(R.drawable.home_registration);
        TextView text1 = (TextView) itemHolder1.findViewById(R.id.home_tv_row_item);
        text1.setText("Registration");

        LinearLayout row_iv_itemHolder1 = (LinearLayout) itemHolder1.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity() , "Clicked : Item 1" , Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getActivity() , Faculty_DetailedView.class));
            }
        });

        LinearLayout itemHolder2 = (LinearLayout) rootView.findViewById(R.id.home_row_item_two);
        ImageView icon2 = (ImageView) itemHolder2.findViewById(R.id.home_iv_row_item);
        icon2.setImageResource(R.drawable.home_alumni);
        TextView text2 = (TextView) itemHolder2.findViewById(R.id.home_tv_row_item);
        text2.setText("Programs");

        LinearLayout row_iv_itemHolder2 = (LinearLayout) itemHolder2.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , Programs.class));
            }
        });

        LinearLayout itemHolder3 = (LinearLayout) rootView.findViewById(R.id.home_row_item_three);
        ImageView icon3 = (ImageView) itemHolder3.findViewById(R.id.home_iv_row_item);
        icon3.setImageResource(R.drawable.home_news_feed);
        TextView text3 = (TextView) itemHolder3.findViewById(R.id.home_tv_row_item);
        text3.setText("News Feed");


        LinearLayout row_iv_itemHolder3 = (LinearLayout) itemHolder3.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , NewsFeed.class));
            }
        });

        LinearLayout itemHolder4 = (LinearLayout) rootView.findViewById(R.id.home_row_item_four);
        ImageView icon4 = (ImageView) itemHolder4.findViewById(R.id.home_iv_row_item);
        icon4.setImageResource(R.drawable.home_maps);
        TextView text4 = (TextView) itemHolder4.findViewById(R.id.home_tv_row_item);
        text4.setText("Map");

        LinearLayout row_iv_itemHolder4 = (LinearLayout) itemHolder4.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Intent intent = new Intent(getActivity(), mapIIITK.class);
                    startActivity(intent);
                }else{
                    alertPopup();
                }


            }
        });


        LinearLayout itemHolder5 = (LinearLayout) rootView.findViewById(R.id.home_row_item_five);
        ImageView icon5 = (ImageView) itemHolder5.findViewById(R.id.home_iv_row_item);
        icon5.setImageResource(R.drawable.home_contact);
        TextView text5 = (TextView) itemHolder5.findViewById(R.id.home_tv_row_item);
        text5.setText("About IIITK");

        LinearLayout itemHolder6 = (LinearLayout) rootView.findViewById(R.id.home_row_item_six);
        ImageView icon6 = (ImageView) itemHolder6.findViewById(R.id.home_iv_row_item);
        icon6.setImageResource(R.drawable.home_registration);
        TextView text6 = (TextView) itemHolder6.findViewById(R.id.home_tv_row_item);
        text6.setText("Social Connect");


        LinearLayout row_iv_itemHolder6 = (LinearLayout) itemHolder6.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity() , SocialConnect.class));
            }
        });


        LinearLayout itemHolder7 = (LinearLayout) rootView.findViewById(R.id.home_row_item_seven);
        ImageView icon7 = (ImageView) itemHolder7.findViewById(R.id.home_iv_row_item);
        icon7.setImageResource(R.drawable.home_faculty);
        TextView text7 = (TextView) itemHolder7.findViewById(R.id.home_tv_row_item);
        text7.setText("Faculty");

        LinearLayout row_iv_itemHolder7 = (LinearLayout) itemHolder7.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity() , "Clicked" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity() , Faculty.class));
            }
        });

        LinearLayout itemHolder8 = (LinearLayout) rootView.findViewById(R.id.home_row_item_eight);
        ImageView icon8 = (ImageView) itemHolder8.findViewById(R.id.home_iv_row_item);
        icon8.setImageResource(R.drawable.home_gallery);
        TextView text8 = (TextView) itemHolder8.findViewById(R.id.home_tv_row_item);
        text8.setText("Gallery");

        LinearLayout row_iv_itemHolder8 = (LinearLayout) itemHolder8.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , Gallery.class));
            }
        });

        LinearLayout itemHolder9 = (LinearLayout) rootView.findViewById(R.id.home_row_item_nine);
        ImageView icon9 = (ImageView) itemHolder9.findViewById(R.id.home_iv_row_item);
        icon9.setImageResource(R.drawable.home_seminar_workshop);
        TextView text9 = (TextView) itemHolder9.findViewById(R.id.home_tv_row_item);
        //Added by-Dixit Chauhan      :03/06/2016
        text9.setText("Events");


        LinearLayout row_iv_itemHolder9 = (LinearLayout) itemHolder9.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() , Events.class)); //Added by-Dixit Chauhan      :03/06/2016
            }
        });


        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_row_item_one:
                Toast.makeText(getActivity() , "Clicked 1" , Toast.LENGTH_SHORT).show();
        }
    }

    public void  alertPopup(){
        AlertDialog alertDialog = new AlertDialog.Builder(
                getActivity()).create();

        alertDialog.setTitle("Alert");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("This application wants to change your GPS setting for location.");
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });

        alertDialog.show();
    }



}
