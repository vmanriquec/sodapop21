package com.food.sistemas.sodapopapp;

/**
 * Created by usuario on 22/06/2017.
 */


public class Message {
    private String message,user_name,facebook;

    public String getFacebook() {
        return facebook;
    }
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public Message() {
    }
    public Message(String message, String user_name,String facebook) {
        this.message = message;
        this.user_name = user_name;
        this.facebook=facebook;
    }
}