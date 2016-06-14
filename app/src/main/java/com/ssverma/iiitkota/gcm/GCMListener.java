package com.ssverma.iiitkota.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.gcm.GcmListenerService;
import com.ssverma.iiitkota.Home;
import com.ssverma.iiitkota.R;

/**
 * Created by Sattar on 6/13/2016.
 */

public class GCMListener  extends GcmListenerService {


    @Override
    public void onMessageReceived(String from, Bundle data) {

        String   message = data.getString("message");

        Log.d("azad", "From:" + from);
        Log.d("azad", "Message: " + message);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
            Log.d("azad", "Message2");
        } else {
            // normal downstream message.
        }


        sendNotification(message);


    }


    private void sendNotification(String msg) {
        Intent resultIntent = new Intent(this, Home.class);
        resultIntent.putExtra("msg", msg);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mNotifyBuilder;
        NotificationManager mNotificationManager;

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("IIITK")
                .setContentText("You've received new message.")
                .setSmallIcon(R.drawable.splash_logo);
        // Set pending intent
        mNotifyBuilder.setContentIntent(resultPendingIntent);

        // Set Vibrate, Sound and Light
        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        mNotifyBuilder.setDefaults(defaults);
        // Set the content for Notification
        mNotifyBuilder.setContentText(msg);
        // Set autocancel
        mNotifyBuilder.setAutoCancel(true);
        // Post a notification
        mNotificationManager.notify(1, mNotifyBuilder.build());

    }



}
