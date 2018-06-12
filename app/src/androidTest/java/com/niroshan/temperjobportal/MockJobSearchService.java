package com.niroshan.temperjobportal;

import com.niroshan.temperjobportal.config.AppConstants;
import com.niroshan.temperjobportal.model.BeanJobListResponse;
import com.niroshan.temperjobportal.retrofit.ApiInterface;

import io.reactivex.Observable;
import retrofit2.mock.BehaviorDelegate;

public class MockJobSearchService implements ApiInterface{

    private final BehaviorDelegate<ApiInterface> delegate;

    public MockJobSearchService(BehaviorDelegate<ApiInterface> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Observable<BeanJobListResponse> fetchJobList(String url) {

        BeanJobListResponse jobSearchResponse = new BeanJobListResponse();

        return delegate.returningResponse(jobSearchResponse).fetchJobList(AppConstants.MAIN_URL);
    }
}
