package com.niroshan.temperjobportal.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.model.BeanJobList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class JobDetailActivity extends AppCompatActivity {

    private WebView webview;
    private static final String JOB_ITEM = "JOB_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        webview = findViewById(R.id.webView);
        getExtrasFromIntent();
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

    private void getExtrasFromIntent(){
        BeanJobList jobItem = (BeanJobList) getIntent().getSerializableExtra(JOB_ITEM);
        loadWebView(jobItem.getUrl());
    }

    private void loadWebView(String url){
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl(url);
    }
}
