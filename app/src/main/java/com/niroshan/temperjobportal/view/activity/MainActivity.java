package com.niroshan.temperjobportal.view.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.databinding.ActivityMainBinding;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.services.LocationService;
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

public class MainActivity extends AppCompatActivity implements Observer {

    public DrawerLayout mDrawerLayout;
    public ActionBarDrawerToggle mDrawerToggle;
    public LocationService locationService;
    private ActivityMainBinding jobActivityBinding;
    private MainViewModel mainViewModel;
    private Toolbar toolbar;
    private CoordinatorLayout mainView;
    private ActionBar actionBar;
    private Location newLocation;
    private BroadcastReceiver locationUpdateReceiver;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            String name = className.getClassName();

            if (name.endsWith("LocationService")) {
                locationService = ((LocationService.LocationServiceBinder) service).getService();
                locationService.startUpdatingLocation();
            }

            MainActivity.this.locationService.startLogging();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            if (className.getClassName().equals("LocationService")) {
                locationService = null;
            }
        }
    };

    private static HashMap sortByValues(Map map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getKey())
                        .compareTo(((Map.Entry) (o2)).getKey());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setToolBar();
        setUpListOfJobsCardViews(jobActivityBinding.listJobCard);
        setUpObserver(mainViewModel);
        startLocationService();

        locationUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                newLocation = intent.getParcelableExtra("location");
               // Toast.makeText(getApplicationContext(), "(" + newLocation.getLatitude() + "," + newLocation.getLongitude() + ")", Toast.LENGTH_LONG).show();
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(locationUpdateReceiver, new IntentFilter("LocationUpdated"));
    }

    private void setToolBar() {
        toolbar = findViewById(R.id.toolbar);
        actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        serDrawer();
    }

    public void serDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mainView = findViewById(R.id.coordinatorLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mainView.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };
    }

    public void startLocationService() {
        final Intent serviceStart = new Intent(this.getApplication(), LocationService.class);
        this.getApplication().startService(serviceStart);
        this.getApplication().bindService(serviceStart, serviceConnection, Context.BIND_AUTO_CREATE);
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

        listView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });

        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(listView, headersDecor);
        listView.addOnItemTouchListener(touchListener);
    }

    public void setUpObserver(Observable observable) {
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

                for (BeanJobList b : beanJobLists) {
                    b.setKey(pair.getKey().toString());
                    b.setKeyvlaue(convertStringDateToMilliseconds(pair.getKey().toString(), "yyyy-MM-dd"));
                    b.setCurrentLat(newLocation.getLatitude());
                    b.setCurrentLong(newLocation.getLongitude());
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

    private long convertStringDateToMilliseconds(String timeString, String format) {
        Date timeInMilis = new Date();
        try {
            timeInMilis = new SimpleDateFormat(format).parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeInMilis.getTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
        SvgLoader.pluck().close();

        MainActivity.this.locationService.startLogging();

        try {
            if (locationUpdateReceiver != null) {
                unregisterReceiver(locationUpdateReceiver);
            }

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }


}
