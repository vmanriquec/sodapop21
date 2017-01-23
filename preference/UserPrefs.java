package com.food.sistemas.sodapopapp.preference;

import android.content.Context;

public class UserPrefs {

    private SingletonPrefs prefs;

    private static final String KEY_LOGGED = "logged";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IDUSUARIO = "IdUsuario";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_APELLIDO = "apellido";
    private static final String KEY_LENGUAJE = "lenguaje";
    private static final String KEY_GENERO = "genero";
    private static final String KEY_ONOMASTICO = "onomastico";
    private static final String KEY_FOTO = "foto";
    private static final String KEY_CIUDAD = "ciudad";
    private static final String KEY_IDFACEBOOK = "fbId";
    private static final String KEY_LOAD_FOTO_FB = "load_foto_fb";
    private static final String KEY_ESTADO = "estado";

    private static final String KEY_LATITUD = "latitud";
    private static final String KEY_LONGITUD = "longitud";
    private static final String KEY_SPEED = "speed";
    private static final String KEY_BATERY = "batery";

    private static final String KEY_SOCKET_CONNECTED = "socket_connected";
    private static final String KEY_SOCKET_ID = "socket_id";

    public UserPrefs(Context context) {
        prefs = SingletonPrefs.getInstance(context);
    }

    public void reset() {
        setKeyLogged(false);
        setKeyIdFacebook("");
        setKeyLoadFotoFb(false);
        setKeyIdusuario("");
        setKeyCiudad("");
        setKeyGenero("M");
        setKeyOnomastico("");
        setKeySocketConnected(false);
        setKeySocketId("");
        setKeySpeed("0.0");
        setKeyBatery("");
    }

    public void setKeySpeed(String value) {
        prefs.put(KEY_SPEED, value);
    }

    public String getKeySpeed() {
        return prefs.getString(KEY_SPEED);
    }

    public void setKeyBatery(String value) {
        prefs.put(KEY_BATERY, value);
    }

    public String getKeyBatery() {
        return prefs.getString(KEY_BATERY);
    }

    public void setKeyLatitud(String value) {
        prefs.put(KEY_LATITUD, value);
    }

    public String getKeyLatitud() {
        return prefs.getString(KEY_LATITUD);
    }

    public void setKeyLongitud(String value) {
        prefs.put(KEY_LONGITUD, value);
    }

    public String getKeyLongitud() {
        return prefs.getString(KEY_LONGITUD);
    }

    public void setKeyEmail(String value) {
        prefs.put(KEY_EMAIL, value);
    }

    public String getKeyEmail() {
        return prefs.getString(KEY_EMAIL);
    }

    public void setKeyEstado(String value) {
        prefs.put(KEY_ESTADO, value);
    }

    public String getKeyEstado() {
        return prefs.getString(KEY_ESTADO);
    }

    public void setKeyIdusuario(String value) {
        prefs.put(KEY_IDUSUARIO, value);
    }

    public String getKeyIdUsuario() {
        return prefs.getString(KEY_IDUSUARIO);
    }

    public void setKeyNombre(String value) {
        prefs.put(KEY_NOMBRE, value);
    }

    public String getKeyNombre() {
        return prefs.getString(KEY_NOMBRE);
    }

    public void setKeyApellido(String value) {
        prefs.put(KEY_APELLIDO, value);
    }

    public String getKeyApellido() {
        return prefs.getString(KEY_APELLIDO);
    }

    public void setKeyLenguaje(String value) {
        prefs.put(KEY_LENGUAJE, value);
    }

    public String getKeyLenguaje() {
        return prefs.getString(KEY_LENGUAJE);
    }

    public void setKeyGenero(String value) {
        prefs.put(KEY_GENERO, value);
    }

    public String getKeyGenero() {
        return prefs.getString(KEY_GENERO);
    }

    public void setKeyOnomastico(String value) {
        prefs.put(KEY_ONOMASTICO, value);
    }

    public String getKeyOnomastico() {
        return prefs.getString(KEY_ONOMASTICO);
    }

    public void setKeyFoto(String value) {
        prefs.put(KEY_FOTO, value);
    }

    public String getKeyFoto() {
        return prefs.getString(KEY_FOTO);
    }

    public void setKeyCiudad(String value) {
        prefs.put(KEY_CIUDAD, value);
    }

    public String getKeyCiudad() {
        return prefs.getString(KEY_CIUDAD);
    }

    public void setKeyIdFacebook(String value) {
        prefs.put(KEY_IDFACEBOOK, value);
    }

    public String getKeyIdFacebook() {
        return prefs.getString(KEY_IDFACEBOOK);
    }

    public void setKeyLogged(Boolean val) {
        prefs.put(KEY_LOGGED, val);
    }

    public Boolean getKeyLogged() {
        return prefs.getBoolean(KEY_LOGGED);
    }

    public void setKeyLoadFotoFb(Boolean val) {
        prefs.put(KEY_LOAD_FOTO_FB, val);
    }

    public Boolean getKeyLoadFotoFb() {
        return prefs.getBoolean(KEY_LOAD_FOTO_FB);
    }


    public void setKeySocketConnected(Boolean val) {
        prefs.put(KEY_SOCKET_CONNECTED, val);
    }

    public Boolean getKeySocketConnected() {
        return prefs.getBoolean(KEY_SOCKET_CONNECTED);
    }

    public void setKeySocketId(String value) {
        prefs.put(KEY_SOCKET_ID, value);
    }

    public String getKeySocketId() {
        return prefs.getString(KEY_SOCKET_ID);
    }

}
