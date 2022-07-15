package com.mx.cvp.common.exception;

/**
 * 数据不存在异常
 *
 * @author cikai <cikai@mxnavi.com>
 * @date 2021/9/13
 */
public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
