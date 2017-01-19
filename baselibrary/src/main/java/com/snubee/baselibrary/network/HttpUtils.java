package com.snubee.baselibrary.network;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.Formatter;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.PostRequest;
import com.snubee.baselibrary.R;
import com.snubee.baselibrary.network.callback.JsonCallback;
import com.snubee.baselibrary.network.listener.OnResponListener;
import com.snubee.baselibrary.network.model.RequestEntity;
import com.snubee.baselibrary.network.model.RequestParams;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 网络请求工具
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2016/12/8 14:49
 **/
public class HttpUtils {


    public static boolean isNetworkUnAvailable(Context context) {
        if (!NetworkUtils.isConnected(context)) {
            ToastUtils.showShortToastSafe(context, R.string.network_is_disabled);
            return true;
        }
        return false;
    }


    /**
     * 简单的不带参数的get请求
     *
     * @param context
     * @param url
     * @param callback
     * @param <T>
     */
    public static <T> void get(Context context, String url, AbsCallback<T> callback) {
        if (isNetworkUnAvailable(context)) return;
        OkGo.get(url).tag(context).execute(callback);
    }

    /**
     * 简单的不带参数的post请求
     *
     * @param context
     * @param url
     * @param callback
     * @param <T>
     */
    public static <T> void post(Context context, String url, AbsCallback<T> callback) {
        if (isNetworkUnAvailable(context)) return;
        OkGo.post(url).tag(context).execute(callback);
    }


    /**
     * 获取服务器文件
     *
     * @param context
     * @param url
     * @param fileCallBack
     */
    public static void getFile(Context context, String url, FileCallback fileCallBack) {
        if (isNetworkUnAvailable(context)) return;
        OkGo.get(url).tag(url).execute(fileCallBack);
    }


    /**
     * post请求
     *
     * @param context
     * @param url
     * @param params   请求参数实体
     * @param callback
     * @param args     请求标签
     * @param
     */
    public static <T> void post(Context context, String url, RequestParams params, AbsCallback<T> callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        postExecute(context, url, params, callback, args);
    }

    /**
     * 执行post请求
     *
     * @param context
     * @param url
     * @param params
     * @param callback
     * @param args
     * @param <T>
     */
    private static <T> void postExecute(Context context, String url, RequestParams params, AbsCallback<T> callback, Object[] args) {
        Object tag = (args != null && args.length > 0) ? args[0] : context;

        PostRequest postRequest = OkGo.post(url);
        postRequest.tag(tag);

        if (params != null) {
            //添加头部参数
            if (params.headers != null && params.headers.size() > 0) {
                while (params.headers.keySet().iterator().hasNext()) {
                    String key = params.headers.keySet().iterator().next();
                    String value = params.headers.get(key);
                    postRequest.headers(key, value);
                }
            }

            if (params.mParamsType == RequestParams.ParamsType.JSON) {
                if (!TextUtils.isEmpty(params.json))
                    postRequest.upJson(params.json);
            } else {
                if (params.fileMap.size() > 0) {
                    for (String key : params.fileMap.keySet()) {
                        File file = params.fileMap.get(key);
                        String path = file.getAbsolutePath();
                        int index = path.lastIndexOf("/") + 1;
                        String fileName = path.substring(index);
                        postRequest.params(key, file, fileName);
                    }
                }

                if (params.stringMap.size() > 0)
                    postRequest.params(params.stringMap);
            }
        }
        postRequest.execute(callback);
    }

    /**
     * post请求
     *
     * @param context
     * @param url
     * @param params   请求参数实体
     * @param callback
     * @param args     请求标签
     * @param
     */
    public static void post(Context context, String url, RequestParams params, StringCallback callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        postExecute(context, url, params, callback, args);
    }


    /**
     * 带参数的get请求，获取一个自定义的放回数据类型
     */
    public static <T> void get(Context context, String url, RequestParams params, AbsCallback<T> callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        Object tag = (args != null && args.length > 0) ? args[0] : context;

        getExecute(url, params, callback, tag);

    }

    /**
     * 执行get请求
     *
     * @param url
     * @param params
     * @param callback
     * @param tag
     * @param <T>
     */
    private static <T> void getExecute(String url, RequestParams params, AbsCallback<T> callback, Object tag) {
        GetRequest getRequest = OkGo.get(url);
        getRequest.tag(tag);

        if (params != null) {
            //添加头部参数
            if (params.headers != null && params.headers.size() > 0) {
                while (params.headers.keySet().iterator().hasNext()) {
                    String key = params.headers.keySet().iterator().next();
                    String value = params.headers.get(key);
                    getRequest.headers(key, value);
                }
            }
            if (params.stringMap.size() > 0)
                getRequest.params(params.stringMap);

        }
        getRequest.execute(callback);
    }

    /**
     * 带参数的get请求，获取一个String对象
     */
    public static void get(Context context, String url, RequestParams params, StringCallback callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        Object tag = (args != null && args.length > 0) ? args[0] : context;

        getExecute(url, params, callback, tag);

    }

    /**
     * 带参数的get请求，获取一个json对象
     */
    public static <T> void get(Context context, String url, RequestParams params, JsonCallback<T> callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        Object tag = (args != null && args.length > 0) ? args[0] : context;

        getExecute(url, params, callback, tag);
    }

    /**
     * 文件下载
     *
     * @param context
     * @param networkRequestEntity
     * @param listener
     */
    public static void downloadFile(Context context, final RequestEntity networkRequestEntity, final OnResponListener.OnResponExListener listener) {
        if (networkRequestEntity.fileString != null && networkRequestEntity.fileString.length() > 0) {
            int index = networkRequestEntity.fileString.lastIndexOf("/") + 1;
            String file = networkRequestEntity.fileString.substring(0, index - 1);
            String fileName = networkRequestEntity.fileString.substring(index);

            getFile(context, networkRequestEntity.url, new FileCallback(file, fileName) {
                @Override
                public void onBefore(BaseRequest request) {
                    if (listener != null) {
                        listener.onRequestStart(networkRequestEntity);
                    }
                }

                @Override
                public void onSuccess(File file, Call call, Response response) {
                    if (listener != null) {
                        listener.onDownFileFinshed(file);
                    }
                }

                @Override
                public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                    System.out.println("downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);

                    if (listener != null) {
                        listener.onProgress(currentSize, totalSize, progress, networkSpeed);
                    }
                }

                @Override
                public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
                    super.onError(call, response, e);
                    if (listener != null) {
                        listener.onRequestFailed(networkRequestEntity, "下载出错了！");
                    }
                }
            });

        }
    }

    /**
     * 文件上传
     *
     * @param context
     * @param url
     * @param params
     * @param callback
     * @param args
     */
    public static void uploadeFile(Context context, String url, RequestParams params, StringCallback callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        Object tag = (args != null && args.length > 0) ? args[0] : context;

        PostRequest postRequest = OkGo.post(url);
        if (params.fileMap.size() > 0) {
            for (String key : params.fileMap.keySet()) {
                File file = params.fileMap.get(key);
                String path = file.getAbsolutePath();
                int index = path.lastIndexOf("/") + 1;
                String fileName = path.substring(index);
                postRequest.params(key, file, fileName);
            }
        }
        postRequest.tag(tag).params(params.stringMap).execute(callback);
    }

    /**
     * 文件上传
     *
     * @param context
     * @param url
     * @param params
     * @param callback
     * @param args
     * @param <T>
     */
    public static <T> void uploadeFile(Context context, String url, RequestParams params, JsonCallback<T> callback, Object... args) {
        if (isNetworkUnAvailable(context)) return;

        Object tag = (args != null && args.length > 0) ? args[0] : context;

        PostRequest postRequest = OkGo.post(url);
        if (params.fileMap.size() > 0) {
            for (String key : params.fileMap.keySet()) {
                File file = params.fileMap.get(key);
                String path = file.getAbsolutePath();
                int index = path.lastIndexOf("/") + 1;
                String fileName = path.substring(index);
                postRequest.params(key, file, fileName);
            }
        }
        postRequest.tag(tag).params(params.stringMap).execute(callback);
    }


    /**
     * 根据标签取消请求
     *
     * @param object
     */
    public static void cancleTag(Object object) {
        OkGo.getInstance().cancelTag(object);
    }

}
