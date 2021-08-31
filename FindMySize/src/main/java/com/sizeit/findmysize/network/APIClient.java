package com.sizeit.findmysize.network;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sizeit.findmysize.BuildConfig;
import com.sizeit.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Gson gson = new GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create();

    private static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(new HeaderInterceptor());
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
//            client.addInterceptor(loggingInterceptor);
//        }
        return client;
    }

    public static Retrofit getClient(Activity activity) {
        return new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient().build())
                .build();
    }

    public static Retrofit getEventClient(Activity activity) {
        return new Retrofit.Builder()
                .baseUrl(Constants.baseUrlEvent)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient().build())
                .build();
    }
}