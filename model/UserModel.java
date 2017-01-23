package com.food.sistemas.sodapopapp.model;

import java.util.ArrayList;

public class UserModel {

    Boolean success;
    String message;
    String IdUsuario, NombreUsuario, ApellidosUsuario, LenguajeUsuario, GeneroUsuario,
            OnomasticoUsuario, FotoURL, CiudadUsuario, IdFacebook, NickUsuario;

    ArrayList<ContactItemModel> Contacts;

    public ArrayList<ContactItemModel> getContactos() {
        return Contacts;
    }

    public void setContactos(ArrayList<ContactItemModel> contactos) {
        Contacts = contactos;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getNickUsuario() {
        return NickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        NickUsuario = nickUsuario;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return ApellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        ApellidosUsuario = apellidosUsuario;
    }

    public String getLenguajeUsuario() {
        return LenguajeUsuario;
    }

    public void setLenguajeUsuario(String lenguajeUsuario) {
        LenguajeUsuario = lenguajeUsuario;
    }

    public String getGeneroUsuario() {
        return GeneroUsuario;
    }

    public void setGeneroUsuario(String generoUsuario) {
        GeneroUsuario = generoUsuario;
    }

    public String getOnomasticoUsuario() {
        return OnomasticoUsuario;
    }

    public void setOnomasticoUsuario(String onomasticoUsuario) {
        OnomasticoUsuario = onomasticoUsuario;
    }

    public String getFotoURL() {
        return FotoURL;
    }

    public void setFotoURL(String fotoURL) {
        FotoURL = fotoURL;
    }

    public String getCiudadUsuario() {
        return CiudadUsuario;
    }

    public void setCiudadUsuario(String ciudadUsuario) {
        CiudadUsuario = ciudadUsuario;
    }

    public String getIdFacebook() {
        return IdFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        IdFacebook = idFacebook;
    }
}
