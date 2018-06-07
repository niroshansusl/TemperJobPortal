package com.niroshan.temperjobportal.retrofit.SyncClass;

import android.app.Activity;
import android.util.Log;

import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.model.BeanJobListResponse;
import com.niroshan.temperjobportal.retrofit.ApiInterface;
import com.niroshan.temperjobportal.retrofit.RxResponseListener;
import com.niroshan.temperjobportal.retrofit.ServiceGenerator;
import com.niroshan.temperjobportal.retrofit.ServiceResponse;

import retrofit2.Call;
import retrofit2.Response;

public class GetJobListResponse {

    private static String TAG = GetJobListResponse.class.getSimpleName();
    private Activity activity;
    private RxResponseListener rxResponseListener;

    public GetJobListResponse(Activity activity, RxResponseListener rxResponseListener) {
        this.activity = activity;
        this.rxResponseListener = rxResponseListener;
    }

    public void loadJobAPIRXWay() {

        ServiceGenerator.CreateService(ApiInterface.class)
                .getJobListResponse()
                .enqueue(new retrofit2.Callback<BeanJobListResponse>() {
                    @Override
                    public void onResponse(Call<BeanJobListResponse> call, Response<BeanJobListResponse> response) {
                        Log.d(TAG, "Request URL :" + call.request().url().toString());

                        try {
                            if (response.isSuccessful() && response.body() != null) {
                                if (response.body().getJobList() != null) {
                                    BeanJobListResponse serviceResponseBody = response.body();
                                    if (serviceResponseBody != null) {
                                        rxResponseListener.serviceResponse(response);
                                    }
                                } else {
                                    System.out.println("Service Request Failed");
                                    rxResponseListener.serviceFailed(activity.getResources().getString(R.string.error_message_loading_jobs));
                                }
                            }

                        } catch (Throwable t){
                            rxResponseListener.serviceThrowable(t);
                        }
                    }

                    @Override
                    public void onFailure(Call<BeanJobListResponse> call, Throwable t) {
                        System.out.println(t);
                        rxResponseListener.serviceThrowable(t);
                    }
                });
    }
}
