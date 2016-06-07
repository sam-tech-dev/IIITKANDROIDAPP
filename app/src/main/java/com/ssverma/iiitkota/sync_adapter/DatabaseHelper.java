package com.ssverma.iiitkota.sync_adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IIITK on 6/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA = ", ";

    private static final String CREATE_FACULTY_TABLE = "CREATE TABLE "
            + DatabaseContract.FacultyTable.TABLE_NAME + " ("
            + DatabaseContract.FacultyTable.FACULTY_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_ID + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_EMAIL + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_DOB + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_DEPARTMENT + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_CONTACT + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_IMAGE + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_QUALIFICATION + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_HOMETOWN + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_DESIGNATION + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_ACHIEVEMENTS + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_SUMMARY + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_RESEARCH_AREAS + TEXT_TYPE + COMMA
            + DatabaseContract.FacultyTable.FACULTY_FACEBOOK + TEXT_TYPE + " )";

    private static final String DROP_FACULTY_TABLE = "DROP TABLE IF EXISTS "
            +DatabaseContract.FacultyTable.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FACULTY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_FACULTY_TABLE);
        onCreate(db);
    }
}
