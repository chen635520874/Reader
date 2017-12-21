package com.chen.reader.module.picture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.chen.reader.R;
import com.chen.reader.base.BaseActivity;
import com.chen.reader.utils.Utils;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.OnClick;

public class PictureActivity extends BaseActivity implements PictureContract.PictureView {

    public static final String EXTRA_IMAGE_URL = "com.chen.reader.module.picture.PictureActivity.EXTRA_IMAGE_URI";
    public static final String EXTRA_IMAGE_TITLE = "com.chen.reader.module.picture.PictureActivity.EXTRA_IMAGE_TITLE";
    public static final String TRANSIT_PIC = "picture";

    @BindView(R.id.picture_app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.picture_btn_save)
    ImageButton btnSave;
    @BindView(R.id.picture_toolbar)
    Toolbar toolbar;
    @BindView(R.id.picture_img)
    ImageView imageView;
    @BindView(R.id.picture_progress)
    ProgressBar progressBar;

    private PictureContract.Presenter  presenter;
    String mImageUrl,mImageTitle;

    public static Intent newIntent(Context context,String url,String desc){
        Intent intent= new Intent(context,PictureActivity.class);
        intent.putExtra(EXTRA_IMAGE_URL,url);
        intent.putExtra(EXTRA_IMAGE_TITLE,desc);
        return intent;
    }

    public static void start(Activity activity, String url, String desc, Banner banner){
        Intent intent = new Intent(activity,PictureActivity.class);
        intent.putExtra(EXTRA_IMAGE_URL,url);
        intent.putExtra(EXTRA_IMAGE_TITLE,desc);
        ActivityOptionsCompat activityOptionsCompat =  ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,banner,TRANSIT_PIC
        );
        ActivityCompat.startActivity(activity,intent,activityOptionsCompat.toBundle());
    }

        public void parseIntent(){
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_picture;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        showProgress();
        presenter = new PicturePresenter(this);
        parseIntent();
        ViewCompat.setTransitionName(imageView,TRANSIT_PIC);

        toolbar.setTitle(TextUtils.isEmpty(mImageTitle)?"图片预览":mImageTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Glide.with(Utils.getContext())
                .load(mImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        hideProgress();
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    /**
     * 点击了保存的按钮就调用savePicture()方法
     */
    @OnClick(R.id.picture_btn_save)
    public void onSaveClick(){
        if (presenter!=null){
            presenter.savePicture(mImageUrl,imageView.getWidth(),imageView.getHeight(),mImageTitle);
        }
    }

    @OnClick(R.id.picture_img)
    public void onPictureClick(){
        if (getSupportActionBar()!=null){
            if (getSupportActionBar().isShowing()){
                getSupportActionBar().hide();
            }else {
                getSupportActionBar().show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
