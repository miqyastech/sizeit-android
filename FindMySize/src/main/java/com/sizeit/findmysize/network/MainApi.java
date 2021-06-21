package com.sizeit.findmysize.network;

import com.sizeit.findmysize.model.DataAPI;
import com.sizeit.findmysize.model.ResponseSize;
import com.sizeit.findmysize.model.event.DataEvent;
import com.sizeit.findmysize.model.event.ResponseEvent;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainApi {

    @POST("measurements")
    Call<ResponseSize> getAllSizes(@Body DataAPI dataAPI);

    @POST("create")
    Call<ResponseEvent> createEvent(@Body DataEvent dataAPI);
}