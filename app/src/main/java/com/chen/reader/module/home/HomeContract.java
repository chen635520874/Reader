package com.chen.reader.module.home;

import com.chen.reader.base.BasePresenter;
import com.chen.reader.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public interface HomeContract {
    interface IHomeView extends BaseView{
        void showBannerFail(String failMessage);

        void setBanner(List<String> imgUrls);
    }

    interface IHomePresenter  extends BasePresenter{
        /**
         * 获取Banner轮播图数据
         */
        void getBannerData();
    }
}
