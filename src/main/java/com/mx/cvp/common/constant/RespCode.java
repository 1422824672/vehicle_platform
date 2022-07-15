package com.mx.cvp.common.constant;

/**
 * 状态码/错误码
 *
 * @author cikai <cikai@mxnavi.com>
 * @date 2021/9/13
 */
public enum RespCode {
    /**
     * 成功
     */
    SUCCESS(100),
    /**
     * 宏观错误码：服务端错误
     */
    SERVER_ERROR(200001),
    /**
     * 没有请求参数
     */
    PARAM_ERR_NULL_PARAM(100100),
    /**
     * 没有请求参数
     */
    DATA_NOT_FOUND(100400)
    ;

    private int code;

    public int value() {
        return code;
    }

    RespCode(int code) {
        this.code = code;
    }
}
