package com.sjdddd.train.common.exception;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/16 20:10
 **/
public class BusinessException extends BaseException{

    private BusinessExceptionEnum e;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(BusinessExceptionEnum e) {
        this.e = e;
    }

    public BusinessExceptionEnum getE() {
        return e;
    }

    public void setE(BusinessExceptionEnum e) {
        this.e = e;
    }

    @Override
    public Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }
}
