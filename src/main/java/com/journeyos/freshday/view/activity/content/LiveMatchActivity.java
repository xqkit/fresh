package com.journeyos.freshday.view.activity.content;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.journeyos.freshday.constant.Constants;
import com.journeyos.freshday.util.Network;
import com.journeyos.freshday.R;

public class LiveMatchActivity extends BaseContentActivity {
    private WebView mWebView;
    private TextView mEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mWebView = (WebView) findViewById(R.id.web_view);
        mEmpty = (TextView) findViewById(R.id.empty);
        initWebView(mWebView, Constants.LIVEMATCH, mEmpty);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_live);
//        initView();
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
//        if(Network.getNetWorkType(this)==Network.NetworkStateNo){
//            mEmpty.setVisibility(View.VISIBLE);
//            mWebView.setVisibility(View.GONE);
//        }else {
//            mWebView.loadUrl("http://www.baidu.com");
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        //查看是否有上一个可显示的网页
//        if (mWebView.canGoBack()) {
//            mWebView.goBack();  // 回到上一个网页
//        } else {
//            finish();
//        }
//    }
//
//    private void initView() {
//        mWebView = (WebView) findViewById(R.id.web_view);
//        mEmpty = (TextView) findViewById(R.id.empty);
//        mEmpty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(Network.getNetWorkType(LiveMatchActivity.this)==Network.NetworkStateNo){
//                    mEmpty.setVisibility(View.VISIBLE);
//                    mWebView.setVisibility(View.GONE);
//                }else {
//                    mEmpty.setVisibility(View.GONE);
//                    mWebView.setVisibility(View.VISIBLE);
//                    mWebView.loadUrl("http://www.baidu.com");
//                }
//            }
//        });
//    }
}
