package com.chen.reader;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/12/5.
 */

public enum  ConfigManage {

    INSTSNCE;

    private final String spName= "app_config";
    private final String key_isListShowImg= "isListShowImg";//是否显示图片
    private final String key_thumbnailQuality = "thumbnailQuality";//缩略图质量

    private boolean isListShowImg;
    private int thumbnailQuality;

    public void initConfig(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(spName,context.MODE_PRIVATE);
        //是否显示缩略图
        isListShowImg = sharedPreferences.getBoolean(key_isListShowImg,true);
        //缩略图质量 0：原图 ，1：默认， 2：省流
        thumbnailQuality = sharedPreferences.getInt(key_thumbnailQuality,1);
    }

    public boolean isListShowImg(){
        return isListShowImg;
    }

    public void setListShowImg(boolean listShowImg){
        SharedPreferences sharedPreferences = App.getINSTANCE().getSharedPreferences(spName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key_isListShowImg,listShowImg);
        if (editor.commit()){
            isListShowImg = listShowImg;
        }
    }

    public int getThumbnailQuality(){
        return thumbnailQuality;
    }

    public void setThumbnailQuality(int thumbnailQuality){
        SharedPreferences sharedPreferences  = App.getINSTANCE().getSharedPreferences(spName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key_thumbnailQuality,thumbnailQuality);
        if (editor.commit()){
            this.thumbnailQuality= thumbnailQuality;
        }
    }
}
