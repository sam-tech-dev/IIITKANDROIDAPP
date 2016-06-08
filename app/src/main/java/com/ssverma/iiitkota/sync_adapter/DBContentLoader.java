package com.ssverma.iiitkota.sync_adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.ssverma.iiitkota.Faculty;

/**
 * Created by IIITK on 6/6/2016.
 */
public class DBContentLoader implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int FACULTY_LOADER_ID = 1;
    private Context context;
    private String selectionClause = null;
    private String[] selectionArgs = null;

    public DBContentLoader(Context context){
        this.context = context;
    }

    public DBContentLoader(Context context , String selectionClause , String[] selectionArgs){
        this.context = context;
        this.selectionClause = selectionClause;
        this.selectionArgs = selectionArgs;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case FACULTY_LOADER_ID:
                return new CursorLoader(context , DatabaseContract.FACULTY_CONTENT_URI , null , selectionClause , selectionArgs , null);  //selection arguments DatabaseContract.FacultyTable.FACULTY_NAME + " = ?" , new String[]{"Fac 1"}
            case 2:
                return new CursorLoader(context , DatabaseContract.FACULTY_CONTENT_URI , null , selectionClause , selectionArgs , null); // for testing
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        switch (loader.getId()){
            case FACULTY_LOADER_ID:
                //Faculty.PlaceholderFragment.adapter.changeCursor(data);
                break;
            case 2:
                //Faculty.PlaceholderFragment.adapter.changeCursor(data);
                break;
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()){
            case FACULTY_LOADER_ID:
                //Faculty.PlaceholderFragment.adapter.changeCursor(null);
                break;
            case 2:
                //Faculty.PlaceholderFragment.adapter.changeCursor(null);
                break;
        }
    }

}
