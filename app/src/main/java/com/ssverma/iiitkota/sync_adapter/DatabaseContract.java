package com.ssverma.iiitkota.sync_adapter;

import android.net.Uri;

/**
 * Created by IIITK on 6/6/2016.
 */
public class DatabaseContract {
    public static final String DB_NAME = "demo.db";
    public static final String AUTHORITY = "com.ssverma.iiitkota.sync_adapter.IIITK_ContentProvider";
    private static final String CONTENT_TYPE = "vnd.android.cursor.dir/";
    private static final String CONTENT_TYPE_ID = "vnd.android.cursor.item/";

    public static final Uri FACULTY_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.FacultyTable.TABLE_NAME);


    //Event
    public static final Uri EVENTS_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.EventsTable.TABLE_NAME);

    //Scholarship
    public static final Uri SCHOLARSHIP_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.ScholarshipTable.TABLE_NAME);
    //CONTACT
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.ContactTable.TABLE_NAME);
    //ADMINISTRATION
    public static final Uri ADMINISTRATION_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.AdministrationTable.TABLE_NAME);
    public abstract class FacultyTable {

        public static final String FACULTY_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.faculty";
        public static final String FACULTY_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.faculty";

        public static final String TABLE_NAME = "faculty_table";

        public static final String FACULTY_SERVER_ID = "id";
        public static final String FACULTY_NAME = "Name";
        public static final String FACULTY_ID = "FacultyId";
        public static final String FACULTY_EMAIL = "Email";
        public static final String FACULTY_DOB = "DateOfBirth";
        public static final String FACULTY_DEPARTMENT = "Department";
        public static final String FACULTY_CONTACT = "Contact";
        public static final String FACULTY_IMAGE = "Image";
        public static final String FACULTY_QUALIFICATION = "Qualification";
        public static final String FACULTY_HOMETOWN = "Hometown";
        public static final String FACULTY_DESIGNATION = "Designation";
        public static final String FACULTY_ACHIEVEMENTS = "Achievements";
        public static final String FACULTY_SUMMARY = "Summary";
        public static final String FACULTY_RESEARCH_AREAS = "ResearchAreas";
        public static final String FACULTY_FACEBOOK = "Facebook";

    }

    //CONTACT
    public abstract class ContactTable {

        public static final String CONTACT_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.contact";
        public static final String CONTACT_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.contact";

        public static final String TABLE_NAME = "contact_table";

        public static final String CONTACT_SERVER_ID = "contact_id";
        public static final String CONTACT_NAME = "contact_name";
        public static final String CONTACT_EMAIL = "contact_email";
        public static final String CONTACT_MOBILE = "contact_mobile_no";
        public static final String CONTACT_CATEGORY = "contact_category";
        public static final String CONTACT_DESIGNATION = "contact_designation";

//        public static final String EVENTS_TITLE = "Title";
//        public static final String EVENTS_DATE = "Date";
//        public static final String EVENTS_SUBTITLE = "Subtitle";
//        public static final String EVENTS_DETAIL = "Detail";
//        public static final String EVENTS_AUTHOR = "Author";
//        public static final String EVENTS_IMAGE = "Image";
    }


    //ADMINISTRATION
    public abstract class AdministrationTable {

        public static final String ADMINISTRATION_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.administration";
        public static final String ADMINISTRATION_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.administration";

        public static final String TABLE_NAME = "administration_table";

        public static final String ADMINISTRATION_SERVER_ID = "id";
        public static final String ADMINISTRATION_NAME = "Name";
        public static final String ADMINISTRATION_DESIGNATION = "Designation";
        public static final String ADMINISTRATION_CATEGORY = "Category";

//        public static final String EVENTS_TITLE = "Title";
//        public static final String EVENTS_DATE = "Date";
//        public static final String EVENTS_SUBTITLE = "Subtitle";
//        public static final String EVENTS_DETAIL = "Detail";
//        public static final String EVENTS_AUTHOR = "Author";
//        public static final String EVENTS_IMAGE = "Image";
    }

    //
    public abstract class EventsTable {

        public static final String EVENTS_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.events";
        public static final String EVENTS_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.events";

        public static final String TABLE_NAME = "events_table";

        public static final String EVENTS_SERVER_ID = "id";
        public static final String EVENTS_TITLE = "Title";
        public static final String EVENTS_DATE = "Date";
        public static final String EVENTS_SUBTITLE = "Subtitle";
        public static final String EVENTS_DETAIL = "Detail";
        public static final String EVENTS_AUTHOR = "Author";
        public static final String EVENTS_IMAGE = "Image";

        public static final String EVENTS_FLAG = "flag";
    }


    //
    public abstract class ScholarshipTable {

        public static final String SCHOLARSHIP_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.scholarship";
        public static final String SCHOLARSHIP_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.scholarship";

        public static final String TABLE_NAME = "scholarship_table";

        public static final String SCHOLARSHIP_SERVER_ID = "id";
        public static final String SCHOLARSHIP_NAME = "name";
        public static final String SCHOLARSHIP_INCOME = "criteria_total_parental_income_rs";
        public static final String SCHOLARSHIP_ACADEMIC= "criteria_academic";
        public static final String SCHOLARSHIP_CATEGORY = "criteria_category";
        public static final String SCHOLARSHIP_VALUE= "value";
        public static final String SCHOLARSHIP_PROCEDURE = "procedure_for_application";
        public static final String SCHOLARSHIP_REMARK = "remark";
        public static final String SCHOLARSHIP_LINK = "link";
        public static final String SCHOLARSHIP_IMAGE= "image";

    }

}
