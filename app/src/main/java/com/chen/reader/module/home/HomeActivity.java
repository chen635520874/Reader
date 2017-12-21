package com.chen.reader.module.home;

import android.os.Bundle;

import com.chen.reader.base.BaseActivity;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class HomeActivity extends BaseActivity implements HomeContract.IHomeView,OnBannerListener {
    @Override
    public void showBannerFail(String failMessage) {

    }

    @Override
    public void setBanner(List<String> imgUrls) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void OnBannerClick(int position) {

    }
}
