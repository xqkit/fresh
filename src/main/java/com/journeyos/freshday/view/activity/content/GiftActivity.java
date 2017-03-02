package com.journeyos.freshday.view.activity.content;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.journeyos.freshday.R;

public class GiftActivity extends Activity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        mWebView = (WebView) findViewById(R.id.web_view);
        //设置让WebView支持显示javascript语言
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        mWebView.loadUrl("http://www.weixin.com");
    }
    @Override
    public void onBackPressed() {
        //查看是否有上一个可显示的网页
        if (mWebView.canGoBack()) {
            mWebView.goBack();  // 回到上一个网页
        } else {
            finish();
        }
    }
}
