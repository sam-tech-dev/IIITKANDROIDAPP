package com.ssverma.iiitkota.gcm;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class RegisterOnServer  {

    Context context;
    String parameters = null;
    String token=null;
    final String serverUrl = ServerUrls.registerUrl;



     public RegisterOnServer(Context conn) {
        context=conn;
         new GetToken().execute();
     }

    class CustomAsyncTask extends AsyncTask<String, String, String> {



        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();



        }

        @Override
        protected String doInBackground(String... params) {

            URL url = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length",Integer.toString(parameters.getBytes().length));
                connection.setRequestProperty("Content-Language","en-US");
                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                DataOutputStream rt = new DataOutputStream(connection.getOutputStream());
                rt.write(parameters.getBytes());
                rt.flush();
                rt.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String s = br.readLine();

                return s;
            } catch (Exception e) {
                return "E:" + e;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
                if(result.equals("You have Successfully Registered")){
                    SharedPreferences sharedpreferences = context.getSharedPreferences("iiitkota", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedpreferences.edit();
                    edit.putString("registerCheck","true");
                    edit.commit();
                }else{
                    Toast.makeText(context, "error 404", Toast.LENGTH_SHORT).show();
                }
        }
    }


    class GetToken extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            boolean isDataFetched = false;
            while(!isDataFetched)
            {
                token = registrationService.getToken();
                if(token!=null)
                {
                    isDataFetched = true;
                }

            }

            return  null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
                parameters = "regId=" + URLEncoder.encode(token, "UTF-8");

            }
            catch (UnsupportedEncodingException e){
                Log.d("exception",e.getMessage());
            }
            new CustomAsyncTask().execute(serverUrl);
        }
    }











}

