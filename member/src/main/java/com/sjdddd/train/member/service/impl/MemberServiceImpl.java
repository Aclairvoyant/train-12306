package com.sjdddd.train.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.sjdddd.train.common.exception.BusinessException;
import com.sjdddd.train.common.exception.BusinessExceptionEnum;
import com.sjdddd.train.common.resp.MemberLoginResp;
import com.sjdddd.train.common.util.SnowUtil;
import com.sjdddd.train.member.domain.Member;
import com.sjdddd.train.member.domain.MemberExample;
import com.sjdddd.train.member.mapper.MemberMapper;
import com.sjdddd.train.member.req.MemberLoginReq;
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
        Member memberDB = selectMemberByMobile(mobile);
        //判断list是否为空，即是否有账号
        if (ObjectUtil.isNotNull(memberDB)){
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
        Member memberDB = selectMemberByMobile(mobile);
        //如果手机号不存在，则插入注册
        if (ObjectUtil.isNull(memberDB)){
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

    @Override
    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectMemberByMobile(mobile);

        //判断是否获取了短信验证码
        if (ObjectUtil.isNull(memberDB)){
            //log.info("未获取短信验证码");
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        //校验验证码
        if (! "8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

//        MemberLoginResp memberLoginResp = MemberLoginResp.builder()
//                .id(memberDB.getId())
//                .mobile(memberDB.getMobile())
//                .token("token")
//                .build();

        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        //log.info("memberLoginResp:{}",memberLoginResp);
        log.info("登录成功");

        return memberLoginResp;
    }

    private Member selectMemberByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isEmpty(list)){
            return null;
        }else {
            return list.get(0);
        }
    }
}
