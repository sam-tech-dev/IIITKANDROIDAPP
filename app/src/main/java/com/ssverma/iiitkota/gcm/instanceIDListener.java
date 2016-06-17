package com.ssverma.iiitkota.gcm;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;


/**
 * Created by Sattar on 6/13/2016.
 */

public class instanceIDListener extends InstanceIDListenerService  {

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, registrationService.class);
        startService(intent);
    }



}
