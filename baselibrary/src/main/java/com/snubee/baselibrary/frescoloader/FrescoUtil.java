package com.snubee.baselibrary.frescoloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * 图片加载工具类
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2016/12/7 14:14
 **/
public class FrescoUtil {
    public static Context context;


    public static void show(View view, String url) {
        FrescoManeger.getInstance().measureView(view);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();

        //处理matchparent的情况:宽度设置为屏幕宽度减去两边的边距共30dp
        if (width < 5) {//matchparent
            width = FrescoManeger.screenWidth;
        }

        if (view instanceof SimpleDraweeView) {
            FrescoManeger.getInstance().loadUrl(url, (SimpleDraweeView) view, null, width, height, null);
        }
    }


    public static void getBitmap(@NonNull final String url, @NonNull final int width, @NonNull final int height,
                                 @Nullable BasePostprocessor processor, @NonNull final FrescoManeger.BitmapListener listener) {
        FrescoManeger.getInstance().getBitmapWithProcessor(url, context, width, height, processor, listener);
    }


}