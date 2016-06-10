package com.ssverma.iiitkota.sync_adapter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by IIITK on 6/6/2016.
 */
public class IIITK_ContentProvider extends ContentProvider{


    private static final UriMatcher uriMatcher;

    private static final int FACULTY_TABLE = 0;  // All rows
    private static final int FACULTY_TABLE_ROW = 1;  //Single row;


    //4
    //5
    private static final int EVENTS_TABLE = 4;  // All rows
    private static final int EVENTS_TABLE_ROW = 5;  //Single row;

    private static final int NEWS_TABLE = 6;  // All rows
    private static final int NEWS_TABLE_ROW = 7;  //Single row;


    //8
    //9
    private static final int CONTACT_TABLE = 8;  // All rows
    private static final int CONTACT_TABLE_ROW = 9;  //Single row;

    //10
    //11
    private static final int SCHOLARSHIP_TABLE = 10;  // All rows
    private static final int SCHOLARSHIP_TABLE_ROW = 11;  //Single row;


    private static final int CAMPUS_TABLE = 12;  // All rows
    private static final int CAMPUS_TABLE_ROW = 13;  //Single row;

    //14
    //15
    private static final int ADMINISTRATION_TABLE = 14;  // All rows
    private static final int ADMINISTRATION_TABLE_ROW = 15;  //Single row;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.FacultyTable.TABLE_NAME , FACULTY_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.FacultyTable.TABLE_NAME + "/#" , FACULTY_TABLE_ROW);
        // add more

        //
        //
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.ContactTable.TABLE_NAME , CONTACT_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.ContactTable.TABLE_NAME + "/#" , CONTACT_TABLE_ROW);

        //
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.AdministrationTable.TABLE_NAME , ADMINISTRATION_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.AdministrationTable.TABLE_NAME + "/#" , ADMINISTRATION_TABLE_ROW);
        //
        //
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.EventsTable.TABLE_NAME , EVENTS_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.EventsTable.TABLE_NAME + "/#" , EVENTS_TABLE_ROW);

        //
        //
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.ScholarshipTable.TABLE_NAME , SCHOLARSHIP_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.ScholarshipTable.TABLE_NAME + "/#" , SCHOLARSHIP_TABLE_ROW);

        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.NewsTable.TABLE_NAME , NEWS_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.NewsTable.TABLE_NAME + "/#" ,NEWS_TABLE_ROW);
        //Campus life
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.CampusTable.TABLE_NAME , CAMPUS_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.CampusTable.TABLE_NAME + "/#" ,CAMPUS_TABLE_ROW);
    }


    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = databaseHelper.getReadableDatabase();

        Cursor cursor;

        switch (uriMatcher.match(uri)){
            case FACULTY_TABLE :
                cursor = db.query(DatabaseContract.FacultyTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case FACULTY_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.FacultyTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
            //case
            //case
            case CONTACT_TABLE :
                cursor = db.query(DatabaseContract.ContactTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case CONTACT_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.ContactTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);

            //case
            //case
            case ADMINISTRATION_TABLE :
                cursor = db.query(DatabaseContract.AdministrationTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case ADMINISTRATION_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.AdministrationTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);

            //case
            //case
            case EVENTS_TABLE :
                cursor = db.query(DatabaseContract.EventsTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case EVENTS_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.EventsTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);

            //case
            //case
            case SCHOLARSHIP_TABLE :
                cursor = db.query(DatabaseContract.ScholarshipTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case SCHOLARSHIP_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.ScholarshipTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);


            case NEWS_TABLE :
                cursor = db.query(DatabaseContract.NewsTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case NEWS_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.NewsTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);


            // campus case

            case CAMPUS_TABLE :
                cursor = db.query(DatabaseContract.CampusTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver() , uri);
                return cursor;

            case CAMPUS_TABLE_ROW :
                selection = "_ID LIKE " + uri.getLastPathSegment();
                return db.query(DatabaseContract.CampusTable.TABLE_NAME , projection , selection , selectionArgs , null , null , sortOrder);


        }

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)){
            case FACULTY_TABLE:
                return DatabaseContract.FacultyTable.FACULTY_CONTENT_TYPE;
            case FACULTY_TABLE_ROW:
                return DatabaseContract.FacultyTable.FACULTY_CONTENT_TYPE_ID;

            //case
            //case

            case CONTACT_TABLE:
                return DatabaseContract.ContactTable.CONTACT_CONTENT_TYPE;
            case CONTACT_TABLE_ROW:
                return DatabaseContract.ContactTable.CONTACT_CONTENT_TYPE_ID;

            //case
            //case

            case ADMINISTRATION_TABLE:
                return DatabaseContract.AdministrationTable.ADMINISTRATION_CONTENT_TYPE;
            case ADMINISTRATION_TABLE_ROW:
                return DatabaseContract.AdministrationTable.ADMINISTRATION_CONTENT_TYPE_ID;
            //case
            //aces

            case EVENTS_TABLE:
                return DatabaseContract.EventsTable.EVENTS_CONTENT_TYPE;
            case EVENTS_TABLE_ROW:
                return DatabaseContract.EventsTable.EVENTS_CONTENT_TYPE_ID;


            //case
            //aces

            case SCHOLARSHIP_TABLE:
                return DatabaseContract.ScholarshipTable.SCHOLARSHIP_CONTENT_TYPE;
            case SCHOLARSHIP_TABLE_ROW:
                return DatabaseContract.ScholarshipTable.SCHOLARSHIP_CONTENT_TYPE_ID;


            case NEWS_TABLE:
                return DatabaseContract.NewsTable.NEWS_CONTENT_TYPE;
            case NEWS_TABLE_ROW:
                return DatabaseContract.NewsTable.NEWS_CONTENT_TYPE_ID;


            // campus life
            case CAMPUS_TABLE:
                return DatabaseContract.CampusTable.CAMPUS_CONTENT_TYPE;
            case CAMPUS_TABLE_ROW:
                return DatabaseContract.CampusTable.CAMPUS_CONTENT_TYPE_ID;

        }

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = databaseHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case FACULTY_TABLE:
                long row_id = db.insert(DatabaseContract.FacultyTable.TABLE_NAME , null , values);
                Uri _uri = ContentUris.withAppendedId(uri , row_id);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri;

            //case

            case CONTACT_TABLE:
                long row_id_contact = db.insert(DatabaseContract.ContactTable.TABLE_NAME , null , values);
                Uri _uri_contact = ContentUris.withAppendedId(uri , row_id_contact);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri_contact;

            //case

            case ADMINISTRATION_TABLE:
                long row_id_administration = db.insert(DatabaseContract.AdministrationTable.TABLE_NAME , null , values);
                Uri _uri_administration = ContentUris.withAppendedId(uri , row_id_administration);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri_administration;
            //case

            case EVENTS_TABLE:
                long row_id_events = db.insert(DatabaseContract.EventsTable.TABLE_NAME , null , values);
                Uri _uri_events = ContentUris.withAppendedId(uri , row_id_events);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri_events;

            //case

            case SCHOLARSHIP_TABLE:
                long row_id_scholarship = db.insert(DatabaseContract.ScholarshipTable.TABLE_NAME , null , values);
                Uri _uri_scholarship = ContentUris.withAppendedId(uri , row_id_scholarship);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri_scholarship;


            case NEWS_TABLE:
                long row_id_news = db.insert(DatabaseContract.NewsTable.TABLE_NAME , null , values);
                Uri _uri_news = ContentUris.withAppendedId(uri , row_id_news);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri_news;


            // campus life

            case CAMPUS_TABLE:
                long row_id_campus = db.insert(DatabaseContract.CampusTable.TABLE_NAME , null , values);
                Uri _uri_campus = ContentUris.withAppendedId(uri , row_id_campus);
                //getContext().getContentResolver().notifyChange(_uri , null);
                return _uri_campus;

        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        db = databaseHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)){
            case FACULTY_TABLE:
                int updatedRows = db.update(DatabaseContract.FacultyTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRows;


            //case
            case CONTACT_TABLE:
                int updatedRowsContact = db.update(DatabaseContract.ContactTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRowsContact;

            //case
            case ADMINISTRATION_TABLE:
                int updatedRowsAdministration = db.update(DatabaseContract.AdministrationTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRowsAdministration;

            //case
            case EVENTS_TABLE:
                int updatedRowsEvents = db.update(DatabaseContract.EventsTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRowsEvents;

            //case
            case SCHOLARSHIP_TABLE:
                int updatedRowsScholarship= db.update(DatabaseContract.ScholarshipTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRowsScholarship;

            case NEWS_TABLE:
                int updatedRowsNews = db.update(DatabaseContract.NewsTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRowsNews;

            // campus life

            case CAMPUS_TABLE:
                int updatedRowsCampus = db.update(DatabaseContract.CampusTable.TABLE_NAME , values , selection , selectionArgs);
                return updatedRowsCampus;

        }

        return 0;
    }
}
