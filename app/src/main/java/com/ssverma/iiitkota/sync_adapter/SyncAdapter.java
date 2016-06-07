package com.ssverma.iiitkota.sync_adapter;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.ssverma.iiitkota.ServerConnection;
import com.ssverma.iiitkota.ServerContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IIITK on 6/6/2016.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter{

    ContentResolver contentResolver;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);

        contentResolver = context.getContentResolver();
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);

        contentResolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {

        Handler h = new Handler(Looper.getMainLooper());
        h.post(new Runnable() {
            public void run() {
                syncServerToLocal_Faculty();
            }
        });

    }


    /*Create method like this , according to your module*/
                                   /*replace 'Faculty' by Module Name*/
    private void syncServerToLocal_Faculty() {
        new ServerAsync_Faculty().execute(ServerContract.getFacultyPhpUrl() , "dept=CS");
        new ServerAsync_Faculty().execute(ServerContract.getFacultyPhpUrl() , "dept=ECE");
    }


    // ... create methods here

    /* Fetching Faculty Module Data From Server*/

    public class ServerAsync_Faculty extends AsyncTask<String , Void , String> {


        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0] , params[1]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<ContentValues> list = parseFacultyJSON(response);
            syncDataToFacultyLocal(list);

        }

        private void syncDataToFacultyLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.FacultyTable.FACULTY_SERVER_ID + " = ?" + " AND "
                    + DatabaseContract.FacultyTable.FACULTY_DEPARTMENT + " = ?";
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list){
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.FacultyTable.FACULTY_SERVER_ID)
                        , contentValues.getAsString(DatabaseContract.FacultyTable.FACULTY_DEPARTMENT)};

                int rowAffected = contentResolver.update(DatabaseContract.FACULTY_CONTENT_URI , contentValues , selectionClause , selectionArgs);

                if (rowAffected == 0){
                    Uri uri = contentResolver.insert(DatabaseContract.FACULTY_CONTENT_URI , contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0){
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.FACULTY_CONTENT_URI , null);
            }
        }


        private ArrayList<ContentValues> parseFacultyJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_SERVER_ID , jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_NAME , jsonObject.getString("Name"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_ID , jsonObject.getString("FacultyId"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_EMAIL , jsonObject.getString("Email"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_DOB , jsonObject.getString("DateOfBirth"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_DEPARTMENT , jsonObject.getString("Department"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_CONTACT , jsonObject.getString("Contact"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_IMAGE , jsonObject.getString("Image"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_QUALIFICATION , jsonObject.getString("Qualification"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_HOMETOWN , jsonObject.getString("Hometown"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_DESIGNATION , jsonObject.getString("Designation"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_ACHIEVEMENTS , jsonObject.getString("Achievements"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_SUMMARY , jsonObject.getString("Summary"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_RESEARCH_AREAS , jsonObject.getString("ResearchAreas"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_FACEBOOK , jsonObject.getString("Facebook"));

                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }


    //... Create Your Async and 'parseJSON Method'

}
