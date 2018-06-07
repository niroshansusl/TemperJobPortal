package com.niroshan.temperjobportal.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niroshan.temperjobportal.config.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class ServiceGenerator {

    public static ApiInterface create() {

        Gson gson = new GsonBuilder() .setLenient() .create();
        //OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
               // .client(okHttpClient.newBuilder().connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }

    private static Retrofit mRetrofitInstance = null;

    public static <S> S CreateService(Class<S> serviceClass) {

        Gson gson = new GsonBuilder() .setLenient() .create();
        OkHttpClient okHttpClient = new OkHttpClient();
        mRetrofitInstance = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(okHttpClient.newBuilder().connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return mRetrofitInstance.create(serviceClass);
    }
}
