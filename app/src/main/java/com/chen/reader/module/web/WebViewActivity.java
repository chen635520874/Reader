package com.chen.reader.module.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chen.reader.module.web.WebContract.IWebPresenter;
import com.chen.reader.module.web.WebContract.IWebView;

import com.chen.reader.R;
import com.chen.reader.base.BaseActivity;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity implements IWebView {

    public static final String GANK_URL = "com.chen.reader.module.web.WebViewActivity.gank_url";
    public static final String GANK_TITLE = "com.chen.reader.module.web.WebViewActivity.gank_title";

    @BindView(R.id.web_title)
    TextView mWebTitle;
    @BindView(R.id.web_toolbar)
    Toolbar mWebToolbar;
    @BindView(R.id.web_progressBar)
    ProgressBar mWebProgressBar;
    @BindView(R.id.web_appbar)
    AppBarLayout mWebAppbar;
    @BindView(R.id.web_view)
    WebView mWebView;

    private IWebPresenter mWebPresenter;

    public static void start(Context context,String url,String title){
        Intent intent = new Intent(context,WebViewActivity.class);
        intent.putExtra(WebViewActivity.GANK_TITLE,title);
        intent.putExtra(WebViewActivity.GANK_URL,url);
        context.startActivity(intent);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mWebPresenter = new WebPresenter(this);
        /**
         * 点击返回按钮结束Activity
         */
        mWebToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebPresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebPresenter.unSubscribe();
    }

   /* @Override
    public boolean isSupportSwipeBack() {
        return false;
    }*/

    @Override
    public Activity getWebViewContext() {
        return this;
    }

    @Override
    public void setGankTitle(String title) {
        mWebTitle.setText(title);
    }

    @Override
    public void loadGankUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);

        mWebView.setWebChromeClient(new MyWebChrome());
        mWebView.setWebViewClient(new MyWebClient());
    }

    /**
     * setWebChromeClient主要处理解析，渲染网页等浏览器做的事情
     * WebChromeClient主要的作用是辅助WebView处理JS网站的对话框，图标，标题，加载进度条等
     */
    private class MyWebChrome extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mWebProgressBar.setVisibility(View.VISIBLE);
            mWebProgressBar.setProgress(newProgress);
        }
    }

    /**
     * WebViewClient就是帮助WebView处理各种通知、请求事件的
     *当网页结束就把进度条设置为不可见
     */
    private class MyWebClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            mWebProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * 用户点击返回按钮如果能够返回就返回，否则结束WebViewActivity
     */
    public void onBackPressed(){
        if (mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            finish();
        }
    }
}
















