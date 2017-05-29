package com.sphincs.vo;

public class Contact {


    private String date;
    private String age;
    private String reason;
    private String message;
    private String url;

    public Contact() {
    }

    public Contact(String date, String age, String reason, String message, String url) {
        this.date = date;
        this.age = age;
        this.reason = reason;
        this.message = message;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
