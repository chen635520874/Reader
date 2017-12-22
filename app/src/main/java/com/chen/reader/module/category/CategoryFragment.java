package com.chen.reader.module.category;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;

import com.chen.reader.R;
import com.chen.reader.base.BaseFragment;
import com.chen.reader.model.CategoryResult;
import com.chen.reader.module.category.CategoryContract.ICategoryView;
import com.chen.reader.widget.RecyclerViewWithFooter.OnLoadMoreListener;
import com.chen.reader.widget.RecyclerViewWithFooter.RecyclerViewWithFooter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/22.
 */

public class CategoryFragment extends BaseFragment implements ICategoryView,OnRefreshListener,OnLoadMoreListener{

    public final static String CATEGORY_NAME= "com.chen.reader.module.category.CategoryFragment.CATEGORY_NAME";
    @BindView(R.id.recyclerView)
    RecyclerViewWithFooter recyclerViewWithFooter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onRefresh() {

    }

    @Override
    public void OnLoadMore() {

    }

    @Override
    public void getCategoryItemFail(String failMessage) {

    }

    @Override
    public void setCategoryItems(List<CategoryResult.ResultsBean> data) {

    }

    @Override
    public void addCategoryItems(List<CategoryResult.ResultsBean> data) {

    }

    @Override
    public void showSwipeloading() {

    }

    @Override
    public void hideSwipeLoading() {

    }

    @Override
    public void setloading() {

    }

    @Override
    public String getCategoryName() {
        return null;
    }

    @Override
    public void setNoMore() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return 0;
    }

    @Override
    protected void init() {

    }
}
