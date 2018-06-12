package com.niroshan.temperjobportal.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.model.BeanJobList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class JobDetailActivity extends AppCompatActivity {

    private WebView webview;
    private static final String JOB_ITEM = "JOB_ITEM";
    private static ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        webview = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progress_bar);
        getExtrasFromIntent();
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
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(JobDetailActivity.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                //String webUrl = webview.getUrl();
            }

    });
        webview.loadUrl(url);
    }
}
