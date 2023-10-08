package com.sjdddd.train.member.service.impl;

import com.sjdddd.train.member.mapper.MemberMapper;
import com.sjdddd.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/8 17:26
 **/
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int countByExample() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}
