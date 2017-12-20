package com.chen.reader.module.picture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.chen.reader.R;
import com.chen.reader.module.picture.PictureContract;
import com.chen.reader.utils.ToastyUtil;
import com.chen.reader.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.http.Url;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/19.
 */

public class PicturePresenter implements PictureContract.Presenter {

    private Context mContext;

    public PicturePresenter(Context context){
        this.mContext = context;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }


    @Override
    public void savePicture(final String url, final int width,final int height,final String title) {

        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                try {
                    Glide.with(Utils.getContext())
                            .load(url)
                            .asBitmap()
                            .into(width, height)
                            .get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (bitmap==null){
                    subscriber.onError(new Exception("无法下载图片"));
                }
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }).flatMap(new Func1<Bitmap, Observable<Uri>>() {
            @Override
            public Observable<Uri> call(Bitmap bitmap) {
                File  appDir = new File(Environment.getExternalStorageDirectory(), mContext.getResources().getString(R.string.app_name));
                if (!appDir.exists()){
                    appDir.mkdir();
                }
                String fileName = title.replace('/','-')+".jpg";
                File file = new File(appDir,fileName);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    if (bitmap !=null){
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri uri = Uri.fromFile(file);

                Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri);
                mContext.sendBroadcast(scannerIntent);
                return Observable.just(uri);
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Uri>() {
                    @Override
                    public void call(Uri uri) {
                        File  appDir = new File(Environment.getExternalStorageDirectory(),mContext.getResources().getString(R.string.app_name));
                        String msg= String.format("图片以保存至%s文件夹",appDir.getAbsoluteFile());
                        ToastyUtil.showSuccess(msg);
                    }
                });
    }
}























