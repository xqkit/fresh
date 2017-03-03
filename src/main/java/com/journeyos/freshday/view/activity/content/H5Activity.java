package com.journeyos.freshday.view.activity.content;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.journeyos.freshday.R;
import com.journeyos.freshday.base.BaseActivity;
import com.journeyos.freshday.constant.Constants;
import com.journeyos.freshday.util.Network;

public class H5Activity extends BaseContentActivity {
    private WebView mWebView;
    private TextView mEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        mWebView = (WebView) findViewById(R.id.web_view);
        mEmpty = (TextView) findViewById(R.id.empty);
        initWebView(mWebView, Constants.H5GAME, mEmpty);
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
//        if (Network.getNetWorkType(this) == Network.NetworkStateNo) {
//            mEmpty.setVisibility(View.VISIBLE);
//            mWebView.setVisibility(View.GONE);
//        } else {
//            mWebView.loadUrl("http://www.weixin.com");
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
//    @Override
//    public void initData() {
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
//        if (Network.getNetWorkType(this) == Network.NetworkStateNo) {
//            mEmpty.setVisibility(View.VISIBLE);
//            mWebView.setVisibility(View.GONE);
//        } else {
//            mWebView.loadUrl("http://www.weixin.com");
//        }
//    }
//
//    //    private void initView() {
//    @Override
//    public void initView() {
//        mWebView = (WebView) findViewById(R.id.web_view);
//        mEmpty = (TextView) findViewById(R.id.empty);
//        mEmpty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Network.getNetWorkType(H5Activity.this) == Network.NetworkStateNo) {
//                    mEmpty.setVisibility(View.VISIBLE);
//                    mWebView.setVisibility(View.GONE);
//                } else {
//                    mEmpty.setVisibility(View.GONE);
//                    mWebView.setVisibility(View.VISIBLE);
//                    mWebView.loadUrl("http://www.weixin.com");
//                }
//            }
//        });
//    }
//
//    @Override
//    protected int getContentViewId() {
//        return R.layout.activity_live;
//    }
}
