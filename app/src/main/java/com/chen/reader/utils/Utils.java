package com.chen.reader.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/12/6.
 */

public class Utils {
    private  static Context context;

    private Utils(){
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     * @param context
     */
    public static void init(Context context){
        Utils.context = context.getApplicationContext();
    }
}
