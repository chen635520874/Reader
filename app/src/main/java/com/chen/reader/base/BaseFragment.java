package com.chen.reader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/14.
 */

public abstract class BaseFragment extends Fragment{
    private Unbinder unbinder;

    /**
     * 获取布局Id
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     */
    protected abstract void init();

    /**
     * 创建视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getContentViewLayoutID()!=0){
            return inflater.inflate(getContentViewLayoutID(),container,false);
        }else{
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    /**
     * 试图创建完成
     * @param view
     * @param savadInstanceState
     */
    public void onViewCreated(View view,@Nullable Bundle savadInstanceState){
        super.onViewCreated(view,savadInstanceState);
        unbinder = ButterKnife.bind(this,view);
        init();
    }


    /**
     * 摧毁并解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
