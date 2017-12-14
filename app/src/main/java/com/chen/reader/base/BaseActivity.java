package com.chen.reader.base;

import android.os.Bundle;
import android.os.Process;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.chen.reader.R;
import com.chen.reader.utils.StatusBarUtil;

import butterknife.ButterKnife;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/12/14.
 */

public abstract class BaseActivity extends AppCompatActivity implements BGASwipeBackHelper.Delegate {

    private CompositeSubscription mCompositeSubscription;
    protected BGASwipeBackHelper mSwipeBackHelper;

    /**
     * 获取布局ID
     * @return  布局id
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化前期准备
     */
    protected void beforeInit() {

    }

    /**
     * 初始化布局和相关view控件
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public CompositeSubscription getmCompositeSubscription(){
        checkSubscription();
        return this.mCompositeSubscription;
    }


    /**
     * 检测mCompositeSubscription是否为空，以免导致空指针
     */
    private void checkSubscription(){
        if (this.mCompositeSubscription ==null){
            this.mCompositeSubscription = new CompositeSubscription();
        }
    }

    /**
     * 增加一个调度器
     * @param subscription
     */
    protected void addSubscription(Subscription subscription){
        checkSubscription();
        this.mCompositeSubscription.add(subscription);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 在 super.onCreate(savedInstanceState) 之前调用该方法
        initSwipeBackFinish();
        //        setStatusBarTransparent();
        super.onCreate(savedInstanceState);

        beforeInit();
        if (getContentViewLayoutID()!=0){
            setContentView(getContentViewLayoutID());
            initView(savedInstanceState);
        }
    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish(){
        mSwipeBackHelper  = new BGASwipeBackHelper(this,this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }


    /**
     * 是否支持滑动返回
     * @return
     */
    public boolean isSupportSwiprBack(){
        return true;
    }

    /**
     * 正在滑动返回
     * @param sideOffset
     */
    public void onSwipeBackLayoutSlide(float sideOffset){

    }

    /**
     * 没有达到滑动返回的阈值，取消滑动返回的动作，回到默认状态
     */
    public void onSwipeBackLayoutCancel(){

    }

    /**
     * 滑动返回完毕，销毁当前Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()){
            return;
        }
        mSwipeBackHelper.backward();
    }

    /**
     * 设置状态栏透明
     */
    public void setStatusBarTransparent(){
        StatusBarUtil.setTranslucent(this);
    }

    /**
     * 设置状态栏颜色
     * @param color
     */
    protected void setStatusBarColor(@ColorInt int color){
        setStatusBarColor(color,StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     * @param color
     * @param statusBarAlpha  状态栏 透明度
     */
    protected void setStatusBarColor(@ColorInt int color,@IntRange(from = 0,to =255 ) int statusBarAlpha){
        StatusBarUtil.setColorForSwipeBack(this,color,statusBarAlpha);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription !=null && !this.mCompositeSubscription.isUnsubscribed()){
            this.mCompositeSubscription.unsubscribe();
        }
    }


}




















