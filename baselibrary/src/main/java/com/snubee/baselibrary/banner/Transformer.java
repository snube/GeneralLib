package com.snubee.baselibrary.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.snubee.baselibrary.banner.transformer.AccordionTransformer;
import com.snubee.baselibrary.banner.transformer.BackgroundToForegroundTransformer;
import com.snubee.baselibrary.banner.transformer.CubeInTransformer;
import com.snubee.baselibrary.banner.transformer.CubeOutTransformer;
import com.snubee.baselibrary.banner.transformer.DefaultTransformer;
import com.snubee.baselibrary.banner.transformer.DepthPageTransformer;
import com.snubee.baselibrary.banner.transformer.FlipHorizontalTransformer;
import com.snubee.baselibrary.banner.transformer.FlipVerticalTransformer;
import com.snubee.baselibrary.banner.transformer.ForegroundToBackgroundTransformer;
import com.snubee.baselibrary.banner.transformer.RotateDownTransformer;
import com.snubee.baselibrary.banner.transformer.RotateUpTransformer;
import com.snubee.baselibrary.banner.transformer.ScaleInOutTransformer;
import com.snubee.baselibrary.banner.transformer.StackTransformer;
import com.snubee.baselibrary.banner.transformer.TabletTransformer;
import com.snubee.baselibrary.banner.transformer.ZoomInTransformer;
import com.snubee.baselibrary.banner.transformer.ZoomOutSlideTransformer;
import com.snubee.baselibrary.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
