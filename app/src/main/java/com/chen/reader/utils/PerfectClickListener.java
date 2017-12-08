package com.chen.reader.utils;

import android.icu.util.Calendar;
import android.view.View;

/**
 *判断用户的点击属不属于双击。
 * 1，一定时间内点的是同个ID；
 * 2，两次点击的时间间隔小于最小延迟时间；
 * Created by Administrator on 2017/12/8.
 */

public abstract class PerfectClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int id = -1;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        int mId = v.getId();
        if (id!=mId){
            id = mId;
            lastClickTime = currentTime;
            onNoDoubleClick(v);
            return;
        }
        if (currentTime-lastClickTime>MIN_CLICK_DELAY_TIME){
            lastClickTime=currentTime;
            onNoDoubleClick(v);
        }
    }
    protected abstract void onNoDoubleClick(View v);
}
