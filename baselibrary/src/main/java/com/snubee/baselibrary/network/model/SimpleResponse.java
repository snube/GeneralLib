package com.snubee.baselibrary.network.model;

import java.io.Serializable;

/**
 *  简单的响应
 *  @author snubee
 *  @email snubee96@gmail.com
 *  @time 2016/12/8 14:22
 *
**/
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public BaseResponse toBaseResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.code = code;
        baseResponse.msg = msg;
        return baseResponse;
    }
}