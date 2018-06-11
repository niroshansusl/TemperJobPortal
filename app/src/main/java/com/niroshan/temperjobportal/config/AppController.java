package com.niroshan.temperjobportal.config;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.niroshan.temperjobportal.retrofit.ApiInterface;
import com.niroshan.temperjobportal.retrofit.ServiceGenerator;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class AppController extends Application {

    private ApiInterface apiService;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public ApiInterface getJobService() {
        if (apiService == null) {
            apiService = ServiceGenerator.create();
        }

        return apiService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    public void setJobService(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
