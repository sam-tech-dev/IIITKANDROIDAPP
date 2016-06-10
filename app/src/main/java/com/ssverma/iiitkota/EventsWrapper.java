package com.ssverma.iiitkota;

/**
 * //Author-Dixit Chauhan      :03/06/2016
 */
public class EventsWrapper {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String subtitle;
    private String date;
    private String detail;
    private String image;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEvents_server_id() {
        return events_server_id;
    }

    public void setEvents_server_id(int events_server_id) {
        this.events_server_id = events_server_id;
    }

    private int events_server_id;
    private String author;

}
