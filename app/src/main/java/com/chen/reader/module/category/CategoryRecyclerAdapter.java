package com.chen.reader.module.category;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chen.reader.ConfigManage;
import com.chen.reader.R;
import com.chen.reader.base.adapter.CommonRecyclerAdapter;
import com.chen.reader.base.adapter.CommonRecyclerHolder;
import com.chen.reader.base.adapter.CommonViewPagerAdapter;
import com.chen.reader.base.adapter.ListenerWithPosition;
import com.chen.reader.model.CategoryResult;
import com.chen.reader.module.web.WebViewActivity;
import com.chen.reader.utils.TimeUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */

public class CategoryRecyclerAdapter extends CommonRecyclerAdapter<CategoryResult.ResultsBean>
        implements ListenerWithPosition.OnClickWithPositionListener<CommonRecyclerHolder> {

    public CategoryRecyclerAdapter(Context context) {
        super(context, null, R.layout.item_category);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, CategoryResult.ResultsBean resultsBean) {
        if (resultsBean !=null){
            ImageView imageView = holder.getView(R.id.category_item_img);
            if (ConfigManage.INSTSNCE.isListShowImg()){
                imageView.setVisibility(View.VISIBLE);
                String quality = "";
                if (resultsBean.images !=null && resultsBean.images.size()>0){
                    switch (ConfigManage.INSTSNCE.getThumbnailQuality()){
                        case 0:
                            quality = "";
                            break;
                        case 1:
                            quality = "?imageView2/0/w/400";
                            break;
                        case 2:
                            quality = "?imageView2/0/w/190";
                            break;
                    }
                    Glide.with(mContext)
                            .load(resultsBean.images.get(0)+quality)
                            .placeholder(R.mipmap.image_default)
                            .error(R.mipmap.image_default)
                            .into(imageView);
                }else {
                    Glide.with(mContext).load(R.mipmap.image_default).into(imageView);
                }
            }else {
                imageView.setVisibility(View.GONE);
            }
            holder.setTextViewText(R.id.category_item_desc,resultsBean.desc ==null? "unknown":resultsBean.desc);
            holder.setTextViewText(R.id.category_item_author,resultsBean.who==null?"unknow":resultsBean.who);
            holder.setTextViewText(R.id.category_item_src,resultsBean.soure ==null?"unknow":resultsBean.soure);
            holder.setTextViewText(R.id.category_item_time, TimeUtil.dataFormat(resultsBean.publishesAt));
            holder.setOnClickListener(this,R.id.category_item_layout);
        }
    }
    @Override
    public void onClick(View v,int position,CommonRecyclerHolder holder){
        Intent intent  = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(WebViewActivity.GANK_TITLE,mData.get(position).desc);
        intent.putExtra(WebViewActivity.GANK_URL,mData.get(position).url);
        mContext.startActivity(intent);
    }
}




































