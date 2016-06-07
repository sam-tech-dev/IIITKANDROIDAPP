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
}
