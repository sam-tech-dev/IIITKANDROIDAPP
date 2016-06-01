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

<<<<<<< HEAD
    private static String FACULTY_EE_PHP_URL = SERVER_URL + "/ee_faculty.php";

=======
    private static String FACULTY_EE_PHP_URL = SERVER_URL + "/ece_faculty.php";
>>>>>>> e54de424c1d46c1a68be9026720bff98826af2ec
    public static String getFacultyEcePhpUrl() {
        return FACULTY_ECE_PHP_URL;
    }

    private static String FACULTY_ECE_PHP_URL = SERVER_URL + "/ece_faculty.php";

    public static String getSERVER_URL() {
        return SERVER_URL;
    }

<<<<<<< HEAD
    public static String getProgramUgGetList() {
        return PROGRAM_UG_GET_LIST;
    }

    public static void setProgramUgGetList(String programUgGetList) {
        PROGRAM_UG_GET_LIST = programUgGetList;
    }

    public static String getProgramPgGetList() {
        return PROGRAM_PG_GET_LIST;
    }

    public static void setProgramPgGetList(String programPgGetList) {
        PROGRAM_PG_GET_LIST = programPgGetList;
    }

    private static String PROGRAM_UG_GET_LIST=SERVER_URL+"/ug_programs.php";
    private static String PROGRAM_PG_GET_LIST=SERVER_URL+"/pg_programs.php";
=======
    public static String getFacultyImagesPath() {
        return FACULTY_IMAGES_PATH;
    }

    private static String FACULTY_IMAGES_PATH = "http://172.16.1.231/iiitk/assets/images/faculty/";
>>>>>>> e54de424c1d46c1a68be9026720bff98826af2ec
    //String urlSuffix = "?s_name="+s_name+"&s_id="+s_id;
    //String finalURL = url + urlSuffix;

}
