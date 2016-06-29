package com.ssverma.iiitkota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class About_App extends AppCompatActivity {

    private CircleImageView dev_one;
    private CircleImageView dev_two;
    private CircleImageView dev_three;
    private CircleImageView dev_four;
    private CircleImageView dev_five;
    private CircleImageView dev_six;

    private RelativeLayout dev_holder_one;
    private RelativeLayout dev_holder_two;
    private RelativeLayout dev_holder_three;
    private RelativeLayout dev_holder_four;
    private RelativeLayout dev_holder_five;
    private RelativeLayout dev_holder_six;

    private String dev_one_image_link = "https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/c27.0.160.160/p160x160/10392163_944648218960290_4562123902925099994_n.jpg?oh=6c8a1408814c33a933f7f3634c7b983a&oe=5800302E";
    private String dev_two_image_link = "https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/12006092_1719353364959817_1628854945237067264_n.jpg?oh=16f9398230f76457dbc4ca3da27098b2&oe=57C13305";
    private String dev_three_image_link = "https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/12805891_1880995908793575_1508094476518186958_n.jpg?oh=f4bf4a06a3f4e268711b2ebf7ae56437&oe=57C6CF12";
    private String dev_four_image_link = "https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/11260419_805267512903854_3562209408148002311_n.jpg?oh=d93ca4f336325afc48d04957f9f735d8&oe=5800F4F4";
    private String dev_five_image_link = "https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-9/13495101_832003993598590_1791213321995975730_n.jpg?oh=3f58d58f3a0fa42882942b9e7fd801ac&oe=57EEEC1A";//"https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/10429485_571307523001573_2518119906904915383_n.jpg?oh=f336b052e69f855a84c7087dba1a52ba&oe=58056CE9";
    private String dev_six_image_link = "https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/12938198_1670918016491633_5127270538560902282_n.jpg?oh=25684adc1eb9a9f0d91c392b4422764e&oe=57F0D010";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__app);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");


        findViewById(R.id.license_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLicenseDialogFragment();
            }
        });


        dev_one = (CircleImageView) findViewById(R.id.dev_one_image);
        dev_two = (CircleImageView) findViewById(R.id.dev_two_image);
        dev_three = (CircleImageView) findViewById(R.id.dev_three_image);
        dev_four = (CircleImageView) findViewById(R.id.dev_four_image);
        dev_five = (CircleImageView) findViewById(R.id.dev_five_image);
        dev_six = (CircleImageView) findViewById(R.id.dev_six_image);

        dev_holder_one = (RelativeLayout) findViewById(R.id.dev_holder_one);
        dev_holder_two = (RelativeLayout) findViewById(R.id.dev_holder_two);
        dev_holder_three = (RelativeLayout) findViewById(R.id.dev_holder_three);
        dev_holder_four = (RelativeLayout) findViewById(R.id.dev_holder_four);
        dev_holder_five = (RelativeLayout) findViewById(R.id.dev_holder_five);
        dev_holder_six = (RelativeLayout) findViewById(R.id.dev_holder_six);


        Picasso.with(this).load(dev_one_image_link).placeholder(R.drawable.person_placeholder).into(dev_one);
        Picasso.with(this).load(dev_two_image_link).placeholder(R.drawable.person_placeholder).into(dev_two);
        Picasso.with(this).load(dev_three_image_link).placeholder(R.drawable.person_placeholder).into(dev_three);
        Picasso.with(this).load(dev_four_image_link).placeholder(R.drawable.person_placeholder).into(dev_four);
        Picasso.with(this).load(dev_five_image_link).placeholder(R.drawable.person_placeholder).into(dev_five);
        Picasso.with(this).load(dev_six_image_link)
                .placeholder(R.drawable.person_placeholder).into(dev_six);


        dev_holder_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dev_data = {dev_one_image_link , "Shyam Sunder Verma" , "Project Head"};

                About_Dev_DialogFragment dialog = About_Dev_DialogFragment.newInstance(dev_data);
                dialog.show(getSupportFragmentManager() , "About Dev");
            }
        });

        dev_holder_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dev_data = {dev_two_image_link , "Sattar Mohammad" , "API Manager"};

                About_Dev_DialogFragment dialog = About_Dev_DialogFragment.newInstance(dev_data);
                dialog.show(getSupportFragmentManager() , "About Dev");
            }
        });

        dev_holder_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dev_data = {dev_three_image_link , "Dixit Chauhan" , "Database Manager"};

                About_Dev_DialogFragment dialog = About_Dev_DialogFragment.newInstance(dev_data);
                dialog.show(getSupportFragmentManager() , "About Dev");
            }
        });

        dev_holder_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dev_data = {dev_four_image_link , "Rajat Jain" , "Senior Developer"};

                About_Dev_DialogFragment dialog = About_Dev_DialogFragment.newInstance(dev_data);
                dialog.show(getSupportFragmentManager() , "About Dev");
            }
        });

        dev_holder_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dev_data = {dev_five_image_link , "Abhishek Mimani" , "Senior Developer"};

                About_Dev_DialogFragment dialog = About_Dev_DialogFragment.newInstance(dev_data);
                dialog.show(getSupportFragmentManager() , "About Dev");
            }
        });

        dev_holder_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] dev_data = {dev_six_image_link , "Rahul Choudhary" , "Senior Developer"};

                About_Dev_DialogFragment dialog = About_Dev_DialogFragment.newInstance(dev_data);
                dialog.show(getSupportFragmentManager() , "About Dev");
            }
        });


    }

    private void displayLicenseDialogFragment() {
        LicenseDialogFragment dialog = LicenseDialogFragment.newInstance();
        dialog.show(getSupportFragmentManager(), "LicenseDialog");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
