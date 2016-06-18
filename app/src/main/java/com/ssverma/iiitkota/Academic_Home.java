package com.ssverma.iiitkota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Author-Dixit Chauhan : 10/06/2016
 */
public class Academic_Home extends AppCompatActivity implements Academic_Home_Adapter.RCVClickListener{

    private RecyclerView recyclerView;
    private Academic_Home_Adapter adapter;

    private String[] icon_names = {"Academic Timetable" , "Academic Calendar" , "Curricula and Syllabi","Academic Resources" , "Sponsored Research"};
    private int[] icons = {R.drawable.home_map_ , R.drawable.home_map_ , R.drawable.home_map_ , R.drawable.home_map_ , R.drawable.home_map_};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));

        adapter = new Academic_Home_Adapter(icon_names , icons);
        recyclerView.setAdapter(adapter);

        adapter.setRCVClickListener(this);
    }

    @Override
    public void onRCVClick(View v, int position) {
        switch (position){
            case 0:
                startActivity(new Intent(this ,Academic_Timetable.class));
                break;
            case 1:
                startActivity(new Intent(this ,Academic_Calendar.class));
                break;
            case 2:
                startActivity(new Intent(this , Academic_Courses.class));
                break;
            case 3:
                startActivity(new Intent(this , Academic_Resources.class));
                break;
            case 4:
                startActivity(new Intent(this , Academic_Research.class));
                break;
        }
    }
}
