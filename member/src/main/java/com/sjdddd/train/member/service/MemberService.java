package com.sjdddd.train.member.service;

import com.sjdddd.train.member.req.MemberRegisterReq;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/8 17:25
 **/
public interface MemberService {

    int countByExample();

    long register(MemberRegisterReq req);
}
