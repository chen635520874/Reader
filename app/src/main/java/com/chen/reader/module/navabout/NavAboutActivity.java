package com.chen.reader.module.navabout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chen.reader.R;
import com.chen.reader.base.BaseActivity;
import com.chen.reader.module.web.WebViewActivity;
import com.chen.reader.utils.PackageUtil;
import com.chen.reader.utils.ToastyUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class NavAboutActivity extends BaseActivity {

    @BindView(R.id.nav_about_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_version_name)
    TextView mTvVersionName;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_nav_about;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvVersionName.setText(R.string.nowVersion+"V"+ PackageUtil.getVersionName());
    }

    @OnClick({R.id.tv_new_version ,R.id.my_blog,R.id.my_github})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_new_version:
                ToastyUtil.showError("已经是最新版本");
                break;
            case R.id.my_blog:
                Intent intent = new Intent(this, WebViewActivity.class);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_about);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
}
