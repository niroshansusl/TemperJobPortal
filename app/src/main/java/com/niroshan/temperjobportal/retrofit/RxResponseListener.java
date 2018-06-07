package com.niroshan.temperjobportal.retrofit;

import retrofit2.Response;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public interface RxResponseListener<T> {

    void serviceResponse(Response<ServiceResponse<T>> serviceResponseResponse);

    void serviceFailed(String message);

    void serviceThrowable(Throwable throwable);
}