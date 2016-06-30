package com.ssverma.iiitkota;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by IIITK on 6/25/2016.
 */
public class AnimToolbarSyncIcon {

    protected MenuItem refreshItem = null;
    private Context context;

    AnimToolbarSyncIcon(Context context){
        this.context = context;
    }

    protected void setRefreshItem(MenuItem item) {
        refreshItem = item;
    }

    protected void stopRefresh() {
        if (refreshItem != null) {
            refreshItem.setActionView(null);
        }
    }

    protected void runRefresh() {
        if (refreshItem != null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ImageView iv = (ImageView)inflater.inflate(R.layout.rotation_layout, null);
            Animation rotation = AnimationUtils.loadAnimation(context, R.anim.rotate);
            rotation.setRepeatCount(Animation.INFINITE);
            iv.startAnimation(rotation);
            refreshItem.setActionView(iv);
        }
    }

}
