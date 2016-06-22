package com.ssverma.iiitkota.admission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.ssverma.iiitkota.R;

public class Admission_Home extends AppCompatActivity implements Admission_Home_Adapter.RCVClickListener{

    private RecyclerView recyclerView;
    private Admission_Home_Adapter adapter;

    private String[] icon_names = {"Admission Procedure" , "Office Contacts" , "Hostel Admission" , "FAQs" , "Write your Queries" , "Statistics Admission 2015-16"};
    private int[] icons = {R.drawable.admission_procedure , R.drawable.admission_contacts , R.drawable.admission_hostel , R.drawable.admission_faq , R.drawable.admission_query , R.drawable.admission_statistics};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));

        adapter = new Admission_Home_Adapter(icon_names , icons);
        recyclerView.setAdapter(adapter);

        adapter.setRCVClickListener(this);
    }

    @Override
    public void onRCVClick(View v, int position) {
        switch (position){
            case 0:
                startActivity(new Intent(this , AdmissionProcedure.class));
                break;
            case 1:
                startActivity(new Intent(this , OfficeContacts.class));
                break;
            case 2:
                startActivity(new Intent(this , HostelAdmission.class));
                break;
            case 3:
                startActivity(new Intent(this , FAQs.class));
                break;
            case 4:
                startActivity(new Intent(this , WriteQueries.class));
                break;
            case 5:
                startActivity(new Intent(this , AdmissionStatistics.class));
                break;
        }
    }
}
