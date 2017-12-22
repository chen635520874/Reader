package com.chen.reader.module.category;

import com.chen.reader.base.BasePresenter;
import com.chen.reader.base.BaseView;
import com.chen.reader.model.CategoryResult.ResultsBean;

import java.util.List;


/**
 * Created by Administrator on 2017/12/22.
 */

public interface CategoryContract {

    interface ICategoryView extends BaseView{
        void getCategoryItemFail(String failMessage);

        void setCategoryItems(List<ResultsBean> data);

        void addCategoryItems(List<ResultsBean> data);

        void showSwipeloading();

        void hideSwipeLoading();

        void setloading();

        String getCategoryName();

        void setNoMore();
    }

    interface ICategoryPresenter extends BasePresenter{

        void getCategoryItems(boolean isRefresh);
    }
}
