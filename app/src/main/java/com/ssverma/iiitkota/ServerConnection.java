package com.ssverma.iiitkota;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IIITK on 5/30/2016.
 */
public class ServerConnection {

    public static String obtainServerResponse(String _url , String urlParameters){
        String s = _url+"?"+urlParameters;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(s);
            System.out.println(s);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded");
//
//            connection.setRequestProperty("Content-Length", "" +
//                    Integer.toString(urlParameters.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();


            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            //result = bufferedReader.readLine();

            return sb.toString();
        }catch(Exception e){
            return "E : " + e;
        }
    }
}
