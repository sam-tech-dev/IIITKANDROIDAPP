package com.ssverma.iiitkota;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.ssverma.iiitkota.sync_adapter.DatabaseContract;
import com.ssverma.iiitkota.sync_adapter.SyncAdapter;
import com.ssverma.iiitkota.sync_adapter.SyncCompletionListener;

public class Splash extends AppCompatActivity {

    private ImageView splash_logo;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        syncFirstTimeOnly();

        splash_logo = (ImageView) findViewById(R.id.splash_logo);

        Animation popIn = AnimationUtils.loadAnimation(this , R.anim.pop_in);
        splash_logo.startAnimation(popIn);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);
                finish();
            }
        }, 4000);

    }

    private void syncFirstTimeOnly(){

        prefs = getSharedPreferences("sync_status" , MODE_PRIVATE);
        if(!IIITK_Singleton.getInstance().getPreference().getBoolean("isSyncComplete" , false)) {
            // run your one time code

            //Toast.makeText(this , "Sync Started : Called in Splash" , Toast.LENGTH_SHORT).show();

            Bundle settingsBundle = new Bundle();
            settingsBundle.putBoolean(
                    ContentResolver.SYNC_EXTRAS_MANUAL, true);
            settingsBundle.putBoolean(
                    ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

            ContentResolver.requestSync(createDummyAccount(this) , DatabaseContract.AUTHORITY , settingsBundle);

        }
    }

    private Account createDummyAccount(Context context) {
        Account dummyAccount = new Account("IIIT KOTA" , "com.ssverma.iiitk");  // Acc , Acc Type
        AccountManager accountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(dummyAccount , null , null);

        return dummyAccount;
    }

}
