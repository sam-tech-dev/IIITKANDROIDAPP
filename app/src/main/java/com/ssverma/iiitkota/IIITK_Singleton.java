package com.ssverma.iiitkota;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by IIITK on 6/15/2016.
 */
public class IIITK_Singleton extends Application{
    private static IIITK_Singleton ourInstance;
    private static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        if (ourInstance == null){
            ourInstance = new IIITK_Singleton();
        }
        if (sp == null){
            sp = getSharedPreferences("sync_status" , MODE_PRIVATE);
        }
    }


    public static void setPreferencesValue(boolean status){
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isSyncComplete", status);
        editor.commit();
    }

    public static SharedPreferences getPreference(){
        return sp;
    }

    public static IIITK_Singleton getInstance() {
        return ourInstance;
    }

}
