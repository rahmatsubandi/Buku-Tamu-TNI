package com.Mabes.datatamu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.Mabes.datatamu.R;

public class NewsActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        webView = (WebView)findViewById(R.id.webview);
        webSettings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://tni.mil.id/");
    }
}