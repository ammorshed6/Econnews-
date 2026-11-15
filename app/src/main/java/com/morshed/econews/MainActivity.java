package com.morshed.econews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.parseColor("#0B5D3B"));
        web = new WebView(this);
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowFileAccessFromFileURLs(true);
        s.setAllowUniversalAccessFromFileURLs(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String u = request.getUrl().toString();
                if (u.startsWith("file:")) {
                    return false;
                }
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(u)));
                } catch (Exception e) {
                    // no app can open this link
                }
                return true;
            }
        });
        web.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
