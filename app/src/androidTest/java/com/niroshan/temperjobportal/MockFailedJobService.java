package com.niroshan.temperjobportal;

import com.niroshan.temperjobportal.model.BeanJobListResponse;
import com.niroshan.temperjobportal.retrofit.ApiInterface;
import io.reactivex.Observable;
import retrofit2.mock.BehaviorDelegate;

public class MockFailedJobService implements ApiInterface {

    private static final String TAG = "MockFailedQOD";
    private final BehaviorDelegate<ApiInterface> delegate;

    public MockFailedJobService(BehaviorDelegate<ApiInterface> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Observable<BeanJobListResponse> fetchJobList(String url) {

        return null;
    }
}
