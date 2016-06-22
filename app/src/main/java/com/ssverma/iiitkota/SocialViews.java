package com.ssverma.iiitkota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class SocialViews extends AppCompatActivity {
    private WebView plugin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_views);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        Bundle bundle = getIntent().getExtras();
        String Url = bundle.getString("url");


        plugin=(WebView)findViewById(R.id.webView);
        WebSettings webSettings = plugin.getSettings();
        webSettings.setJavaScriptEnabled(true);

        plugin.loadUrl(Url);
        plugin.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });



    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (plugin.canGoBack()) {
                        plugin.goBack();
                    } else {
                        super.onBackPressed();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
