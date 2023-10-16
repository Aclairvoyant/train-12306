package com.domain;

/**
* @Author: 沈佳栋
* @Description: TODO
* @DateTime: 2023/10/9 19:32
**/
/**
    * 会员
    */
public class Member {
    /**
    * id
    */
    private Long id;

    /**
    * 手机号
    */
    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}