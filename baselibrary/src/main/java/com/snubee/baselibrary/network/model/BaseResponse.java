package com.snubee.baselibrary.network.model;

import java.io.Serializable;

/**
 *  基础的通用响应实体
 *  @author snubee
 *  @email snubee96@gmail.com
 *  @time 2016/12/8 14:21
 *
**/
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T data;

}