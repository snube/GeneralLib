package com.snubee.baselibrary.banner.loader;

import android.content.Context;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.snubee.baselibrary.frescoloader.FrescoUtil;

/**
 * 默认传入的是图片url集合则使用该loader，否则需要自定义
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2016/12/9 10:04
 **/
public class FrescoImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //用fresco加载图片
//        Uri uri = Uri.parse((String) path);
//        imageView.setImageURI(uri);
        FrescoUtil.show(imageView, (String) path);

    }

    //提供createImageView 方法，方便fresco自定义ImageView
    @Override
    public ImageView createImageView(Context context) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
