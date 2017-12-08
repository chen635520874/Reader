package com.chen.reader.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 包相关工具类
 * Created by Administrator on 2017/12/8.
 */

public class PackageUtil {


    /**
     * 获取版本号
     * @return
     */
    public static String getVersionName(){
        //获取PackgeManager实例 ；
        PackageManager packageManager = Utils.getContext().getPackageManager();

        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packageInfo = null;
        try {
            packageInfo  =packageManager.getPackageInfo(Utils.getContext().getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }
}
