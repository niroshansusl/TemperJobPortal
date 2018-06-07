package com.niroshan.temperjobportal.retrofit;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class ServiceResponse<T> {

    public ServiceResponse() {
    }

    public T data;

    public T getResponse() {
        return data;
    }

    public void setResponse(T response) {
        this.data = response;
    }
}
