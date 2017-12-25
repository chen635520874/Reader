package com.chen.reader.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/14.
 * <T>表示泛型  数据决定采用泛型，布局打算直接构造传递，绑定显示效果回传。
 *
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter{

    protected Context mContext;
    //数据怎么办？利用泛型
    protected List<T> mData;
    // 布局怎么办？直接从构造里面传递
    private int layoutId;
    private View mView;

    public void addData(List<T> data){
        if (data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void setData(List<T> data){
        mData.clear();
        addData(data);
    }

    public CommonRecyclerAdapter(Context context,List<T> data,int layoutId){
        this.mContext =context;
        this.mData = data ==null?(List<T>) new ArrayList<>():data;
        this.layoutId =layoutId;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(layoutId,parent,false);
        return new CommonRecyclerHolder(mView);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder,int position){
        if (holder instanceof CommonRecyclerHolder){
            CommonRecyclerHolder commonRecyclerHolder = (CommonRecyclerHolder) holder;
            commonRecyclerHolder.position= position;
            convert(commonRecyclerHolder,mData.get(position));
        }
    }

    public int getItemCount(){
        return (mData != null)? mData.size():0;
    }

    public abstract void convert(CommonRecyclerHolder holder,T t);


}
