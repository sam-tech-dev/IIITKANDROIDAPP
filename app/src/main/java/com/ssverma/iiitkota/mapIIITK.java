package com.ssverma.iiitkota;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class mapIIITK extends AppCompatActivity implements OnMapReadyCallback , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    ImageButton pBhavan,dispen,auro,basket,gargi,mainagate;
    Marker m;
    GoogleMap ma;
    LatLng myLocation;
    Polyline polylineFinal=null;

    protected GoogleApiClient mGoogleApiClient;

    LocationManager locationManager ;
    protected Location mLastLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_iiitk);

        locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        buildGoogleApiClient();
        mGoogleApiClient.connect();
        new GetLocation().execute();

        pBhavan=(ImageButton)findViewById(R.id.prabhavan);
        pBhavan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(polylineFinal!=null){
                    polylineFinal.remove();
                }

                drawRoute(myLocation,new LatLng(26.864292, 75.810683));
                m.setPosition(new LatLng(26.864292, 75.810683));
                m.setTitle("MNIT Design Center");
                m.showInfoWindow();
                m.setVisible(true);
                m.setDraggable(true);
                ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.865141, 75.807810), 15));

            }
        });


        dispen=(ImageButton)findViewById(R.id.dispens);
        dispen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(polylineFinal!=null){
                    polylineFinal.remove();
                }

                drawRoute(myLocation,new LatLng(26.862231, 75.812241));
                m.setPosition(new LatLng(26.862231, 75.812241));
                m.setTitle("MNIT Dispensary");
                m.showInfoWindow();
                m.setVisible(true);
                m.setDraggable(true);
                ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.862231, 75.812241), 15));

            }
        });



        auro=(ImageButton)findViewById(R.id.aurob);
        auro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(polylineFinal!=null){
                    polylineFinal.remove();
                }

                drawRoute(myLocation,new LatLng(26.862745, 75.820356));
                m.setPosition(new LatLng(26.862745, 75.820356));
                m.setTitle("Aurobindo Hostel");
                m.showInfoWindow();
                m.setVisible(true);
                m.setDraggable(true);
                ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.862745, 75.820356), 15));

            }
        });

        basket=(ImageButton)findViewById(R.id.basketb);
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(polylineFinal!=null){
                    polylineFinal.remove();
                }
                drawRoute(myLocation,new LatLng(26.861734, 75.814705));
                m.setPosition(new LatLng(26.861734, 75.814705));
                m.setTitle("MNIT Basketball Court");
                m.showInfoWindow();
                m.setVisible(true);
                m.setDraggable(true);
                ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.861734, 75.814705), 15));

            }
        });



        gargi=(ImageButton)findViewById(R.id.gargi);
        gargi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(polylineFinal!=null){
                    polylineFinal.remove();
                }
                drawRoute(myLocation,new LatLng(26.864424, 75.815382));
                m.setPosition(new LatLng(26.864424, 75.815382));
                m.setTitle("Gargi Hostel");
                m.showInfoWindow();
                m.setVisible(true);
                m.setDraggable(true);
                ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.864424, 75.815382), 15));

            }
        });



        mainagate=(ImageButton)findViewById(R.id.maingate);
        mainagate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(polylineFinal!=null){
                    polylineFinal.remove();
                }
                drawRoute(myLocation,new LatLng(26.865238, 75.807801));
                m.setPosition(new LatLng(26.865238, 75.807801));
                m.setTitle("MNIT Main Gate");
                m.showInfoWindow();
                m.setVisible(true);
                m.setDraggable(true);
                ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.865238, 75.807801),15));

            }
        });

    }



    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }




    @Override
    public void onMapReady(GoogleMap map) {


        ma=map;

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        ma.setMyLocationEnabled(true);
        ma.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.8626, 75.8186), 15));
        m= map.addMarker(new MarkerOptions()
                .title("MNIT Jaipur")
                .position(new LatLng(26.8626, 75.8186)));


    }






    void drawRoute(LatLng orig,LatLng dest){


        Log.d("azad",orig.latitude+" origin "+orig.longitude);
        Log.d("azad",dest.latitude+" dest "+dest.longitude);

        // LatLng dest= new LatLng(26.861734, 75.814705);
        // LatLng orig=  new LatLng(26.864292, 75.810683);
        String url = getdirecUrl(orig, dest);

        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(url);


    }




    private String getdirecUrl(LatLng origin,LatLng dest) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }



    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }


        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);



        if (mLastLocation != null) {

            Log.d("azad",mLastLocation.getLatitude()+"az"+mLastLocation.getLongitude());


        } else {
            // Toast.makeText(this,"location is not find", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

        Log.i("az", "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.i("az", "Connection suspended");
        mGoogleApiClient.connect();
    }


    private class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }



    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }


        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();


            for(int i=0;i<result.size();i++){
                ArrayList<LatLng>  points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();


                List<HashMap<String, String>> path = result.get(i);


                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(3);
                lineOptions.color(Color.BLUE);
            }


            polylineFinal=  ma.addPolyline(lineOptions);
        }
    }




    class GetLocation extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;


        @Override
        protected Void doInBackground(Void... params) {

            boolean isDataFetched = false;

            while(!isDataFetched)
            {
                if(mLastLocation!=null)
                {
                    isDataFetched = true;
                }
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            progressDialog = new ProgressDialog(mapIIITK.this);
            progressDialog.setMessage("Getting Location...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(1);
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog != null){
                progressDialog.dismiss();
            }


            myLocation=new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());


            MapFragment mapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.fragment);

            mapFragment.getMapAsync(mapIIITK.this);


        }
    }





}
