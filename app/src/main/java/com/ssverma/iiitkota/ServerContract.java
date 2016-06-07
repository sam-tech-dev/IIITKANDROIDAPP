package com.ssverma.iiitkota;

/**
 * Created by IIITK on 5/30/2016.
 */
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

    private static String GALLERY_ALBUM_THUMBNAIL_PATH = "http://172.16.1.231/iiitk/assets/images/gallery/";

    public static String getGalleryAlbumThumbnailPath() {
        return GALLERY_ALBUM_THUMBNAIL_PATH;
    }

    public static String getGalleryAlbumPhpUrl() {
        return GALLERY_ALBUM_PHP_URL;
    }

    private static String GALLERY_ALBUM_PHP_URL = SERVER_URL + "/gallery_album.php";

    public static String getContactsPhpUrl() { return CONTACTS_PHP_URL; }

    private static String CONTACTS_PHP_URL = SERVER_URL + "/contacts.php";

    //String urlSuffix = "?s_name="+s_name+"&s_id="+s_id;
    //String finalURL = url + urlSuffix;


    private static String NEWS_PHP_URL = SERVER_URL + "/news.php";

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

    private static String EVENTS_IMAGE_PATH = "http://172.16.1.231/iiitk/assets/images/events/";

    public static String getNewsPhpUrl() {
        return NEWS_PHP_URL;
    }

    public static String getProgramImagePath() {
        return PROGRAM_IMAGE_PATH;
    }

    public static String PROGRAM_IMAGE_PATH = "http://172.16.1.231/iiitk/assets/images/events/";
}
