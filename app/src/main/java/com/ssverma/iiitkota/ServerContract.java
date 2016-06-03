package com.ssverma.iiitkota;

/**
 * Created by IIITK on 5/30/2016.
 */
public class ServerContract {

    private static String SERVER_URL = "http://172.16.1.231/iiitk/android";

    public static String getFACULTY_CS_PHP_URL() {
        return FACULTY_CS_PHP_URL;
    }

    private static String FACULTY_CS_PHP_URL = SERVER_URL + "/sample_get_cs_faculty.php";

    public static String getFacultyEePhpUrl() {
        return FACULTY_EE_PHP_URL;
    }

    private static String FACULTY_EE_PHP_URL = SERVER_URL + "/sample_get_ee_faculty.php";

    public static String getFacultyEcePhpUrl() {
        return FACULTY_ECE_PHP_URL;
    }

    private static String FACULTY_ECE_PHP_URL = SERVER_URL + "/sample_get_ece_faculty.php";

    public static String getSERVER_URL() {
        return SERVER_URL;
    }

    //String urlSuffix = "?s_name="+s_name+"&s_id="+s_id;
    //String finalURL = url + urlSuffix;






        public static String getNewsPhpUrl() {
            return NEWS_PHP_URL;
        }

        private static String NEWS_PHP_URL = SERVER_URL + "/news.php";



      //  public static String getSERVER_URL() {
          //  return SERVER_URL;
       // }

        //String urlSuffix = "?s_name="+s_name+"&s_id="+s_id;
        //String finalURL = url + urlSuffix;


        private static String NEWS_PATH="http://172.16.1.231/iiitk/assets/images/news/";

        public static String getNewsImagesUrl() {
            return NEWS_PATH;
        }

        // public static void setNewsImagesUrl(String serverUrl) {
        // = serverUrl;
        //}
    }




