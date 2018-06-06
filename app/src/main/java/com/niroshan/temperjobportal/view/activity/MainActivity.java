package com.niroshan.temperjobportal.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.databinding.ActivityMainBinding;
import com.niroshan.temperjobportal.view.adapter.JobCardAdapter;
import com.niroshan.temperjobportal.viewModel.MainViewModel;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    private ActivityMainBinding jobActivityBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(jobActivityBinding.toolbar);
        setUpListOfJobsCardViews(jobActivityBinding.listJobCard);
        setContentView(R.layout.activity_main);
        setUpObserver(mainViewModel);
    }

    private void initDataBinding() {
        jobActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        jobActivityBinding.setJobCardViewModel(mainViewModel);
    }

    private void setUpListOfJobsCardViews(RecyclerView listJobs) {
        JobCardAdapter jobCardAdapter = new JobCardAdapter();
        listJobs.setAdapter(jobCardAdapter);
        listJobs.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpObserver(Observable observable){
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(observable instanceof MainViewModel){
            JobCardAdapter jobCardAdapter = (JobCardAdapter) jobActivityBinding.listJobCard.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            jobCardAdapter.setJobList(mainViewModel.getJobList().get(0));
        }

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }


}
