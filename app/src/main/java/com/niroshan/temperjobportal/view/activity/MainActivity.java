package com.niroshan.temperjobportal.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.databinding.ActivityMainBinding;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.view.adapter.RecyclerHeaderItemAdapter;
import com.niroshan.temperjobportal.viewModel.MainViewModel;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{

    private ActivityMainBinding jobActivityBinding;
    private MainViewModel mainViewModel;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpListOfJobsCardViews(jobActivityBinding.listJobCard);
        setUpObserver(mainViewModel);
    }

    private void initDataBinding() {
        jobActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        jobActivityBinding.setJobCardViewModel(mainViewModel);
    }

    private void setUpListOfJobsCardViews(RecyclerView listView) {

        RecyclerHeaderItemAdapter jobCardAdapter = new RecyclerHeaderItemAdapter();
        listView.setAdapter(jobCardAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(jobCardAdapter);
        listView.addItemDecoration(headersDecor);

        listView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL){
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });

        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(listView, headersDecor);
        listView.addOnItemTouchListener(touchListener);
    }

    public void setUpObserver(Observable observable){
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        if (observable instanceof MainViewModel) {
            Map<String, ArrayList<BeanJobList>> jobList = new HashMap<>();

            ArrayList<BeanJobList> values = new ArrayList<>();

            MainViewModel mainViewModel = (MainViewModel) observable;
            jobList = mainViewModel.getJobList();
            jobList = sortByValues(jobList);

            Iterator it = jobList.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                ArrayList<BeanJobList> beanJobLists = (ArrayList<BeanJobList>) pair.getValue();

                for(BeanJobList b : beanJobLists){
                    b.setKey(pair.getKey().toString());
                    b.setKeyvlaue(convertStringDateToMilliseconds(pair.getKey().toString(), "yyyy-MM-dd"));
                }

                values.addAll(beanJobLists);


            }

            jobActivityBinding.listJobCard.setLayoutManager(new LinearLayoutManager(this));

            RecyclerHeaderItemAdapter mAdapter = new RecyclerHeaderItemAdapter(values, this);
            jobActivityBinding.listJobCard.setAdapter(mAdapter);

            final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(mAdapter);
            jobActivityBinding.listJobCard.addItemDecoration(headersDecor);


            jobActivityBinding.listJobCard.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            StickyRecyclerHeadersTouchListener touchListener =
                    new StickyRecyclerHeadersTouchListener(jobActivityBinding.listJobCard, headersDecor);
            jobActivityBinding.listJobCard.addOnItemTouchListener(touchListener);


        }

    }

    private long convertStringDateToMilliseconds(String timeString , String format ){
        Date timeInMilis = new Date();
        try {
            timeInMilis = new SimpleDateFormat(format).parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilis.getTime();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }

    private static HashMap sortByValues(Map map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getKey())
                        .compareTo(((Map.Entry) (o2)).getKey());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }


}
