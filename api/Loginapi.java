package com.food.sistemas.sodapopapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface Loginapi {
    @FormUrlEncoded
    @POST("/login.php")
    public void insertUser(
            @Field("nombreusuario") String nombreusuario,
            @Field("claveusuario") String claveusuario,

            Callback<Response> callback);




}
