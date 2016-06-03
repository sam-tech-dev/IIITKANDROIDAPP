package com.ssverma.iiitkota;

/**
 * Created by IIITK on 5/30/2016.
 */
public class ServerContract {

    private static String SERVER_URL = "http://172.16.1.231/iiitk/android";

    public static String getFACULTY_CS_PHP_URL() {
        return FACULTY_CS_PHP_URL;
    }

    private static String FACULTY_CS_PHP_URL = SERVER_URL + "/cs_faculty.php";

    public static String getFacultyEePhpUrl() {
        return FACULTY_EE_PHP_URL;
    }

    private static String FACULTY_EE_PHP_URL = SERVER_URL + "/ece_faculty.php";
    public static String getFacultyEcePhpUrl() {
        return FACULTY_ECE_PHP_URL;
    }

    private static String FACULTY_ECE_PHP_URL = SERVER_URL + "/ece_faculty.php";

    public static String getSERVER_URL() {
        return SERVER_URL;
    }

    public static String getFacultyImagesPath() {
        return FACULTY_IMAGES_PATH;
    }

    private static String FACULTY_IMAGES_PATH = "http://172.16.1.231/iiitk/assets/images/faculty/";
    //String urlSuffix = "?s_name="+s_name+"&s_id="+s_id;
    //String finalURL = url + urlSuffix;


    public static String getNEWS_PREV_PHP_URL() {
        return NEWS_PREV_PHP_URL;
    }

    private static String NEWS_PREV_PHP_URL = SERVER_URL + "/old_news.php";

    public static String getNewsLatestPhpUrl() {
        return NEWS_LATEST_PHP_URL;
    }

    private static String NEWS_LATEST_PHP_URL = SERVER_URL + "/today_news.php";

    public static String getNewsUpcomingPhpUrl() {
        return NEWS_UPCOMING_PHP_URL;
    }

    private static String NEWS_UPCOMING_PHP_URL = SERVER_URL + "/upcoming_news.php";

    public static String getNewsImagePath() {
        return NEWS_IMAGE_PATH;
    }

    private static String NEWS_IMAGE_PATH = "http://172.16.1.231/iiitk/assets/images/news/";



    //  public static String getSERVER_URL() {
    //  return SERVER_URL;
    // }


    //Added by-Dixit Chauhan      :03/06/2016
    public static String getEventsPhpUrl() {
        return EVENTS_PHP_URL;
    }

    //String urlSuffix = "?s_name="+s_name+"&s_id="+s_id;
    //String finalURL = url + urlSuffix;
    private static String EVENTS_PHP_URL= SERVER_URL + "/events.php";

    public static String getEventsImagePath() {
        return EVENTS_IMAGE_PATH;
    }

    private static String EVENTS_IMAGE_PATH = "http://172.16.1.231/android/assets/images/events/";

}
