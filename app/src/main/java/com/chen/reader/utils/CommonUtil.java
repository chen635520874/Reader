package com.chen.reader.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

import com.chen.reader.App;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 获取资源的工具类
 * Created by Administrator on 2017/12/8.
 */

public class CommonUtil {

    /**
     * 随机颜色
     * @return
     */
    public static int randomColor(){
        Random random= new Random();
        int red = random.nextInt(150)+50;//50-199
        int green = random.nextInt(150)+50;
        int blue = random.nextInt(150)+50;
        return Color.rgb(red,green,blue);
    }

    /**
     * 得到年月日的“日”
     */
    private String getData(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        return dateFormat.format(date);
    }

    /**
     * 获取屏幕px；获取分辨率
     * @param context
     * @return
     */
    public static int getScreenWidthPixels(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


    public static Resources getResoure(){
        return App.getINSTANCE().getResources();
    }

    public static Drawable getDrawable(int resid){
        return getResoure().getDrawable(resid);
    }

    public static int getColor(int resid){
        return getResoure().getColor(resid);
    }

    public static String[] getStringArray(int resid){
        return getResoure().getStringArray(resid);
    }

    public static String getString(int resid){
        return getResoure().getString(resid);
    }

    public static float getDimens(int resid){
        return getResoure().getDimension(resid);
    }

    /**
     * 从视图中删除
     * @param child
     */
    public static void removeSelfFromParent(View child){
        if (child!=null){
            ViewParent parent = child.getParent();
            if (parent instanceof ViewGroup){
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);
            }
        }
    }

}
