package com.chen.reader;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Created by Administrator on 2017/12/5.
 */

public class App extends Application {

    private static App INSTANCE;
    private App(){}

    private Set<Activity> mActivitys;

    public static App getINSTANCE(){
        if (INSTANCE == null){
            synchronized (App.class){
                if (INSTANCE == null){
                    INSTANCE = new App();
                    //INSTANCE.mActivitys = new Set();

                    }
            }
        }
        return INSTANCE;
    }

    public void onCreatr(){
        super.onCreate();

        INSTANCE = this;

        /**
         * 内存泄漏检测工具leakCanary
         * This process is dedicated to LeakCanary for heap analysis.
           You should not init your app in this process.
         */
        if (LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);

        BGASwipeBackManager.getInstance().init(this);
        ConfigManage.INSTSNCE.initConfig(this);
    }

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if (mActivitys ==null){
            mActivitys = new HashSet<>();
        }
        mActivitys.add(activity);
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if (activity!=null){
            mActivitys.remove(activity);
        }
    }

    public void exitApp(){
        if (mActivitys!=null){
            synchronized (mActivitys){
                for (Activity activity:mActivitys){
                        activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}



























