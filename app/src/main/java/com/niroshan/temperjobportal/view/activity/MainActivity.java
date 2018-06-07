package com.niroshan.temperjobportal.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.databinding.ActivityMainBinding;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.view.adapter.JobCardAdapter;
import com.niroshan.temperjobportal.view.adapter.RecyclerHeaderItemAdapter;
import com.niroshan.temperjobportal.viewModel.MainViewModel;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        //setContentView(R.layout.activity_main);
        setUpObserver(mainViewModel);
    }

    private void initDataBinding() {
        jobActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        jobActivityBinding.setJobCardViewModel(mainViewModel);
    }

    private void setUpListOfJobsCardViews(RecyclerView listView) {
//        JobCardAdapter jobCardAdapter = new JobCardAdapter();
//        listJobs.setAdapter(jobCardAdapter);
//        listJobs.setLayoutManager(new LinearLayoutManager(this));

        RecyclerHeaderItemAdapter jobCardAdapter = new RecyclerHeaderItemAdapter();
        listView.setAdapter(jobCardAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(jobCardAdapter);
        listView.addItemDecoration(headersDecor);

        listView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(listView, headersDecor);
        listView.addOnItemTouchListener(touchListener);
    }

    public void setUpObserver(Observable observable){
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if(observable instanceof MainViewModel){
            Map<String, ArrayList<BeanJobList>> jobList = new HashMap<>();
            ArrayList<BeanJobList> values = new ArrayList<>();
            //JobCardAdapter jobCardAdapter = (JobCardAdapter) jobActivityBinding.listJobCard.getAdapter();

            RecyclerHeaderItemAdapter jobCardAdapter = (RecyclerHeaderItemAdapter) jobActivityBinding.listJobCard.getAdapter();
            MainViewModel mainViewModel = (MainViewModel) observable;
            jobList = mainViewModel.getJobList();

            for (Map.Entry<String, ArrayList<BeanJobList>> entry : jobList.entrySet()) {
                String key = entry.getKey();
               values = entry.getValue();
            }

            jobCardAdapter.setJobList(values);
        }

    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }


}
