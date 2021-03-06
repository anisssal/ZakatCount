package com.iman.inc.zakatcount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PanduanZakat extends AppCompatActivity {
    //Jangan lupa tambahkan uses-permision dim AndroidManifest.xml untuk minta akses internetnya
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_zakat);
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
        wv.loadUrl("https://muslim.or.id/9427-panduan-zakat-1-keutamaan-menunaikan-zakat.html");
    }
}
