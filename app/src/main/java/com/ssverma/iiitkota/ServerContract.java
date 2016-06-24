package com.ssverma.iiitkota;

public class ServerContract {

    private static String SERVER_URL = "http://172.16.1.231/iiitk/android";
    public static String getSERVER_URL() {
        return SERVER_URL;
    }

    public static String getFacultyImagesPath() {
        return FACULTY_IMAGES_PATH;
    }

    private static String FACULTY_IMAGES_PATH = "http://172.16.1.231/iiitk/assets/images/faculty/";

    public static String getGalleryImagesPath() {
        return GALLERY_IMAGES_PATH;
    }

    private static String GALLERY_IMAGES_PATH = "http://172.16.1.231/iiitk/assets/images/gallery/";

    public static String getGalleryPhpUrl() {
        return GALLERY_PHP_URL;
    }

    private static String GALLERY_PHP_URL = SERVER_URL + "/gallery.php";

    public static String getFacultyPhpUrl() {
        return FACULTY_PHP_URL;
    }

    private static String FACULTY_PHP_URL = SERVER_URL + "/faculty.php";



    public static String getProgramsPhpUrl() {
        return PROGRAMS_PHP_URL;
    }

    private static String PROGRAMS_PHP_URL = SERVER_URL + "/programs.php";


    public static String getGetRepList() {
        return getRepList;
    }

    private static  String getRepList=SERVER_URL + "/rep.php";

    public static String getPlacementDataPhpUrl() {
        return PlacementDataPhpUrl;
    }

    private static String PlacementDataPhpUrl=SERVER_URL+"/placement_report.php";

    public static String getVisitingCompanyPhpUrl() {
        return VISITING_COMPANY_PHP_URL;
    }

    private static String VISITING_COMPANY_PHP_URL = SERVER_URL + "/visitingcompany.php";


    private static String GALLERY_ALBUM_THUMBNAIL_PATH = "http://172.16.1.231/iiitk/assets/images/gallery/";

    public static String getGalleryAlbumThumbnailPath() {
        return GALLERY_ALBUM_THUMBNAIL_PATH;
    }

    public static String getGalleryAlbumPhpUrl() {
        return GALLERY_ALBUM_PHP_URL;
    }

    private static String GALLERY_ALBUM_PHP_URL = SERVER_URL + "/gallery_album.php";

    public static String getContactsPhpUrl() { return CONTACTS_PHP_URL; }

    private static String CONTACTS_PHP_URL = SERVER_URL + "/contact.php";

    public static String getAdministrationPhpUrl() { return ADMINISTRATION_PHP_URL; }

    private static String ADMINISTRATION_PHP_URL = SERVER_URL + "/administration.php";


    public static String getFestPhpUrl() { return FEST_PHP_URL; }

    private static String FEST_PHP_URL = SERVER_URL + "/fest.php";

    private static String FEST_PATH = SERVER_URL + "/assets/images/fest/";

    public static String getFestImagesUrl() {
        return FEST_PATH;
    }

    public static String getNewsPhpUrl() {
            return NEWS_PHP_URL;
        }

    private static String NEWS_PHP_URL = SERVER_URL + "/news.php";

    public static String getNewsImagePath() {
        return NEWS_IMAGE_PATH;
    }

    private static String NEWS_IMAGE_PATH = "http://172.16.1.231/iiitk/assets/images/news/";

    public static String getEventsPhpUrl() {
        return EVENTS_PHP_URL;
    }

    private static String EVENTS_PHP_URL= SERVER_URL + "/events.php";

    public static String getEventsImagePath() {
        return EVENTS_IMAGE_PATH;

    }

    public static String getCampusPhpUrl(){
        return CAMPUS_PHP_URL;
    }

    private static String CAMPUS_PHP_URL = SERVER_URL + "/campus_life.php";

    private static String CAMPUS_IMAGES_PATH =SERVER_URL + "/assets/images/campus/";

    public static String getCampusImagesUrl() {
        return CAMPUS_IMAGES_PATH;
    }

    private static String EVENTS_IMAGE_PATH = "http://172.16.1.231/iiitk/assets/images/events/";

    public static String getScholarshipPhpUrl() {
        return SCHOLARSHIP_PHP_URL;
    }

    private static String SCHOLARSHIP_PHP_URL= SERVER_URL + "/scholarship.php";

    public static String getScholarshipImagePath() {
        return SCHOLARSHIP_IMAGE_PATH;
    }

    private static String SCHOLARSHIP_IMAGE_PATH = SERVER_URL + "/assets/images/scholarship/";

    public static String getProgramImagePath() {
        return PROGRAM_IMAGE_PATH;
    }


    public static String getAcademicCalendarPhpUrl() {
        return ACADEMIC_CALENDAR_PHP_URL;
    }

    public static String ACADEMIC_CALENDAR_PHP_URL = SERVER_URL + "/academic_calendar.php";

    public static String getAcademicTimetablePhpUrl() {
        return ACADEMIC_TIMETABLE_PHP_URL;
    }

    public static String ACADEMIC_TIMETABLE_PHP_URL = SERVER_URL + "/academic_timetable.php";

    public static String getAcademicResourcesPhpUrl() {
        return ACADEMIC_RESOURCES_PHP_URL;
    }

    private static String ACADEMIC_RESOURCES_PHP_URL= SERVER_URL + "/academic_resources.php";

    public static String getAcademicResearchPhpUrl() {
        return ACADEMIC_RESEARCH_PHP_URL;
    }

    public static String ACADEMIC_RESEARCH_PHP_URL = SERVER_URL + "/academic_research.php";

    private static String ACADEMIC_RESEARCH_PROJECT_IMAGE_PATH = SERVER_URL + "/assets/images/research/project/";

    public static String getAcademicResearchPersonImagePath() {
        return ACADEMIC_RESEARCH_PERSON_IMAGE_PATH;
    }

    public static String getAcademicResearchProjectImagePath() {
        return ACADEMIC_RESEARCH_PROJECT_IMAGE_PATH;
    }

    private static String ACADEMIC_RESEARCH_PERSON_IMAGE_PATH = SERVER_URL + "/assets/images/research/person/";

    public static String getAcademicCoursesPhpUrl() {
        return ACADEMIC_COURSES_PHP_URL;
    }

    private static String ACADEMIC_COURSES_PHP_URL = SERVER_URL + "/academic_courses.php";

    public static String PROGRAM_IMAGE_PATH = "http://172.16.1.231/iiitk/assets/images/faculty/";


    public static String getAcademicCalendar_URL() {
        return ACADEMIC_CALENDAR_URL;
    }

    public static String ACADEMIC_CALENDAR_URL = SERVER_URL + "/assets/documents/academic_calendar/";

    public static String getPlacementReport_URL() {
        return PLACEMENT_REPORT_URL;
    }

    public static String PLACEMENT_REPORT_URL = SERVER_URL + "/assets/documents/academic_calendar/";

    public static String getFaqUrl() {
        return FAQ_URL;
    }

    public static String FAQ_URL = SERVER_URL + "/faq.php";

    public static String getAdmissionStatisticsUrl() {
        return ADMISSION_STATISTICS_URL;
    }

    public static String ADMISSION_STATISTICS_URL = SERVER_URL + "/admission_statistics.php";

    public static String getAdmissionQueriesUrl() {
        return ADMISSION_QUERIES_URL;
    }

    public static String ADMISSION_QUERIES_URL = SERVER_URL + "/admission_queries.php";

    public static String getAcademicTimetableDocs() {
        return ACADEMIC_TIMETABLE_DOCS;
    }

    public static String ACADEMIC_TIMETABLE_DOCS = SERVER_URL + "/assets/documents/academic_timetable/";

}
