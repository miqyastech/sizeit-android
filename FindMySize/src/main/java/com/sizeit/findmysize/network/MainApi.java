package com.sizeit.findmysize.network;

import com.sizeit.findmysize.model.DataAPI;
import com.sizeit.findmysize.model.ResponseSize;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainApi {

    @POST("measurements")
    Call<ResponseSize> getAllSizes(@Body DataAPI dataAPI);

}