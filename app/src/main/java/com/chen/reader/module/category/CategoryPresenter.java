package com.chen.reader.module.category;


import com.chen.reader.config.GlobalConfig;
import com.chen.reader.model.CategoryResult;
import com.chen.reader.module.category.CategoryContract.ICategoryPresenter;
import com.chen.reader.module.category.CategoryContract.ICategoryView;
import com.chen.reader.net.NetWork;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/22.
 */

public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryView categoryView;
    private int page =1;
    private Subscription subscription;

    public CategoryPresenter(ICategoryView iCategoryView){
        this.categoryView = iCategoryView;
    }

    @Override
    public void subscribe() {
        getCategoryItems(true);
    }

    @Override
    public void unSubscribe() {
        if (subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void getCategoryItems(final boolean isRefresh) {
        if (isRefresh){
            page = 1;
            categoryView.showSwipeloading();
        }else {
            page++;
        }
        subscription = NetWork.getGankApi()
                .getCategoryData(categoryView.getCategoryName(), GlobalConfig.CATEGORY_COUNT,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        categoryView.hideSwipeLoading();
                        categoryView.getCategoryItemFail(categoryView.getCategoryName()+"列表数据获取失败");
                    }

                    @Override
                    public void onNext(CategoryResult categoryResult) {
                        if (categoryResult!=null && !categoryResult.error){
                            if (categoryResult.result == null && categoryResult.result.size()==0){
                               categoryView.getCategoryItemFail("获取数据为空");
                            }else {
                                if (isRefresh){
                                    categoryView.setCategoryItems(categoryResult.result);
                                    categoryView.hideSwipeLoading();
                                    categoryView.setloading();
                                }else {
                                    categoryView.addCategoryItems(categoryResult.result);
                                }
                                if (categoryResult.result.size()<GlobalConfig.CATEGORY_COUNT){
                                    categoryView.setNoMore();
                                }
                            }
                        }else {
                            categoryView.getCategoryItemFail("获取数据失败");
                        }
                    }
                });
    }
}
