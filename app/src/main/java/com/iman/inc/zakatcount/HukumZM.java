package com.iman.inc.zakatcount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HukumZM extends AppCompatActivity {
    //Jangan lupa tambahkan uses-permision dim AndroidManifest.xml untuk minta akses internetnya

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hukum_zm);
        WebView wv = findViewById(R.id.webView);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);


        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        wv.loadUrl("https://muslim.or.id/367-syarat-wajib-dan-cara-mengeluarkan-zakat-mal.html");
    }

}
