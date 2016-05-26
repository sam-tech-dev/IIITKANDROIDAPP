package com.ssverma.iiitkota;


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


    public Home_fragment_1() {
        // Required empty public constructor
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

        LinearLayout itemHolder2 = (LinearLayout) rootView.findViewById(R.id.home_row_item_two);
        ImageView icon2 = (ImageView) itemHolder2.findViewById(R.id.home_iv_row_item);
        icon2.setImageResource(R.drawable.home_programs);
        TextView text2 = (TextView) itemHolder2.findViewById(R.id.home_tv_row_item);
        text2.setText("Programs");


        LinearLayout itemHolder3 = (LinearLayout) rootView.findViewById(R.id.home_row_item_three);
        ImageView icon3 = (ImageView) itemHolder3.findViewById(R.id.home_iv_row_item);
        icon3.setImageResource(R.drawable.home_newsfeed);
        TextView text3 = (TextView) itemHolder3.findViewById(R.id.home_tv_row_item);
        text3.setText("News Feed");

        LinearLayout itemHolder4 = (LinearLayout) rootView.findViewById(R.id.home_row_item_four);
        ImageView icon4 = (ImageView) itemHolder4.findViewById(R.id.home_iv_row_item);
        icon4.setImageResource(R.drawable.home_maps);
        TextView text4 = (TextView) itemHolder4.findViewById(R.id.home_tv_row_item);
        text4.setText("Map");

        LinearLayout itemHolder5 = (LinearLayout) rootView.findViewById(R.id.home_row_item_five);
        ImageView icon5 = (ImageView) itemHolder5.findViewById(R.id.home_iv_row_item);
        icon5.setImageResource(R.drawable.home_about_us);
        TextView text5 = (TextView) itemHolder5.findViewById(R.id.home_tv_row_item);
        text5.setText("About IIITK");

        LinearLayout itemHolder6 = (LinearLayout) rootView.findViewById(R.id.home_row_item_six);
        ImageView icon6 = (ImageView) itemHolder6.findViewById(R.id.home_iv_row_item);
        icon6.setImageResource(R.drawable.home_registration);
        TextView text6 = (TextView) itemHolder6.findViewById(R.id.home_tv_row_item);
        text6.setText("Social Connect");

        LinearLayout itemHolder7 = (LinearLayout) rootView.findViewById(R.id.home_row_item_seven);
        ImageView icon7 = (ImageView) itemHolder7.findViewById(R.id.home_iv_row_item);
        icon7.setImageResource(R.drawable.home_registration);
        TextView text7 = (TextView) itemHolder7.findViewById(R.id.home_tv_row_item);
        text7.setText("Faculty");

        LinearLayout itemHolder8 = (LinearLayout) rootView.findViewById(R.id.home_row_item_eight);
        ImageView icon8 = (ImageView) itemHolder8.findViewById(R.id.home_iv_row_item);
        icon8.setImageResource(R.drawable.home_registration);
        TextView text8 = (TextView) itemHolder8.findViewById(R.id.home_tv_row_item);
        text8.setText("Gallery");

        LinearLayout itemHolder9 = (LinearLayout) rootView.findViewById(R.id.home_row_item_nine);
        ImageView icon9 = (ImageView) itemHolder9.findViewById(R.id.home_iv_row_item);
        icon9.setImageResource(R.drawable.home_programs);
        TextView text9 = (TextView) itemHolder9.findViewById(R.id.home_tv_row_item);
        text9.setText("Seminars/Workshops");


        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_row_item_one:
                Toast.makeText(getActivity() , "Clicked 1" , Toast.LENGTH_SHORT).show();
        }
    }
}
