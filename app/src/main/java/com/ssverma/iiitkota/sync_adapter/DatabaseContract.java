package com.ssverma.iiitkota.sync_adapter;

import android.net.Uri;

/**
 * Created by IIITK on 6/6/2016.
 */
public class DatabaseContract {
    public static final String DB_NAME = "iiitk.db";
    public static final String AUTHORITY = "com.ssverma.iiitkota.sync_adapter.IIITK_ContentProvider";
    private static final String CONTENT_TYPE = "vnd.android.cursor.dir/";
    private static final String CONTENT_TYPE_ID = "vnd.android.cursor.item/";

    public static final Uri FACULTY_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.FacultyTable.TABLE_NAME);
    //Programs URI
    public static final Uri PROGRAM_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.ProgramTable.TABLE_NAME);
    //VC URI
    public static final Uri VC_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.Placement_Visting_Company_Table.TABLE_NAME);
    public static final Uri RP_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.Placement_RP_Table.TABLE_NAME);
    public static final Uri PLT_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.Placement_module_Table.TABLE_NAME);
    public static final Uri INT_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.Internship_module_Table.TABLE_NAME);

    public static final Uri NEWS_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.NewsTable.TABLE_NAME);
    public static final Uri CAMPUS_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.CampusTable.TABLE_NAME);
    public static final Uri EVENTS_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.EventsTable.TABLE_NAME);

    //Scholarship
    public static final Uri SCHOLARSHIP_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.ScholarshipTable.TABLE_NAME);
    //CONTACT
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.ContactTable.TABLE_NAME);
    //ADMINISTRATION
    public static final Uri ADMINISTRATION_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.AdministrationTable.TABLE_NAME);
//FEST
//public static final Uri FEST_CONTENT_URI = Uri.parse("content://" + DatabaseContract.AUTHORITY + "/" + DatabaseContract.FestTable.TABLE_NAME);


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

    public abstract class NewsTable {

        public static final String NEWS_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.news";
        public static final String NEWS_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.news";

        public static final String TABLE_NAME = "newsfeed_table";

        public static final String NEWS_SERVER_ID = "id";
        public static final String NEWS_TITLE = "Title";
        public static final String NEWS_AUTHOR = "Author";
        public static final String NEWS_DATE = "Date";
        public static final String NEWS_SUBTITLE = "Subtitle";
        public static final String NEWS_DETAIL = "Detail";

        public static final String NEWS_IMAGE = "Image";

        public static final String NEWS_FLAG = "flag";
    }


    public abstract class CampusTable {

        public static final String CAMPUS_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.campus";
        public static final String CAMPUS_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.campus";

        public static final String TABLE_NAME = "campus_table";

        public static final String CAMPUS_SERVER_ID = "id";
        public static final String CAMPUS_TITLE = "Title";
        public static final String CAMPUS_DETAIL = "Description";
        public static final String CAMPUS_IMAGE = "image_link";
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

    }


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


    public abstract class ScholarshipTable {

        public static final String SCHOLARSHIP_CONTENT_TYPE =
                CONTENT_TYPE + "vnd.Demo_ContentProvider.scholarship";
        public static final String SCHOLARSHIP_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.scholarship";

        public static final String TABLE_NAME = "scholarship_table";

        public static final String SCHOLARSHIP_SERVER_ID = "id";
        public static final String SCHOLARSHIP_NAME = "name";
        public static final String SCHOLARSHIP_INCOME = "criteria_total_parental_income_rs";
        public static final String SCHOLARSHIP_ACADEMIC = "criteria_academic";
        public static final String SCHOLARSHIP_CATEGORY = "criteria_category";
        public static final String SCHOLARSHIP_VALUE = "value";
        public static final String SCHOLARSHIP_PROCEDURE = "procedure_for_application";
        public static final String SCHOLARSHIP_REMARK = "remark";
        public static final String SCHOLARSHIP_LINK = "link";
        public static final String SCHOLARSHIP_IMAGE = "image";

    }

    public abstract class ProgramTable {
        public static final String PROGRAM_CONTENT_TYPE = CONTENT_TYPE + "vnd.Demo_ContentProvider.program";
        ;
        public static final String PROGRAM_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.program";
        public static final String TABLE_NAME = "program";
        public static final String PROGRAM_SERVER_ID = "id";
        public static final String PROGRAM_NAME = "Program_name";
        public static final String PROGRAM_Type = "Program_type";
        public static final String PROGRAM_SEAT = "Program_seats";
        public static final String PROGRAM_DESC = "Program_desc";
        public static final String PROGRAM_IMAGE = "Program_image";
        public static final String PROGRAM_ELIGIBILITY = "Program_eli";
        public static final String PROGRAM_DURATION = "Program_duration";
        public static final String PROGRAM_FEE = "Program_fee";

    }

    public abstract class Placement_Visting_Company_Table {
        public static final String VC_CONTENT_TYPE = CONTENT_TYPE + "vnd.Demo_ContentProvider.placement_vc";
        ;
        public static final String VC_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.placement_vc";
        public static final String TABLE_NAME = "company_profile";
        public static final String VC_SERVER_ID = "id";
        public static final String VC_NAME = "Name";
        public static final String VC_SUMMARY = "Summary";
        public static final String VC_EMAIL = "Email";
        public static final String VC_ADDRESS = "Address";
        public static final String VC_CONTACT = "Contact";
        public static final String VC_INDUSTRY = "Industry";
        public static final String VC_DOMAIN = "Domain";
        public static final String VC_CTC = "Expected_CTC";
        public static final String VC_STRENGTH = "Strength";
        public static final String VC_TURNOVER = "TurnOver";
        public static final String VC_IMAGE = "Image";

    }

    public abstract class Placement_RP_Table {
        public static final String RP_CONTENT_TYPE = CONTENT_TYPE + "vnd.Demo_ContentProvider.placement_rp";
        ;
        public static final String RP_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.placement_rp";
        public static final String TABLE_NAME = "contacts_rp";
        public static final String RP_SERVER_ID = "contact_id";
        public static final String RP_NAME = "contact_name";
        public static final String RP_DES = "contact_designation";
        public static final String RP_EMAIL = "contact_email";
        public static final String RP_MOB = "contact_mobile_no";
        public static final String RP_CATEGORY = "contact_category";
        public static final String RP_IMAGE = "Contact_image";
    }

    public abstract class Placement_module_Table {
        public static final String PLT_CONTENT_TYPE = CONTENT_TYPE + "vnd.Demo_ContentProvider.placement";
        ;
        public static final String PLT_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.placement";
        public static final String TABLE_NAME = "placement_data";
        public static final String PLT_SERVER_ID = "id";
        public static final String PLT_SNAME = "student_name";
        public static final String PLT_COMPNAY = "company_name";
        public static final String PLT_BRANCH = "branch";
        public static final String PLT_PACKAGE = "package";
        public static final String PLT_SESSION = "session";


    }

    public abstract class Internship_module_Table {
        public static final String INT_CONTENT_TYPE = CONTENT_TYPE + "vnd.Demo_ContentProvider.internship";
        ;
        public static final String INT_CONTENT_TYPE_ID =
                CONTENT_TYPE_ID + "vnd.Demo_ContentProvider.internship";
        public static final String TABLE_NAME = "internship_Data";
        public static final String INT_SERVER_ID = "id";
        public static final String INT_SNAME = "student_name";
        public static final String INT_COMPNAY = "company_name";
        public static final String INT_BRANCH = "branch";
        public static final String INT_STIPEND = "stipend";
        public static final String INT_DETAILS = "details";
        public static final String INT_SESSION = "session";

    }


}
