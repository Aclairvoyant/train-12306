package com.sjdddd.train.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.sjdddd.train.common.exception.BusinessException;
import com.sjdddd.train.common.exception.BusinessExceptionEnum;
import com.sjdddd.train.member.domain.Member;
import com.sjdddd.train.member.domain.MemberExample;
import com.sjdddd.train.member.mapper.MemberMapper;
import com.sjdddd.train.member.req.MemberRegisterReq;
import com.sjdddd.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        List<Member> list = memberMapper.selectByExample(memberExample);
        //判断list是否为空，即是否有账号
        if (CollUtil.isNotEmpty(list)){
            //return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }
}
