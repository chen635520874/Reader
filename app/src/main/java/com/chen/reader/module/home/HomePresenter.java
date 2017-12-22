package com.chen.reader.module.home;

import android.graphics.Picture;

import com.chen.reader.model.CategoryResult;
import com.chen.reader.model.PictureModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.chen.reader.model.CategoryResult.ResultsBean;
import com.chen.reader.module.home.HomeContract.IHomePresenter;
import com.chen.reader.module.home.HomeContract.IHomeView;
import com.chen.reader.net.NetWork;
import com.youth.banner.Banner;

/**
 * Created by Administrator on 2017/12/21.
 */

public class HomePresenter implements IHomePresenter {

    private Subscription subscription;
    private IHomeView homeView;
    private List<PictureModel> models;

    HomePresenter(IHomeView homeView){
        this.homeView=homeView;
        models = new ArrayList<>();
    }
    @Override
    public void subscribe() {
        getBannerData();
    }

    public List<PictureModel> getBannerModel(){
        return this.models;
    }

    @Override
    public void unSubscribe() {
        if (subscription !=null&& !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void getBannerData() {
        subscription = NetWork.getGankApi()
                .getCategoryData("福利",5,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        homeView.showBannerFail("Banner加载失败");
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult!=null && categoryResult.result != null && categoryResult.result.size()>0){
                            List<String> imgUrls = new ArrayList<>();
                            for (ResultsBean resultsBean : categoryResult.result){
                                if (!resultsBean.url.isEmpty()){
                                    imgUrls.add(resultsBean.url);
                                }
                                PictureModel model = new PictureModel();
                                model.desc = resultsBean.desc;
                                model.url = resultsBean.url;
                                models.add(model);
                            }
                            homeView.setBanner(imgUrls);
                        }else {
                            homeView.showBannerFail("Banner加载失败");
                        }
                    }
                });
    }
}
