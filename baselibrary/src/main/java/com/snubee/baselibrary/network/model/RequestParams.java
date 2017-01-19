package com.snubee.baselibrary.network.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求参数实体
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2016/12/8 14:16
 **/
public class RequestParams {

    public enum ParamsType {
        MAP, JSON;
    }

    public ParamsType mParamsType = ParamsType.MAP;//默认的参数类型是map的键值对形式

    public Map<String, String> stringMap = new HashMap<String, String>();
    public Map<String, File> fileMap = new HashMap<String, File>();
    public Map<String, String> headers = new HashMap<>();
    public String json;//json类型的文本参数

    public void put(String k, String v) {
        try {
            stringMap.put(k, v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(String key, File file) {
        try {
            fileMap.put(key, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addHeader(String key, String value) {
        try {
            headers.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
