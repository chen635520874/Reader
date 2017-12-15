package com.chen.reader.module.web;


import android.app.Activity;
import android.content.Intent;

import com.chen.reader.module.web.WebContract.IWebPresenter;
import com.chen.reader.module.web.WebContract.IWebView;
/**
 * Created by Administrator on 2017/12/15.
 */

public class WebPresenter implements IWebPresenter {

    private IWebView mWebView;
    private String mGankUrl;
    private Activity mActivity;

    public WebPresenter(IWebView mWebView) {
        this.mWebView = mWebView;
    }

    @Override
    public void subscribe() {
        mActivity = mWebView.getWebViewContext();
        Intent intent = mActivity.getIntent();
        mWebView.setGankTitle(intent.getStringExtra(WebViewActivity.GANK_TITLE));
        mWebView.initWebView();
        mGankUrl = intent.getStringExtra(WebViewActivity.GANK_URL);
        mWebView.loadGankUrl(mGankUrl);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public String getGankUrl() {
        return this.mGankUrl;
    }
}
