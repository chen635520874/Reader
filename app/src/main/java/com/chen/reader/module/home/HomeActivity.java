package com.chen.reader.module.home;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chen.reader.R;
import com.chen.reader.base.BaseActivity;
import com.chen.reader.base.adapter.CommonViewPagerAdapter;
import com.chen.reader.config.GlobalConfig;
import com.chen.reader.module.category.CategoryFragment;
import com.chen.reader.utils.ScreenUtil;
import com.chen.reader.utils.StatusBarUtil;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.chen.reader.module.home.HomeContract.IHomeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/21.
 */

public class HomeActivity extends BaseActivity implements IHomeView,OnBannerListener {

    @BindView(R.id.mainActivity)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.main_head_img)
    ImageView mHeadImg;
    @BindView(R.id.main_banner)
    Banner banner;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_tab)
    DachshundTabLayout tabLayout;
    @BindView(R.id.main_vp)
    ViewPager viewPager;
    @BindView(R.id.main_fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private long mExitTime =0;
    private HomePresenter homePresenter;

    @Override
    public void showBannerFail(String failMessage) {

    }

    @Override
    public void setBanner(List<String> imgUrls) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void beforeInit() {
        super.beforeInit();
        StatusBarUtil.setTranslucent(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        homePresenter = new HomePresenter(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 4.4 以上版本
            // 设置 Toolbar 高度为 80dp，适配状态栏
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = ScreenUtil.dip2px(this,80);
            toolbar.setLayoutParams(layoutParams);
        }
        initDrawLayout();
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(this);

        String[] titles ={
                GlobalConfig.CATEGORY_NAME_APP,
                GlobalConfig.CATEGORY_NAME_ANDROID,
                GlobalConfig.CATEGORY_NAME_IOS,
                GlobalConfig.CATEGORY_NAME_FRONT_END,
                GlobalConfig.CATEGORY_NAME_RECOMMEND,
                GlobalConfig.CATEGORY_NAME_RESOURCE };
        CommonViewPagerAdapter appFragment = CategoryFragment.CATEGORY_NAME

    }

    @Override
    public void OnBannerClick(int position) {

    }

    public void initDrawLayout(){

    }
}




























