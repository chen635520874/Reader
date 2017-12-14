package com.chen.reader.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import com.chen.reader.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/14.
 */

public abstract class BaseDialog extends Dialog {

    private Context context;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog( Context context, int layoutId) {
       this(context,layoutId, R.style.MyDialog);
    }

    public BaseDialog(@NonNull Context context, int layoutId, int styleId) {
       super(context,styleId);
        this.context =context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutId,null);
        this.setContentView(view);
        ButterKnife.bind(this);
    }
}
