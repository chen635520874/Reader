package com.chen.reader.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

    /**
     * 获取ApplicationContext
     * @return
     */
    public static Context getContext(){
        if (context!=null){
            return context;
        }
        throw  new NullPointerException("u should init first");
    }

    /**
     * Intent.ACTION_VIEW显示用户数据。会根据用户的数据类型打开相应的Activity
     * 使用浏览器打开链接
     * @param context
     * @param content
     */
    public static void openLink(Context context,String content){
        Uri issuesUrl = Uri.parse(content);
        Intent intent = new Intent(Intent.ACTION_VIEW,issuesUrl);
        context.startActivity(intent);
    }
}
