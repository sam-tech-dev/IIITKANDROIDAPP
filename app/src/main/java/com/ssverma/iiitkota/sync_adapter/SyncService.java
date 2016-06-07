package com.ssverma.iiitkota.sync_adapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by IIITK on 6/6/2016.
 */
public class SyncService extends Service{

    private static SyncAdapter syncAdapter;
    private static final Object syncAdapterLock = new Object();


    @Override
    public void onCreate() {

        synchronized (syncAdapterLock){
            if (syncAdapter == null){
                syncAdapter = new SyncAdapter(getApplicationContext() , true);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }

}
