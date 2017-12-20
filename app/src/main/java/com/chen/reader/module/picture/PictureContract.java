package com.chen.reader.module.picture;

import com.chen.reader.base.BasePresenter;
import com.chen.reader.base.BaseView;

/**
 * Created by Administrator on 2017/12/19.
 */

public interface PictureContract {
    interface PictureView extends BaseView{
        void showProgress();
        void hideProgress();
    }

    interface Presenter extends BasePresenter{
        void savePicture(String url,int width,int height,String title);
    }
}
