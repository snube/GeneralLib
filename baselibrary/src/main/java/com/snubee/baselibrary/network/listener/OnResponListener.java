package com.snubee.baselibrary.network.listener;

import java.io.File;

import com.snubee.baselibrary.network.model.RequestEntity;

/**
 * 网络请求响应时间回调
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2016/12/8 14:13
 **/
public interface OnResponListener {
    /**
     * 请求失败的回调
     *
     * @param networkRquestEntity 代表具体某个网络请求，同一个界面多个请求可以使用 requestIdentifier 来标识
     * @param responseString      响应字符串
     */
    void onRequestFailed(RequestEntity networkRquestEntity, String responseString);

    /**
     * 请求成功的回调,此处仅代表网络访问成功,得到服务器响应
     *
     * @param networkRquestEntity 代表具体某个网络请求，同一个界面多个请求可以使用 requestIdentifier 来标识
     * @param responseString      响应字符串,业务层的状态码在这个字段里
     */
    void onRequestSuceeessfully(RequestEntity networkRquestEntity, String responseString);


    /**
     * 网络响应扩展接口，提供开始事件和结束事件 考虑有些业务逻辑可能需要
     */
    interface OnResponExListener extends OnResponListener {

        /**
         * 进度条添加
         */
        void onProgress(long currentSize, long totalSize, float progress, long networkSpeed);

        /**
         * 网络请求开始
         *
         * @param entity
         */
        void onRequestStart(RequestEntity entity);


        /**
         * 下载文件完成
         *
         * @param file
         */
        void onDownFileFinshed(File file);

    }

}
