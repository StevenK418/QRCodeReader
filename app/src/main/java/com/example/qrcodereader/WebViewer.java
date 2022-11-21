package com.example.qrcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class WebViewer extends AppCompatActivity
{
    WebView siteViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewer);

        //Initialize the webView object
        siteViewer = findViewById(R.id.wvSiteViewer);

        //Load in the intent of the main activity
        Intent intent = getIntent();
        //Get and store the url passed from the main activity
        String siteUrl = intent.getStringExtra("siteUrl");

        //Load teh website
        LoadWebsite(siteUrl);
    }

    //Loads a website with a given domain
    public void LoadWebsite(String url)
    {
        //Create a new webclient instance
        siteViewer.setWebViewClient(new WebViewClient());
        //Pass the url into the siteview and load
        siteViewer.loadUrl(url);
    }
}