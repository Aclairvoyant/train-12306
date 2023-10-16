package com.sjdddd.train.common.exception;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/16 20:08
 **/
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已存在"),;
    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
