package com.snubee.baselibrary.network.model;


import android.os.Bundle;

import com.blankj.utilcode.utils.StringUtils;


/**
 *  网络请求实体
 *  @author snubee
 *  @email snubee96@gmail.com
 *  @time 2016/12/8 13:49
 *
**/
public class RequestEntity {
    public String url;
    public RequestParams params;
    public String fileString;//完整地址字符串 如：xxx/XXX/XXX.apk
    public boolean isShowDilaog;
    private int requestIdentifier; // 用来标识 一个界面多个网络请求的访问
    private Bundle bundle; // 可以用来传递数据

    /**
     * 默认会显示加载对话框
     *
     * @param url    不能为null，必须是完整的
     * @param params 参数 不能为null
     */
    public RequestEntity(String url, RequestParams params) {
        this(url, params, true);
    }

    /**
     * 默认会显示加载对话框
     *
     * @param url          不能为null，必须是完整的
     * @param params       参数 不能为null
     * @param isShowDilaog 是否显示对话框
     */
    public RequestEntity(String url, RequestParams params, boolean isShowDilaog) {
        this.url = url;
        this.params = params;
        this.isShowDilaog = isShowDilaog;
    }

    /**
     * 默认会显示加载对话框
     *
     * @param url          不能为null，必须是完整的
     * @param fileString       参数 不能为null
     * @param isShowDilaog 是否显示对话框
     */
    public RequestEntity(String url, String fileString, boolean isShowDilaog) {
        this.url = url;
        this.fileString = fileString;
        this.isShowDilaog = isShowDilaog;
    }


    /**
     * 是否可以请求
     *
     * @return
     */
    public boolean isVaildRequest() {
        if (StringUtils.isEmpty(url))
            return false;
        if (params == null)
            return false;
        return true;
    }


    public void setRequestIdentifier(int requestIdentifier) {
        this.requestIdentifier = requestIdentifier;
    }


    public int getRequestIdentifier() {
        return requestIdentifier;
    }

    public RequestParams getParams() {
        return params;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setIsShowDilaog(boolean isShowDilaog) {
        this.isShowDilaog = isShowDilaog;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }
}
