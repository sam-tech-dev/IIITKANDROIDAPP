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
import android.widget.Toast;

import com.ssverma.iiitkota.IIITK_Singleton;
import com.ssverma.iiitkota.ServerConnection;
import com.ssverma.iiitkota.ServerContract;
import com.ssverma.iiitkota.Splash;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IIITK on 6/6/2016.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private ContentResolver contentResolver;
    private static SyncCompletionListener listener;

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
              //  syncServerToLocal_Fest();
                syncServerToLocal_Faculty();
                syncServerToLocal_Contact();
                syncServerToLocal_Administration();
                syncServerToLocal_Events();
                syncServerToLocal_Scholarship();

                syncServerToLocal_News();
                syncServerToLocal_Campus();

                IIITK_Singleton.getInstance().setPreferencesValue(true);

            }
        });

    }


    /*Create method like this , according to your module*/
                                   /*replace 'Faculty' by Module Name*/
    private void syncServerToLocal_Faculty() {
        new ServerAsync_Faculty().execute(ServerContract.getFacultyPhpUrl());
        //new ServerAsync_Faculty().execute(ServerContract.getFacultyPhpUrl(), "dept=ECE");
    }

    private void syncServerToLocal_Events() {
        new ServerAsync_Events().execute(ServerContract.getEventsPhpUrl(), "filter=prev");
        new ServerAsync_Events().execute(ServerContract.getEventsPhpUrl(), "filter=upcoming");
        new ServerAsync_Events().execute(ServerContract.getEventsPhpUrl(), "filter=latest");
    }

    private void syncServerToLocal_Scholarship() {
        new ServerAsync_Scholarship().execute(ServerContract.getScholarshipPhpUrl());
    }

    //CONTACT
    private void syncServerToLocal_Contact() {
        new ServerAsync_Contact().execute(ServerContract.getContactsPhpUrl());
    }

    //ADMINISTRATION
    private void syncServerToLocal_Administration() {
        new ServerAsync_Administration().execute(ServerContract.getAdministrationPhpUrl());
    }

    private void syncServerToLocal_News() {
        new ServerAsync_News().execute(ServerContract.getNewsPhpUrl(), "filter=prev");
        new ServerAsync_News().execute(ServerContract.getNewsPhpUrl(), "filter=upcoming");
        new ServerAsync_News().execute(ServerContract.getNewsPhpUrl(), "filter=latest");

    }

    //campus life

    private void syncServerToLocal_Campus() {
        new ServerAsync_Campus().execute(ServerContract.getCampusPhpUrl());
    }

//    //FEST
//
//    private void syncServerToLocal_Fest() {
//        new ServerAsync_Fest().execute(ServerContract.getFestPhpUrl());
//    }

    // ... create methods here

    /* Fetching Faculty Module Data From Server*/

    public class ServerAsync_Faculty extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<ContentValues> list = parseFacultyJSON(response);
            syncDataToFacultyLocal(list);

        }

        private void syncDataToFacultyLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.FacultyTable.FACULTY_SERVER_ID + " = ?";
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.FacultyTable.FACULTY_SERVER_ID)};

                int rowAffected = contentResolver.update(DatabaseContract.FACULTY_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.FACULTY_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.FACULTY_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseFacultyJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_SERVER_ID, jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_NAME, jsonObject.getString("Name"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_ID, jsonObject.getString("FacultyId"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_EMAIL, jsonObject.getString("Email"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_DOB, jsonObject.getString("DateOfBirth"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_DEPARTMENT, jsonObject.getString("Department"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_CONTACT, jsonObject.getString("Contact"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_IMAGE, jsonObject.getString("Image"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_QUALIFICATION, jsonObject.getString("Qualification"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_HOMETOWN, jsonObject.getString("Hometown"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_DESIGNATION, jsonObject.getString("Designation"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_ACHIEVEMENTS, jsonObject.getString("Achievements"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_SUMMARY, jsonObject.getString("Summary"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_RESEARCH_AREAS, jsonObject.getString("ResearchAreas"));
                    contentValues.put(DatabaseContract.FacultyTable.FACULTY_FACEBOOK, jsonObject.getString("Facebook"));

                    list.add(contentValues);
                }
            } catch (JSONException e) {

            }

            return list;
        }
    }


    //... Create Your Async and 'parseJSON Method'

    //CONTACT

    public class ServerAsync_Contact extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<ContentValues> list = parseEventsJSON(response);
            syncDataToContactLocal(list);
        }

        private void syncDataToContactLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.ContactTable.CONTACT_SERVER_ID + " = ?";
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.ContactTable.CONTACT_SERVER_ID)};

                int rowAffected = contentResolver.update(DatabaseContract.CONTACT_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.CONTACT_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.CONTACT_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseEventsJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.ContactTable.CONTACT_SERVER_ID, jsonObject.getInt("contact_id"));
                    contentValues.put(DatabaseContract.ContactTable.CONTACT_NAME, jsonObject.getString("contact_name"));
                    contentValues.put(DatabaseContract.ContactTable.CONTACT_EMAIL, jsonObject.getString("contact_email"));
                    contentValues.put(DatabaseContract.ContactTable.CONTACT_MOBILE, jsonObject.getString("contact_mobile_no"));
                    contentValues.put(DatabaseContract.ContactTable.CONTACT_CATEGORY, jsonObject.getString("contact_category"));
                    contentValues.put(DatabaseContract.ContactTable.CONTACT_DESIGNATION, jsonObject.getString("contact_designation"));

                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }


    //ADMINISTRATION

    public class ServerAsync_Administration extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<ContentValues> list = parseEventsJSON(response);
            syncDataToAdministrationLocal(list);
        }

        private void syncDataToAdministrationLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.AdministrationTable.ADMINISTRATION_SERVER_ID + " = ?";
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.AdministrationTable.ADMINISTRATION_SERVER_ID)};

                int rowAffected = contentResolver.update(DatabaseContract.ADMINISTRATION_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.ADMINISTRATION_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.ADMINISTRATION_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseEventsJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.AdministrationTable.ADMINISTRATION_SERVER_ID, jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.AdministrationTable.ADMINISTRATION_NAME, jsonObject.getString("Name"));
                    contentValues.put(DatabaseContract.AdministrationTable.ADMINISTRATION_DESIGNATION, jsonObject.getString("Designation"));
                    contentValues.put(DatabaseContract.AdministrationTable.ADMINISTRATION_CATEGORY, jsonObject.getString("Category"));


                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }

    //... Create Your Async and 'parseJSON Method'


    public class ServerAsync_Events extends AsyncTask<String, Void, String> {


        String flag = "prev";

        @Override
        protected String doInBackground(String... params) {
            flag = params[1].substring(7);  //
            return ServerConnection.obtainServerResponse(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            //Toast.makeText(getContext(),response+" ",Toast.LENGTH_LONG).show();

            ArrayList<ContentValues> list = parseEventsJSON(response);
            syncDataToEventsLocal(list);

        }

        private void syncDataToEventsLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.EventsTable.EVENTS_SERVER_ID + " = ?" + " AND "
                    + DatabaseContract.EventsTable.EVENTS_DATE + " = ?";//
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.EventsTable.EVENTS_SERVER_ID)
                        , contentValues.getAsString(DatabaseContract.EventsTable.EVENTS_DATE)};//

                int rowAffected = contentResolver.update(DatabaseContract.EVENTS_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.EVENTS_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.EVENTS_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseEventsJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.EventsTable.EVENTS_SERVER_ID, jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.EventsTable.EVENTS_TITLE, jsonObject.getString("Title"));
                    contentValues.put(DatabaseContract.EventsTable.EVENTS_DATE, jsonObject.getString("Date"));
                    contentValues.put(DatabaseContract.EventsTable.EVENTS_SUBTITLE, jsonObject.getString("Subtitle"));
                    contentValues.put(DatabaseContract.EventsTable.EVENTS_DETAIL, jsonObject.getString("Detail"));
                    contentValues.put(DatabaseContract.EventsTable.EVENTS_AUTHOR, jsonObject.getString("Author"));
                    contentValues.put(DatabaseContract.EventsTable.EVENTS_IMAGE, jsonObject.getString("Image"));

                    contentValues.put(DatabaseContract.EventsTable.EVENTS_FLAG, flag);

                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }


//

    public class ServerAsync_Scholarship extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            ArrayList<ContentValues> list = parseScholarshipJSON(response);
            syncDataToScholarshipLocal(list);

        }

        private void syncDataToScholarshipLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.ScholarshipTable.SCHOLARSHIP_SERVER_ID + " = ?";//
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.ScholarshipTable.SCHOLARSHIP_SERVER_ID)
                };

                int rowAffected = contentResolver.update(DatabaseContract.SCHOLARSHIP_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.SCHOLARSHIP_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.SCHOLARSHIP_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseScholarshipJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_SERVER_ID, jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_NAME, jsonObject.getString("name"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_INCOME, jsonObject.getString("criteria_total_parental_income_rs"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_ACADEMIC, jsonObject.getString("criteria_academic"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_CATEGORY, jsonObject.getString("criteria_category"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_VALUE, jsonObject.getString("value"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_PROCEDURE, jsonObject.getString("procedure_for_application"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_REMARK, jsonObject.getString("remark"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_LINK, jsonObject.getString("link"));
                    contentValues.put(DatabaseContract.ScholarshipTable.SCHOLARSHIP_IMAGE, jsonObject.getString("image"));
                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }


    public class ServerAsync_News extends AsyncTask<String, Void, String> {

        String flag = "";

        @Override
        protected String doInBackground(String... params) {
            flag = params[1].substring(7);
            return ServerConnection.obtainServerResponse(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            //Toast.makeText(getContext(),response+" ",Toast.LENGTH_LONG).show();

            ArrayList<ContentValues> list = parseNewsJSON(response);
            syncDataToNewsLocal(list);

        }

        private void syncDataToNewsLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.NewsTable.NEWS_SERVER_ID + " = ?" + " AND "
                    + DatabaseContract.NewsTable.NEWS_DATE + " = ?";//
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.NewsTable.NEWS_SERVER_ID)
                        , contentValues.getAsString(DatabaseContract.NewsTable.NEWS_DATE)};//

                int rowAffected = contentResolver.update(DatabaseContract.NEWS_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.NEWS_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.NEWS_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseNewsJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.NewsTable.NEWS_SERVER_ID, jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_TITLE, jsonObject.getString("Title"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_DATE, jsonObject.getString("Date"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_SUBTITLE, jsonObject.getString("Subtitle"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_DETAIL, jsonObject.getString("Detail"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_AUTHOR, jsonObject.getString("Author"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_IMAGE, jsonObject.getString("Image"));
                    contentValues.put(DatabaseContract.NewsTable.NEWS_FLAG, flag);
                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }


    // campus life


    public class ServerAsync_Campus extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            return ServerConnection.obtainServerResponse(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            //Toast.makeText(getContext(),response+" ",Toast.LENGTH_LONG).show();

            ArrayList<ContentValues> list = parseCampusJSON(response);
            syncDataToCampusLocal(list);

        }

        private void syncDataToCampusLocal(ArrayList<ContentValues> list) {

            String selectionClause = DatabaseContract.CampusTable.CAMPUS_SERVER_ID + " = ?" + " AND "
                    + DatabaseContract.CampusTable.CAMPUS_TITLE + " = ?";//
            String[] selectionArgs = null;

            int newRowAdded = 0;

            for (ContentValues contentValues : list) {
                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.CampusTable.CAMPUS_SERVER_ID)
                        , contentValues.getAsString(DatabaseContract.CampusTable.CAMPUS_TITLE)};//

                int rowAffected = contentResolver.update(DatabaseContract.CAMPUS_CONTENT_URI, contentValues, selectionClause, selectionArgs);

                if (rowAffected == 0) {
                    Uri uri = contentResolver.insert(DatabaseContract.CAMPUS_CONTENT_URI, contentValues);
                    long rowId = ContentUris.parseId(uri);
                    if (rowId > 0) {
                        newRowAdded++;
                    }
                }

                contentResolver.notifyChange(DatabaseContract.CAMPUS_CONTENT_URI, null);
            }
        }


        private ArrayList<ContentValues> parseCampusJSON(String response) {

            ArrayList<ContentValues> list = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_SERVER_ID, jsonObject.getInt("id"));
                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_TITLE, jsonObject.getString("Title"));
                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_DETAIL, jsonObject.getString("Description"));
                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_IMAGE, jsonObject.getString("image_link"));


                    list.add(contentValues);
                }
            } catch (JSONException e) {
                //tv.setText("JSON E:" + e);
            }

            return list;
        }
    }



















//
//    // FEST
//
//
//    public class ServerAsync_Fest extends AsyncTask<String, Void, String> {
//
//
//        @Override
//        protected String doInBackground(String... params) {
//            return ServerConnection.obtainServerResponse(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String response) {
//            super.onPostExecute(response);
//
//            Toast.makeText(getContext(),response+" : Response ", Toast.LENGTH_LONG).show();
//
//            ArrayList<ContentValues> list = parseCampusJSON(response);
//            syncDataToFestLocal(list);
//
//        }
//
//        private void syncDataToFestLocal(ArrayList<ContentValues> list) {
//
//            String selectionClause = DatabaseContract.FestTable.FEST_SERVER_ID + " = ?" + " AND "
//                    + DatabaseContract.FestTable.FEST_NAME + " = ?";//
//            String[] selectionArgs = null;
//
//            int newRowAdded = 0;
//
//            for (ContentValues contentValues : list) {
//                selectionArgs = new String[]{contentValues.getAsString(DatabaseContract.FestTable.FEST_SERVER_ID)
//                        , contentValues.getAsString(DatabaseContract.FestTable.FEST_NAME)};//
//
//                int rowAffected = contentResolver.update(DatabaseContract.FEST_CONTENT_URI, contentValues, selectionClause, selectionArgs);
//
//                if (rowAffected == 0) {
//                    Uri uri = contentResolver.insert(DatabaseContract.FEST_CONTENT_URI, contentValues);
//                    long rowId = ContentUris.parseId(uri);
//                    if (rowId > 0) {
//                        newRowAdded++;
//                    }
//                }
//
//                contentResolver.notifyChange(DatabaseContract.FEST_CONTENT_URI, null);
//            }
//        }
//
//
//        private ArrayList<ContentValues> parseCampusJSON(String response) {
//
//            ArrayList<ContentValues> list = new ArrayList<>();
//
//            try {
//                JSONArray jsonArray = new JSONArray(response);
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    ContentValues contentValues = new ContentValues();
//
//                    contentValues.put(DatabaseContract.FestTable.FEST_SERVER_ID, jsonObject.getInt("id"));
//                    contentValues.put(DatabaseContract.FestTable.FEST_NAME, jsonObject.getString("name"));
//
//                    contentValues.put(DatabaseContract.FestTable.FEST_DATE, jsonObject.getString("date"));
//                    contentValues.put(DatabaseContract.FestTable.FEST_DESCRIPTION, jsonObject.getInt("description"));
//                    contentValues.put(DatabaseContract.FestTable.FEST_IMAGE, jsonObject.getString("image_link"));
//
////                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_TITLE, jsonObject.getString("Title"));
////                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_DETAIL, jsonObject.getString("Description"));
////                    contentValues.put(DatabaseContract.CampusTable.CAMPUS_IMAGE, jsonObject.getString("image_link"));
//
//
//                    list.add(contentValues);
//                }
//            } catch (JSONException e) {
//                //tv.setText("JSON E:" + e);
//            }
//
//            return list;
//        }
//    }

}
