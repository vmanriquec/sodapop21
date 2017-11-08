package com.food.sistemas.sodapopapp;

/**
 * Created by usuario on 22/06/2017.
 */


public class Message {
    private String message,user_name,facebook,latitud,longitud,foto;

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



    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }



    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Message() {
    }
    public Message(String message, String user_name,String facebook,String foto,String latitud,String longitud) {
        this.message = message;
        this.user_name = user_name;
        this.facebook=facebook;
        this.foto=foto;
        this.longitud=longitud;
        this.latitud=latitud;
    }
}