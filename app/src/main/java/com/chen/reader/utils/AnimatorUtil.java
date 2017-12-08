package com.chen.reader.utils;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by Administrator on 2017/12/7.
 */

public class AnimatorUtil {

    /**
     * LinearOutSlowInInterpolator基于贝塞尔曲线插补器效果：快 慢  慢
     */
    public static final LinearOutSlowInInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR=new LinearOutSlowInInterpolator();


    /**
     * View显示的方法，是运用ViewPropertyAnimator;主要调用animate（）；
     * @param view
     * @param viewPropertyAnimatorListener
     */
    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener){
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                .alpha(1.0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(LINEAR_OUT_SLOW_IN_INTERPOLATOR)
                .start();
    }

    /**
     * 隐藏view
     * @param view
     * @param viewPropertyAnimatorListener
     */
    public static void scaleHide(View view,ViewPropertyAnimatorListener viewPropertyAnimatorListener){
        view.setVisibility(View.GONE);
        ViewCompat.animate(view)
                .alpha(0.0f)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setDuration(800)
                .setInterpolator(LINEAR_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start();
    }
}
