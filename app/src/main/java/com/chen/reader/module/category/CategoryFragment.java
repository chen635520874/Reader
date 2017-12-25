package com.chen.reader.module.category;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;

import com.chen.reader.R;
import com.chen.reader.base.BaseFragment;
import com.chen.reader.model.CategoryResult;
import com.chen.reader.module.category.CategoryContract.ICategoryView;
import com.chen.reader.widget.RecyclerViewDivider;
import com.chen.reader.widget.RecyclerViewWithFooter.OnLoadMoreListener;
import com.chen.reader.widget.RecyclerViewWithFooter.RecyclerViewWithFooter;

import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

import com.chen.reader.module.category.CategoryRecyclerAdapter;
import com.chen.reader.module.category.CategoryContract.ICategoryPresenter;

/**
 * Created by Administrator on 2017/12/22.
 */

public class CategoryFragment extends BaseFragment implements ICategoryView,OnRefreshListener,OnLoadMoreListener{

    public final static String CATEGORY_NAME= "com.chen.reader.module.category.CategoryFragment.CATEGORY_NAME";
    @BindView(R.id.recyclerView)
    RecyclerViewWithFooter recyclerViewWithFooter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private String categoryName;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private ICategoryPresenter iCategoryPresenter;

    public static CategoryFragment newInstance(String categoryName){
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_NAME,categoryName);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }


    @Override
    public void onRefresh() {
        iCategoryPresenter.getCategoryItems(true);
    }

    @Override
    public void onLoadMore() {
        iCategoryPresenter.getCategoryItems(false);
    }

    @Override
    public void getCategoryItemFail(String failMessage) {
        if (getUserVisibleHint()){
            Toasty.error(this.getContext(),failMessage).show();
        }
    }

    @Override
    public void setCategoryItems(List<CategoryResult.ResultsBean> data) {
        categoryRecyclerAdapter.setData(data);
    }

    @Override
    public void addCategoryItems(List<CategoryResult.ResultsBean> data) {
        categoryRecyclerAdapter.addData(data);
    }

    @Override
    public void showSwipeloading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setloading() {
        recyclerViewWithFooter.setLoading();
    }

    @Override
    public String getCategoryName() {
        return this.categoryName;
    }

    @Override
    public void setNoMore() {
        recyclerViewWithFooter.setEnd("没有更多数据");
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_category;
    }

    @Override
    protected void init() {
        iCategoryPresenter = new CategoryPresenter(this);
        categoryName = getArguments().getString(CATEGORY_NAME);
        swipeRefreshLayout.setOnRefreshListener(this);
        categoryRecyclerAdapter = new CategoryRecyclerAdapter(getActivity());

        recyclerViewWithFooter.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewWithFooter.addItemDecoration(new RecyclerViewDivider(getActivity(),LinearLayoutManager.HORIZONTAL));
        recyclerViewWithFooter.setAdapter(categoryRecyclerAdapter);
        recyclerViewWithFooter.setOnLoadMoreListener(this);
        recyclerViewWithFooter.setEmpty();

        iCategoryPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iCategoryPresenter !=null){
            iCategoryPresenter.unSubscribe();
        }
    }


}
