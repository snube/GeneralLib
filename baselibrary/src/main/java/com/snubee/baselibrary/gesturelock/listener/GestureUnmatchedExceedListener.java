package com.snubee.baselibrary.gesturelock.listener;

/**
 * 项目名称：MeshLed_dxy
 * 类描述：
 * 创建人：oden
 * 创建时间：2016/7/25 21:24
 */
public interface GestureUnmatchedExceedListener {
    void onUnmatchedExceedBoundary(int count);
    void onUnmatchedLessFourPoint(int length);

}
