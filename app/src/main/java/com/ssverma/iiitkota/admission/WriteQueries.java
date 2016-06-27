package com.ssverma.iiitkota.admission;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import com.ssverma.iiitkota.R;
import com.ssverma.iiitkota.ServerConnection;
import com.ssverma.iiitkota.ServerContract;

public class WriteQueries extends AppCompatActivity {

    private EditText et_name;
    private EditText et_email;
    private EditText et_contact;
    private EditText et_location;
    private EditText et_query;

    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_queries);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(false);

        et_name = (EditText) findViewById(R.id.wq_name);
        et_email = (EditText) findViewById(R.id.wq_email);
        et_contact = (EditText) findViewById(R.id.wq_contact);
        et_location = (EditText) findViewById(R.id.wq_location);
        et_query = (EditText) findViewById(R.id.wq_query);

        submit = (Button) findViewById(R.id.submit_button);

        if (!ServerConnection.isNetworkAvailable(this)){
            Snackbar.make(findViewById(R.id.wq_root_coordinator), "No internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Action", null).show();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String contact = et_contact.getText().toString();
                String location = et_location.getText().toString();
                String query = et_query.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(contact) || TextUtils.isEmpty(location) || TextUtils.isEmpty(query)){
                    Toast.makeText(WriteQueries.this , "Fill all fields" , Toast.LENGTH_LONG).show();
                }else if (!isValidEmail(email)){
                    Toast.makeText(WriteQueries.this , "Invalid email address" , Toast.LENGTH_LONG).show();
                }else if (!isValidMobileNo(contact)){
                    Toast.makeText(WriteQueries.this , "Invalid Contact Number" , Toast.LENGTH_LONG).show();
                }else {

                    String param1 = null;
                    String param2 = null;
                    String param3 = null;
                    String param4 = null;
                    String param5 = null;

                    try {
                        param1 = "name=" + URLEncoder.encode(name , "utf-8");
                        param2 = "email=" + URLEncoder.encode(email , "utf-8");
                        param3 = "contact=" + URLEncoder.encode(contact , "utf-8");
                        param4 = "location=" + URLEncoder.encode(location , "utf-8");
                        param5 = "query=" + URLEncoder.encode(query , "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                    String url = ServerContract.getAdmissionQueriesUrl();

                    if (ServerConnection.isNetworkAvailable(WriteQueries.this)){
                        new ServerAsync().execute(url, param1, param2, param3, param4, param5);
                    }else {
                        //Toast.makeText(WriteQueries.this , "No internet Connection" , Toast.LENGTH_LONG).show();
                        Snackbar.make(findViewById(R.id.wq_root_coordinator), "No internet Connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    private boolean isValidMobileNo(String phone)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length() < 10 || phone.length() > 10)
            {
                check = false;
                et_contact.setError("Not a valid number");
            }
            else
            {
                check = true;
            }
        }
        else
        {
            check=false;
        }
        return check;
    }

    public class ServerAsync extends AsyncTask<String , Void , String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(WriteQueries.this);
            dialog.setMessage("Submitting ... ");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0] , params[1] , params[2] , params[3] , params[4] , params[5]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            dialog.dismiss();

            Toast.makeText(WriteQueries.this , "Query Submitted ", Toast.LENGTH_LONG).show();

            finish();

        }

    }

}
