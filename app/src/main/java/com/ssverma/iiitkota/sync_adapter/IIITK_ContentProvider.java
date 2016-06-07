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

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.FacultyTable.TABLE_NAME , FACULTY_TABLE);
        uriMatcher.addURI(DatabaseContract.AUTHORITY , DatabaseContract.FacultyTable.TABLE_NAME + "/#" , FACULTY_TABLE_ROW);
        // add more
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
        }

        return 0;
    }
}
