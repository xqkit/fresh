package com.journeyos.freshday.view.activity.content;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.journeyos.freshday.R;
import com.journeyos.freshday.base.BaseActivity;
import com.journeyos.freshday.constant.Constants;

public class GiftActivity extends BaseActivity {
    private WebView mWebView;

    @Override
    public void initData() {
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
        mWebView.loadUrl(Constants.GIFT);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_gift;
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gift);
//        mWebView = (WebView) findViewById(R.id.web_view);
//        //设置让WebView支持显示javascript语言
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        //设置支持缩放
//        mWebView.getSettings().setSupportZoom(true);
//        mWebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
//        mWebView.loadUrl("http://www.weixin.com");
//    }
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
