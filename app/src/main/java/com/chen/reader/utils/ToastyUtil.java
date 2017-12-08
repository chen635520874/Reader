package com.chen.reader.utils;

import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by Administrator on 2017/12/7.
 */

public class ToastyUtil {

    /**
     * 错误Toast
     * @param msg
     */
    public static void showError(String msg){
        Toasty.error(Utils.getContext(),msg,Toast.LENGTH_SHORT,true).show();
    }

    /**
     * 成功Toast
     * @param msg
     */
    public static void showSuccess(String msg){
        Toasty.success(Utils.getContext(),msg,Toast.LENGTH_SHORT,true).show();
    }
}
