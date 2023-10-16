package com.sjdddd.train.common.exception;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/16 20:11
 **/
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    //不写入堆栈，提高性能
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
