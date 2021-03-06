package com.ssverma.iiitkota.sync_adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IIITK on 6/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
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
            + DatabaseContract.FacultyTable.TABLE_NAME;


    //CONTACT
    private static final String CREATE_CONTACT_TABLE = "CREATE TABLE "
            + DatabaseContract.ContactTable.TABLE_NAME + " ("
            + DatabaseContract.ContactTable.CONTACT_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.ContactTable.CONTACT_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.ContactTable.CONTACT_EMAIL + TEXT_TYPE + COMMA
            + DatabaseContract.ContactTable.CONTACT_MOBILE + TEXT_TYPE + COMMA
            + DatabaseContract.ContactTable.CONTACT_CATEGORY + TEXT_TYPE + COMMA
            + DatabaseContract.ContactTable.CONTACT_DESIGNATION + TEXT_TYPE + " )";


    //
    private static final String DROP_CONTACT_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.ContactTable.TABLE_NAME;


    //ADMINISTRATION
    private static final String CREATE_ADMINISTRATION_TABLE = "CREATE TABLE "
            + DatabaseContract.AdministrationTable.TABLE_NAME + " ("
            + DatabaseContract.AdministrationTable.ADMINISTRATION_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.AdministrationTable.ADMINISTRATION_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.AdministrationTable.ADMINISTRATION_DESIGNATION + TEXT_TYPE + COMMA
            + DatabaseContract.AdministrationTable.ADMINISTRATION_CATEGORY + TEXT_TYPE + " )";


    //
    private static final String DROP_ADMINISTRATION_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.AdministrationTable.TABLE_NAME;


    //Event
    private static final String CREATE_EVENTS_TABLE = "CREATE TABLE "
            + DatabaseContract.EventsTable.TABLE_NAME + " ("
            + DatabaseContract.EventsTable.EVENTS_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_DATE + TEXT_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_SUBTITLE + TEXT_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_DETAIL + TEXT_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_AUTHOR + TEXT_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_IMAGE + TEXT_TYPE + COMMA
            + DatabaseContract.EventsTable.EVENTS_FLAG + TEXT_TYPE + " )";


    //
    private static final String DROP_EVENTS_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.EventsTable.TABLE_NAME;


    //Scholarship
    private static final String CREATE_SCHOLARSHIP_TABLE = "CREATE TABLE "
            + DatabaseContract.ScholarshipTable.TABLE_NAME + " ("
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_INCOME + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_ACADEMIC + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_CATEGORY + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_VALUE + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_PROCEDURE + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_REMARK + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_LINK + TEXT_TYPE + COMMA
            + DatabaseContract.ScholarshipTable.SCHOLARSHIP_IMAGE + TEXT_TYPE + " )";


    //
    private static final String DROP_SCHOLARSHIP_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.ScholarshipTable.TABLE_NAME;


    //News
    private static final String CREATE_NEWS_TABLE = "CREATE TABLE "
            + DatabaseContract.NewsTable.TABLE_NAME + " ("
            + DatabaseContract.NewsTable.NEWS_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_DATE + TEXT_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_SUBTITLE + TEXT_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_DETAIL + TEXT_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_AUTHOR + TEXT_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_FLAG + TEXT_TYPE + COMMA
            + DatabaseContract.NewsTable.NEWS_IMAGE + TEXT_TYPE + " )";

    //
    private static final String DROP_NEWS_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.NewsTable.TABLE_NAME;


    //Campus_life


    private static final String CREATE_CAMPUS_TABLE = "CREATE TABLE "
            + DatabaseContract.CampusTable.TABLE_NAME + " ("
            + DatabaseContract.CampusTable.CAMPUS_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.CampusTable.CAMPUS_TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.CampusTable.CAMPUS_DETAIL + TEXT_TYPE + COMMA
            + DatabaseContract.CampusTable.CAMPUS_IMAGE + TEXT_TYPE +

            " )";

    //
    private static final String DROP_CAMPUS_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.CampusTable.TABLE_NAME;


    private static final String CREATE_PROGRAM_TABLE = "CREATE TABLE "
            + DatabaseContract.ProgramTable.TABLE_NAME + " ("
            + DatabaseContract.ProgramTable.PROGRAM_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_DESC + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_ELIGIBILITY + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_Type + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_DURATION + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_FEE + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_IMAGE + TEXT_TYPE + COMMA
            + DatabaseContract.ProgramTable.PROGRAM_SEAT + TEXT_TYPE
            + " )";

    private static final String DROP_PROGRAM_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.ProgramTable.TABLE_NAME;


    private static final String CREATE_VC_TABLE = "CREATE TABLE "
            + DatabaseContract.Placement_Visting_Company_Table.TABLE_NAME + " ("
            + DatabaseContract.Placement_Visting_Company_Table.VC_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_CTC + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_INDUSTRY + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_CONTACT + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_DOMAIN + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_EMAIL + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_IMAGE + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_ADDRESS + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_STRENGTH + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_SUMMARY + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_Visting_Company_Table.VC_TURNOVER + TEXT_TYPE
            + " )";

    private static final String DROP_VC_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.Placement_Visting_Company_Table.TABLE_NAME;


    private static final String CREATE_RP_TABLE = "CREATE TABLE "
            + DatabaseContract.Placement_RP_Table.TABLE_NAME + " ("
            + DatabaseContract.Placement_RP_Table.RP_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.Placement_RP_Table.RP_DES + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_RP_Table.RP_EMAIL + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_RP_Table.RP_MOB + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_RP_Table.RP_NAME + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_RP_Table.RP_CATEGORY + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_RP_Table.RP_IMAGE + TEXT_TYPE
            + " )";

    private static final String DROP_RP_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.Placement_RP_Table.TABLE_NAME;

    private static final String CREATE_PLT_TABLE = "CREATE TABLE "
            + DatabaseContract.Placement_module_Table.TABLE_NAME + " ("
            + DatabaseContract.Placement_module_Table.PLT_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.Placement_module_Table.PLT_BRANCH + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_module_Table.PLT_COMPNAY + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_module_Table.PLT_PACKAGE + INTEGER_TYPE + COMMA
            + DatabaseContract.Placement_module_Table.PLT_SESSION + TEXT_TYPE + COMMA
            + DatabaseContract.Placement_module_Table.PLT_SNAME + TEXT_TYPE
            + " )";

    private static final String DROP_PLT_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.Placement_module_Table.TABLE_NAME;


    private static final String CREATE_INTERN_TABLE = "CREATE TABLE "
            + DatabaseContract.Internship_module_Table.TABLE_NAME + " ("
            + DatabaseContract.Internship_module_Table.INT_SERVER_ID + INTEGER_TYPE + COMMA
            + DatabaseContract.Internship_module_Table.INT_BRANCH + TEXT_TYPE + COMMA
            + DatabaseContract.Internship_module_Table.INT_COMPNAY + TEXT_TYPE + COMMA
            + DatabaseContract.Internship_module_Table.INT_DETAILS + TEXT_TYPE + COMMA
            + DatabaseContract.Internship_module_Table.INT_SESSION + TEXT_TYPE + COMMA
            + DatabaseContract.Internship_module_Table.INT_SNAME + TEXT_TYPE + COMMA
            + DatabaseContract.Internship_module_Table.INT_STIPEND + TEXT_TYPE
            + " )";

    private static final String DROP_INT_TABLE = "DROP TABLE IF EXISTS "
            + DatabaseContract.Internship_module_Table.TABLE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_FACULTY_TABLE);
        db.execSQL(CREATE_CONTACT_TABLE);
        db.execSQL(CREATE_ADMINISTRATION_TABLE);
        db.execSQL(CREATE_EVENTS_TABLE);
        db.execSQL(CREATE_SCHOLARSHIP_TABLE);
        db.execSQL(CREATE_NEWS_TABLE);
        db.execSQL(CREATE_CAMPUS_TABLE);

        db.execSQL(CREATE_PROGRAM_TABLE);

        db.execSQL(CREATE_VC_TABLE);

        db.execSQL(CREATE_RP_TABLE);

        db.execSQL(CREATE_PLT_TABLE);
        db.execSQL(CREATE_INTERN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_FACULTY_TABLE);
        //onCreate(db);
        db.execSQL(DROP_EVENTS_TABLE);

        //
        db.execSQL(DROP_SCHOLARSHIP_TABLE);
        //
        db.execSQL(DROP_CONTACT_TABLE);
        db.execSQL(DROP_ADMINISTRATION_TABLE);

        db.execSQL(DROP_NEWS_TABLE);
        db.execSQL(DROP_CAMPUS_TABLE);
        //       db.execSQL(DROP_FEST_TABLE);


        //Program-Module
        db.execSQL(DROP_PROGRAM_TABLE);
        //VC Module
        db.execSQL(DROP_VC_TABLE);
        //RP Module
        db.execSQL(DROP_RP_TABLE);
        //Placement Module
        db.execSQL(DROP_PLT_TABLE);
        //Internship Module
        db.execSQL(DROP_INT_TABLE);
        onCreate(db);


    }
}
