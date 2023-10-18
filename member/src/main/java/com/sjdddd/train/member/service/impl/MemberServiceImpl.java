package com.sjdddd.train.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.sjdddd.train.common.exception.BusinessException;
import com.sjdddd.train.common.exception.BusinessExceptionEnum;
import com.sjdddd.train.common.util.SnowUtil;
import com.sjdddd.train.member.domain.Member;
import com.sjdddd.train.member.domain.MemberExample;
import com.sjdddd.train.member.mapper.MemberMapper;
import com.sjdddd.train.member.req.MemberRegisterReq;
import com.sjdddd.train.member.req.MemberSendCodeReq;
import com.sjdddd.train.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/8 17:26
 **/
@Slf4j
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
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return member.getId();
    }

    @Override
    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        List<Member> list = memberMapper.selectByExample(memberExample);
        //如果手机号不存在，则插入注册
        if (CollUtil.isEmpty(list)){
            log.info("手机号不存在，插入");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);

            memberMapper.insert(member);
        }else {
            log.info("手机号存在");
        }

        //生成验证码
        //String code = RandomUtil.randomString(4);
        String code = "8888"; //测试
        log.info("生成验证码：{}",code);

        //保存到短信记录表：手机号，验证码，有效期，是否已使用，业务类型，发送时间，使用时间

    }
}
