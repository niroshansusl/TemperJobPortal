package com.niroshan.temperjobportal.retrofit;

import com.niroshan.temperjobportal.model.BeanJobListResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public interface ApiInterface {

    @GET
    Observable<BeanJobListResponse> fetchJobList(@Url String url);

    @GET("niroshan/jobsearch.json")
    Call<BeanJobListResponse> getJobListResponse();
}
