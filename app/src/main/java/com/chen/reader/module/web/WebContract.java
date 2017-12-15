package com.chen.reader.module.web;

import android.app.Activity;

import com.chen.reader.base.BasePresenter;
import com.chen.reader.base.BaseView;

/**
 * Created by Administrator on 2017/12/15.
 */

public interface WebContract {

    interface IWebView extends BaseView{
        Activity getWebViewContext();

        void setGankTitle(String title);

        void loadGankUrl(String url);

        void initWebView();
    }

    interface IWebPresenter extends BasePresenter{
        String getGankUrl();
    }
}
