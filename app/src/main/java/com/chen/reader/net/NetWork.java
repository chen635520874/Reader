package com.chen.reader.net;

import com.chen.reader.net.api.GankApi;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/12/13.
 */

public class NetWork {
    private static GankApi gankApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static GankApi getGankApi(){
        if (gankApi ==null){
            Retrofit retrofit  = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }
}
