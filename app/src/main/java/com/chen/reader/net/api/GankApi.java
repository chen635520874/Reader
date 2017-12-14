package com.chen.reader.net.api;

import com.chen.reader.model.CategoryResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import static android.R.attr.category;

/**
 * Created by Administrator on 2017/12/13.
 */

public interface GankApi {

    /**
     * 获取数据
     * @param category 类别
     * @param count  数目
     * @param page 页数
     * @return
     */
    @GET("data/{category}/{count}/{page}")
    Observable<CategoryResult> getCategoryData(@Path("category")String category,@Path("count") int count,@Path("page") int page);
}
