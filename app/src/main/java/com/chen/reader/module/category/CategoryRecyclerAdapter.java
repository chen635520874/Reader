package com.chen.reader.module.category;

import android.content.Context;
import android.view.View;

import com.chen.reader.R;
import com.chen.reader.base.adapter.CommonRecyclerAdapter;
import com.chen.reader.base.adapter.CommonRecyclerHolder;
import com.chen.reader.base.adapter.CommonViewPagerAdapter;
import com.chen.reader.base.adapter.ListenerWithPosition;
import com.chen.reader.model.CategoryResult;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class CategoryRecyclerAdapter extends CommonRecyclerAdapter<CategoryResult.ResultsBean>
        implements ListenerWithPosition.OnClickWithPositionListener<CommonRecyclerHolder> {

    public CategoryRecyclerAdapter(Context context) {
        super(context, null, R.layout.item_category);
    }

    @Override
    public void onClick(View v, int position, CommonRecyclerHolder holder) {

    }

    @Override
    public void convert(CommonRecyclerHolder holder, CategoryResult.ResultsBean resultsBean) {
        if (resultsBean !=null){

        }
    }
}
