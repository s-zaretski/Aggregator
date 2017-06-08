package com.sphincs.vo;

public class Advert {


    private String date;
    private String message;
    private String cost;
    private String place;
    private String tags;
    private String url;

    public Advert() {
    }

    public Advert(final String date, final String message, final String cost, final String place, final String tags, final String url) {
        this.date = date;
        this.message = message;
        this.cost = cost;
        this.place = place;
        this.tags = tags;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(final String place) {
        this.place = place;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(final String tags) {
        this.tags = tags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
