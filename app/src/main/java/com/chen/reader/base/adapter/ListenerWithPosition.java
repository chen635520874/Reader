package com.chen.reader.base.adapter;

import android.view.View;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/12/14.
 */

public class ListenerWithPosition implements View.OnClickListener {

    private int mPosition;
    private Object mHolder;
    private OnClickWithPositionListener mOnClickListener;


    public ListenerWithPosition(int position,Object holder){
        this.mPosition = position;
        this.mHolder =holder;
    }
    @Override
    public void onClick(View v) {
        if (mOnClickListener != null){
            mOnClickListener.onClick(v,mPosition,mHolder);
        }
    }

    public interface OnClickWithPositionListener<T>{
        void onClick(View v,int position,T holder);
    }

    public void setOnClickListener(OnClickWithPositionListener mOnClickListener){
        this.mOnClickListener = mOnClickListener;
    }
}
