package com.ssverma.iiitkota;

/**
 * Created by DIXIT on 8/6/2016.
 */
public class ScholarshipWrapper {
    private String name;
    private String income;

    public String getName() {
        return name;
    }

    public String getIncome() {
        return income;
    }

    public String getAcademic() {
        return academic;
    }

    public String getCategory() {
        return category;
    }

    public String getValue() {
        return value;
    }

    public String getProcedure() {
        return procedure;
    }

    public String getRemark() {
        return remark;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String academic;
    private String category;
    private String value;
    private String procedure;
    private String remark;
    private String link;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public int getScholarship_server_id() {
        return scholarship_server_id;
    }

    public void setScholarship_server_id(int scholarship_server_id) {
        this.scholarship_server_id = scholarship_server_id;
    }

    private int scholarship_server_id;
}
