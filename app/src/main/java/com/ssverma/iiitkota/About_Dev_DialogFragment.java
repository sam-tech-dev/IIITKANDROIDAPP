package com.ssverma.iiitkota;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by IIITK on 6/29/2016.
 */
public class About_Dev_DialogFragment extends DialogFragment {

    public static About_Dev_DialogFragment newInstance(String[] dev_data) {
        About_Dev_DialogFragment fragment = new About_Dev_DialogFragment();
        Bundle args = new Bundle();
        args.putStringArray("dev_data" , dev_data);
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String[] dev_data = getArguments().getStringArray("dev_data");

        LinearLayout view = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.about_developers_detailed, null);

        CircleImageView dev_image = (CircleImageView) view.findViewById(R.id.dev_image_detailed_view);
        TextView dev_name = (TextView) view.findViewById(R.id.dev_name_detailed);
        TextView dev_role = (TextView) view.findViewById(R.id.dev_role_detailed);

        Picasso.with(getActivity()).load(dev_data[0] /*Image Link*/).placeholder(R.drawable.person_placeholder).into(dev_image);
        dev_name.setText(dev_data[1]);
        dev_role.setText("(" + dev_data[2] + ")");

        return new AlertDialog.Builder(getActivity(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle("")
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
