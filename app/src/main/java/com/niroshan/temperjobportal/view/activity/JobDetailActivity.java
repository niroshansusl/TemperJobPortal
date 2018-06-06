package com.niroshan.temperjobportal.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.niroshan.temperjobportal.model.BeanJobList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class JobDetailActivity extends AppCompatActivity {

    private static final String JOB_ITEM = "job_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void displayHomeAsUpEnabled() {

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent loadDetailView(Context context, BeanJobList jobItem) {
        Intent intent = new Intent(context, JobDetailActivity.class);
        intent.putExtra(JOB_ITEM, jobItem);
        return intent;
    }
}
