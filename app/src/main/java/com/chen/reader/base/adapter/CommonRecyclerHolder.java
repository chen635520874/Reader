package com.chen.reader.base.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.chen.reader.R;

/**
 *  通用的RecyclerView的Holder
 * Created by Administrator on 2017/12/13.
 */

public class CommonRecyclerHolder extends RecyclerView.ViewHolder {

    public View mConvertView;
    public int position;
    private SparseArray<View> mViews;

    public CommonRecyclerHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }

    public <T extends View> T getView(@IdRes int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public CommonRecyclerHolder setTextViewText(@IdRes int textViewId,String text){
        TextView textView = getView(textViewId);
        if (!TextUtils.isEmpty(text)){
            textView.setText(text);
        }else {
            textView.setText(" ");
        }
        return this;
    }

    public CommonRecyclerHolder setOnClickListener(ListenerWithPosition.OnClickWithPositionListener clickListener,@IdRes int... viewIds){
        ListenerWithPosition listenerWithPosition = new ListenerWithPosition(position,this);
        listenerWithPosition.setOnClickListener(clickListener);
        for (int id:viewIds){
            View view = getView(id);
            view.setOnClickListener(listenerWithPosition);
        }
        return this;
    }

}
