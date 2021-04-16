package com.femi9.findmysize.network;

import com.femi9.tracking.main.findmysize.model.DataAPI;
import com.femi9.tracking.main.findmysize.model.ResponseSize;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainApi {

    @POST("measurements")
    Call<ResponseSize> getAllSizes(@Body DataAPI dataAPI);

}