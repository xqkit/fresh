package com.journeyos.freshday.view.activity.content;

import android.app.Activity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.journeyos.freshday.util.Network;

/**
 * Desc:
 * Date:    2017/3/3 09:43
 * Email:   frank.xiong@coolpad.com
 */

public abstract class BaseContentActivity extends Activity {

    protected WebView mWebView;

    /**
     * 初始化资源设置
     *
     * @param webView 传入的webview
     * @param url     跳转地址
     * @param emptyTv 若为空的判断处理
     */
    protected void initWebView(final WebView webView, final String url, final TextView emptyTv) {

        mWebView = webView;
        emptyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Network.getNetWorkType(getApplicationContext()) == Network.NetworkStateNo) {
                    //无网络则不显示webview
                    emptyTv.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.GONE);
                } else {
                    //有网络就显示webview
                    emptyTv.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    webView.loadUrl(url);
                }
            }
        });

        //设置让WebView支持显示javascript语言
        webView.getSettings().setJavaScriptEnabled(true);
        //设置支持缩放
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        if (Network.getNetWorkType(this) == Network.NetworkStateNo) {
            emptyTv.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        } else {
            webView.loadUrl(url);
        }
//        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //查看是否有上一个可显示的网页
        if (mWebView.canGoBack()) {
            mWebView.goBack();  // 回到上一个网页
        } else {
            finish();
        }
    }
}
