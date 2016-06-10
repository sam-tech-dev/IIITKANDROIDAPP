package com.ssverma.iiitkota;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_fragment_2 extends Fragment {


    public Home_fragment_2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home_fragment_2, container, false);

        LinearLayout itemHolder1 = (LinearLayout) rootView.findViewById(R.id.home_row_item_one);
        ImageView icon1 = (ImageView) itemHolder1.findViewById(R.id.home_iv_row_item);
        icon1.setImageResource(R.drawable.home_contact);
        TextView text1 = (TextView) itemHolder1.findViewById(R.id.home_tv_row_item);
        text1.setText("Contacts");


        LinearLayout row_iv_itemHolder1 = (LinearLayout) itemHolder1.findViewById(R.id.home_iv_row_item_holder);

        row_iv_itemHolder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity() , "Clicked" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity() , Contact.class));
            }
        });


        LinearLayout itemHolder2 = (LinearLayout) rootView.findViewById(R.id.home_row_item_two);
        ImageView icon2 = (ImageView) itemHolder2.findViewById(R.id.home_iv_row_item);
        icon2.setImageResource(R.drawable.home_placement);
        TextView text2 = (TextView) itemHolder2.findViewById(R.id.home_tv_row_item);
        text2.setText("Placement");

        LinearLayout row_iv_itemHolder2 = (LinearLayout) itemHolder2.findViewById(R.id.home_iv_row_item_holder);




        LinearLayout itemHolder3 = (LinearLayout) rootView.findViewById(R.id.home_row_item_three);
        ImageView icon3 = (ImageView) itemHolder3.findViewById(R.id.home_iv_row_item);
        icon3.setImageResource(R.drawable.home_scholership);
        TextView text3 = (TextView) itemHolder3.findViewById(R.id.home_tv_row_item);
        text3.setText("Scholarships");

        LinearLayout row_iv_itemHolder3 = (LinearLayout) itemHolder3.findViewById(R.id.home_iv_row_item_holder);


        LinearLayout itemHolder4 = (LinearLayout) rootView.findViewById(R.id.home_row_item_four);
        ImageView icon4 = (ImageView) itemHolder4.findViewById(R.id.home_iv_row_item);
        icon4.setImageResource(R.drawable.home_alumni);
        TextView text4 = (TextView) itemHolder4.findViewById(R.id.home_tv_row_item);
        text4.setText("Alumni");

        LinearLayout itemHolder5 = (LinearLayout) rootView.findViewById(R.id.home_row_item_five);
        ImageView icon5 = (ImageView) itemHolder5.findViewById(R.id.home_iv_row_item);
        icon5.setImageResource(R.drawable.home_newsfeed);
        TextView text5 = (TextView) itemHolder5.findViewById(R.id.home_tv_row_item);
        text5.setText("Extras");

        LinearLayout itemHolder6 = (LinearLayout) rootView.findViewById(R.id.home_row_item_six);
        ImageView icon6 = (ImageView) itemHolder6.findViewById(R.id.home_iv_row_item);
        icon6.setImageResource(R.drawable.home_registration);
        TextView text6 = (TextView) itemHolder6.findViewById(R.id.home_tv_row_item);
        text6.setText("Campus-Life");

        LinearLayout row_iv_itemHolder6 = (LinearLayout) itemHolder6.findViewById(R.id.home_iv_row_item_holder);

        LinearLayout itemHolder7 = (LinearLayout) rootView.findViewById(R.id.home_row_item_seven);
        ImageView icon7 = (ImageView) itemHolder7.findViewById(R.id.home_iv_row_item);
        icon7.setImageResource(R.drawable.home_registration);
        TextView text7 = (TextView) itemHolder7.findViewById(R.id.home_tv_row_item);
        text7.setText("...");

        LinearLayout itemHolder8 = (LinearLayout) rootView.findViewById(R.id.home_row_item_eight);
        ImageView icon8 = (ImageView) itemHolder8.findViewById(R.id.home_iv_row_item);
        icon8.setImageResource(R.drawable.home_registration);
        TextView text8 = (TextView) itemHolder8.findViewById(R.id.home_tv_row_item);
        text8.setText("...");

        LinearLayout itemHolder9 = (LinearLayout) rootView.findViewById(R.id.home_row_item_nine);
        ImageView icon9 = (ImageView) itemHolder9.findViewById(R.id.home_iv_row_item);
        icon9.setImageResource(R.drawable.home_programs);
        icon9.setVisibility(View.INVISIBLE);
        TextView text9 = (TextView) itemHolder9.findViewById(R.id.home_tv_row_item);
        text9.setText("Seminars/Workshops");
        text9.setVisibility(View.INVISIBLE);

        return rootView;
    }

}
