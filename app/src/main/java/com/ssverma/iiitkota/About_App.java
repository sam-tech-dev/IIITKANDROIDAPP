package com.ssverma.iiitkota;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class About_App extends AppCompatActivity {

    private CircleImageView dev_one;
    private CircleImageView dev_two;
    private CircleImageView dev_three;
    private CircleImageView dev_four;
    private CircleImageView dev_five;
    private CircleImageView dev_six;


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


        Picasso.with(this).load("https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/c27.0.160.160/p160x160/10392163_944648218960290_4562123902925099994_n.jpg?oh=6c8a1408814c33a933f7f3634c7b983a&oe=5800302E").placeholder(R.drawable.person_placeholder).into(dev_one);
        Picasso.with(this).load("https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/12006092_1719353364959817_1628854945237067264_n.jpg?oh=16f9398230f76457dbc4ca3da27098b2&oe=57C13305").placeholder(R.drawable.person_placeholder).into(dev_two);
        Picasso.with(this).load("https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/11260419_805267512903854_3562209408148002311_n.jpg?oh=d93ca4f336325afc48d04957f9f735d8&oe=5800F4F4").placeholder(R.drawable.person_placeholder).into(dev_three);
        Picasso.with(this).load("https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/12938198_1670918016491633_5127270538560902282_n.jpg?oh=25684adc1eb9a9f0d91c392b4422764e&oe=57F0D010").placeholder(R.drawable.person_placeholder).into(dev_four);
        Picasso.with(this).load("https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/12805891_1880995908793575_1508094476518186958_n.jpg?oh=f4bf4a06a3f4e268711b2ebf7ae56437&oe=57C6CF12").placeholder(R.drawable.person_placeholder).into(dev_five);
        Picasso.with(this).load("https://scontent.fbom1-2.fna.fbcdn.net/v/t1.0-1/p160x160/10429485_571307523001573_2518119906904915383_n.jpg?oh=f336b052e69f855a84c7087dba1a52ba&oe=58056CE9")
                .placeholder(R.drawable.person_placeholder).into(dev_six);

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
