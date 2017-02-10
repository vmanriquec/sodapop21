package com.food.sistemas.sodapopapp.response;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RedemnorteApiService {
    @POST("/api.php/almacen")
    Call<ResponsableResponse> getResponsables();


}
