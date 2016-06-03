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

}
