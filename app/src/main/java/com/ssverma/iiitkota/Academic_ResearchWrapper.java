package com.ssverma.iiitkota;

/**
 * Author-Dixit Chauhan : 13/06/2016
 */
public class Academic_ResearchWrapper {

    private String research_type ;
    private String person_name ;
    private String person_position ;
    private String project_name ;

    public String getResearch_type() {
        return research_type;
    }

    public void setResearch_type(String research_type) {
        this.research_type = research_type;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_position() {
        return person_position;
    }

    public void setPerson_position(String person_position) {
        this.person_position = person_position;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;

    public String getPerson_image_link() {
        return person_image_link;
    }

    public void setPerson_image_link(String person_image_link) {
        this.person_image_link = person_image_link;
    }

    public String getProject_image_link() {
        return project_image_link;
    }

    public void setProject_image_link(String project_image_link) {
        this.project_image_link = project_image_link;
    }

    private String person_image_link,project_image_link;

}
