package com.ssverma.iiitkota.admission;

/**
 * Created by IIITK on 6/12/2016.
 */
public class AdmissionStatisticsWrapper {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;

    public String getStartingRank() {
        return startingRank;
    }

    public void setStartingRank(String startingRank) {
        this.startingRank = startingRank;
    }

    private String startingRank;

    public String getClosingRank() {
        return closingRank;
    }

    public void setClosingRank(String closingRank) {
        this.closingRank = closingRank;
    }

    private String closingRank;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    private String branch;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    private String session;
}
