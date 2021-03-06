package com.chen.reader.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.chen.reader.R;

import java.net.URI;

/**
 * Created by Administrator on 2017/12/12.
 */

public class ShareUtil {
    public static void share(Context context, int stringRes){
        share(context,context.getString(stringRes));
    }

    public static void share(Context context,String extraText){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,context.getString(R.string.actionShare));
        intent.putExtra(Intent.EXTRA_TEXT,extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(Intent.createChooser(intent,context.getString(R.string.actionShare)));
    }

    public static void shareImage(Context context, Uri uri,String title){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM,uri);
        shareIntent.setType("Image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent,title));
    }

}
