package com.niroshan.temperjobportal.viewModel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.config.AppController;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.model.BeanJobListResponse;
import com.niroshan.temperjobportal.retrofit.ApiInterface;
import com.niroshan.temperjobportal.retrofit.RxResponseListener;
import com.niroshan.temperjobportal.retrofit.ServiceResponse;
import com.niroshan.temperjobportal.retrofit.SyncClass.GetJobListResponse;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Response;

import static com.niroshan.temperjobportal.config.AppConstants.RANDOM_USER_URL;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class MainViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt jobRecycler;
    public ObservableInt jobLabel;
    public ObservableField<String> messageLabel;
//    private ArrayList<BeanJobList> jobList;
    private Map<String, ArrayList<BeanJobList>> jobList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Context context) {
        this.context = context;
        this.jobList = new HashMap<>();
//        this.jobList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        jobRecycler = new ObservableInt(View.GONE);
        jobLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading_jobs));
    }

    public void onClickFabToLoad(View view) {
        initializeViews();
        fetchJobList();
    }

    public void initializeViews() {
        jobLabel.set(View.GONE);
        jobRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchJobList() {

           /* new GetJobListResponse((Activity) context, new RxResponseListener<BeanJobListResponse>() {

                @Override
                public void serviceResponse(Response<ServiceResponse<BeanJobListResponse>> response) {

                    ArrayList<BeanJobList> data = new ArrayList<>(Arrays.asList(response.body().data.getListOne()));
                    updateJobDataList(data);
                    progressBar.set(View.GONE);
                    jobLabel.set(View.GONE);
                    jobRecycler.set(View.VISIBLE);
                }

                @Override
                public void serviceFailed(String message) {
                    messageLabel.set(context.getString(R.string.error_message_loading_jobs));
                    progressBar.set(View.GONE);
                    jobLabel.set(View.VISIBLE);
                    jobRecycler.set(View.GONE);
                }

                @Override
                public void serviceThrowable(Throwable throwable) {
                    messageLabel.set(context.getString(R.string.error_message_loading_jobs));
                    progressBar.set(View.GONE);
                    jobLabel.set(View.VISIBLE);
                    jobRecycler.set(View.GONE);
                }

            }).loadJobAPIRXWay();*/

        AppController appController = AppController.create(context);
        ApiInterface usersService = appController.getUserService();

        Disposable disposable = usersService.fetchJobList(RANDOM_USER_URL)
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanJobListResponse>() {
                    @Override public void accept(BeanJobListResponse jobResponse) throws Exception {
                        updateJobDataList(jobResponse.getJobList());
                        progressBar.set(View.GONE);
                        jobLabel.set(View.GONE);
                        jobRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_message_loading_jobs));
                        progressBar.set(View.GONE);
                        jobLabel.set(View.VISIBLE);
                        jobRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

   /* private void updateJobDataList(ArrayList<BeanJobList> mJobList){
        jobList.addAll(mJobList);
        setChanged();
        notifyObservers();
    }*/

    private void updateJobDataList(Map<String, ArrayList<BeanJobList>> mJobList){
        jobList.putAll(mJobList);
        setChanged();
        notifyObservers();
    }

    public Map<String, ArrayList<BeanJobList>> getJobList() {
        return jobList;
    }

   /* public ArrayList<BeanJobList> getJobList() {
        return jobList;
    }*/

    private void unSubscribeFromObservable(){
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }
}
