package com.food.sistemas.sodapopapp.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by sistemas on 07/02/2017.
 */

public interface Traeralamacenapi {
    @FormUrlEncoded
    @POST("/traeram.php")
    public void BuscarAlmacen(

            Callback<Response> callback);


}
