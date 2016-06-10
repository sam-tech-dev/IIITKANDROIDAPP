package com.ssverma.iiitkota;

/**
 * Created by IIITK on 6/3/2016.
 */
public class ContactsWrapper {

    public String getContact_id() { return contact_id; }

    public void setContact_id(String contact_id) { this.contact_id = contact_id; }

    private  String contact_id;


    public void setContact_server_id(Integer contact_server_id) {
        this.contact_server_id = contact_server_id;
    }

    private Integer contact_server_id;



    public String getContact_name() { return contact_name; }

    public void setContact_name(String contact_name) { this.contact_name = contact_name; }

    private String contact_name;



    public String getContact_email() { return contact_email; }

    public void setContact_email(String contact_email) { this.contact_email = contact_email; }

    private String contact_email;



    public String getContact_mobile_no() { return contact_mobile_no; }

    public void setContact_mobile_no(String contact_mobile_no) { this.contact_mobile_no = contact_mobile_no; }

    private String contact_mobile_no;



    public String getContact_category() { return contact_category; }

    public void setContact_category(String contact_category) { this.contact_category = contact_category; }

    private  String contact_category;



    public String getContact_designation() { return contact_designation; }

    public void setContact_designation(String contact_designation) { this.contact_designation = contact_designation; }

    private  String contact_designation;

}
