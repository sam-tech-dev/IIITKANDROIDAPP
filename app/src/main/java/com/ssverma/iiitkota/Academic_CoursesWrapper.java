package com.ssverma.iiitkota;

/**
 * //Author-Dixit Chauhan      :16/06/2016
 */
public class Academic_CoursesWrapper {

    private String course_name ;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }


    private String code ;
    private String abbr ;


    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    private String credit ;
    private String semester ;

}
