package com.ssverma.iiitkota;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by IIITK on 5/27/2016.
 */
public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ViewHolder>{

    //private String[] dummy;
    private Context context;
    private ArrayList<ContactsWrapper> listContact;

    private RCVClickListener listener;

    Contact_Adapter(Context context , ArrayList<ContactsWrapper> listContact){
        this.context = context;
        this.listContact = listContact;
    }

    public void setOnRCVClickListener(RCVClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row_item , parent , false);
        ViewHolder holder = new ViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.contact_name.setText(listContact.get(position).getContact_name());
        holder.contact_email.setText(listContact.get(position).getContact_email());
        holder.contact_mobile_no.setText(listContact.get(position).getContact_mobile_no());
        holder.contact_designation.setText(listContact.get(position).getContact_designation());

    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout itemHolder;
        TextView contact_name;
        TextView contact_email;
        TextView contact_mobile_no;
        TextView contact_designation;
       // Button call_button;
      //  CircleImageView contact_image;

        public ViewHolder(View itemView) {
            super(itemView);

            itemHolder = (RelativeLayout) itemView.findViewById(R.id.contact_row_item_holder);
            //itemHolder.setOnClickListener(this);

            contact_name = (TextView) itemView.findViewById(R.id.contact_name);
            contact_email = (TextView) itemView.findViewById(R.id.contact_email);
            contact_mobile_no = (TextView) itemView.findViewById(R.id.contact_mobile_no);
            contact_designation = (TextView) itemView.findViewById(R.id.contact_designation);

        //    call_button = (Button) itemView.findViewById(R.id.buttonCall);
            itemHolder.setOnClickListener(this);
           // contact_image = (CircleImageView) itemView.findViewById(R.id.contact_image);
        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onRCVClick(v , getAdapterPosition());
            }
        }
    }

}
