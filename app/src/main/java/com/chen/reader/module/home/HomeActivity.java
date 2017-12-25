package com.chen.reader.module.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chen.reader.GlideImageLoader;
import com.chen.reader.R;
import com.chen.reader.base.BaseActivity;
import com.chen.reader.base.adapter.CommonViewPagerAdapter;
import com.chen.reader.config.GlobalConfig;
import com.chen.reader.model.PictureModel;
import com.chen.reader.module.category.CategoryFragment;
import com.chen.reader.module.navabout.NavAboutActivity;
import com.chen.reader.module.picture.PictureActivity;
import com.chen.reader.module.web.WebViewActivity;
import com.chen.reader.utils.PerfectClickListener;
import com.chen.reader.utils.ScreenUtil;
import com.chen.reader.utils.StatusBarUtil;
import com.chen.reader.utils.ToastyUtil;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.chen.reader.module.home.HomeContract.IHomeView;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

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
        Toasty.error(this,failMessage).show();
    }

    @Override
    public void setBanner(List<String> imgUrls) {
        banner.setImages(imgUrls).setImageLoader(new GlideImageLoader()).start();
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
        initDrawerLayout();
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(this);

        String[] titles ={
                GlobalConfig.CATEGORY_NAME_APP,
                GlobalConfig.CATEGORY_NAME_ANDROID,
                GlobalConfig.CATEGORY_NAME_IOS,
                GlobalConfig.CATEGORY_NAME_FRONT_END,
                GlobalConfig.CATEGORY_NAME_RECOMMEND,
                GlobalConfig.CATEGORY_NAME_RESOURCE };
        CommonViewPagerAdapter infoPagerAdapter = new CommonViewPagerAdapter(getSupportFragmentManager(),titles);

        //app
        CategoryFragment appFragment = CategoryFragment.newInstance(titles[0]);
        //android
        CategoryFragment androidFragment = CategoryFragment.newInstance(titles[1]);
        //ios
        CategoryFragment iosFragment = CategoryFragment.newInstance(titles[2]);
        //前端
        CategoryFragment frontFragment = CategoryFragment.newInstance(titles[3]);
        //推荐
        CategoryFragment referenceFragment = CategoryFragment.newInstance(titles[4]);
        //资源
        CategoryFragment resourseFragment = CategoryFragment.newInstance(titles[5]);

        infoPagerAdapter.addFragment(appFragment);
        infoPagerAdapter.addFragment(androidFragment);
        infoPagerAdapter.addFragment(iosFragment);
        infoPagerAdapter.addFragment(frontFragment);
        infoPagerAdapter.addFragment(referenceFragment);
        infoPagerAdapter.addFragment(resourseFragment);

        viewPager.setAdapter(infoPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);//设置默认显示的Fragment
        viewPager.setOffscreenPageLimit(6);//预加载的Fragment数

        homePresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homePresenter !=null){
            homePresenter.unSubscribe();
        }
    }

    public boolean isSupportSwipeBack(){
        return false;
    }

    @Override
    public void OnBannerClick(int position) {
        PictureModel pictureModel = homePresenter.getBannerModel().get(position);
//        Intent intent = PictureActivity.newIntent(this,model.url,model.desc);
//        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                this,mBanner,PictureActivity.TRANSIT_PIC);
//        ActivityCompat.startActivity(this,intent,optionsCompat.toBundle());
        PictureActivity.start(this,pictureModel.url,pictureModel.desc,banner);
    }

    private void initDrawerLayout(){
        navigationView.inflateHeaderView(R.layout.layout_main_nav);
        View headerView = navigationView.getHeaderView(0);
        headerView.findViewById(R.id.ll_nav_homepage).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_about).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_more).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_exit).setOnClickListener(mListener);
        headerView.findViewById(R.id.ll_nav_login).setOnClickListener(mListener);
    }

    private PerfectClickListener mListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(final View v) {
            drawerLayout.closeDrawer(GravityCompat.START);
            drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()){
                        case R.id.ll_nav_homepage:
                            startActivity(new Intent(HomeActivity.this, NavAboutActivity.class));
                            break;
                        case R.id.ll_nav_about:
                            startActivity(new Intent(HomeActivity.this,NavAboutActivity.class));
                            break;
                        case R.id.ll_nav_more:
                            ToastyUtil.showSuccess("更多");
                            break;
                        case R.id.ll_nav_login:
                            Intent intent_login = new Intent(HomeActivity.this,WebViewActivity.class);
                            intent_login.putExtra(WebViewActivity.GANK_TITLE,"GitHub");
                            intent_login.putExtra(WebViewActivity.GANK_URL,"https://github.com/login");
                            startActivity(intent_login);
                            break;
                        case R.id.ll_nav_exit:
                            finish();
                            break;
                        default:
                            break;
                    }
                }
            },300);
        }
    };

    public void onBackPressed(){
        if ((System.currentTimeMillis()-mExitTime)>2000){
            Snackbar.make(drawerLayout,R.string.exitToast,Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        }else {
            finish();
        }
    }


}




























