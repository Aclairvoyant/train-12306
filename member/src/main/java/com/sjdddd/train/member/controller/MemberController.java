package com.sjdddd.train.member.controller;

import com.sjdddd.train.common.resp.CommonResp;
import com.sjdddd.train.common.resp.MemberLoginResp;
import com.sjdddd.train.member.req.MemberLoginReq;
import com.sjdddd.train.member.req.MemberRegisterReq;
import com.sjdddd.train.member.req.MemberSendCodeReq;
import com.sjdddd.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/8 17:28
 **/

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        int count = memberService.countByExample();
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
//        CommonResp<Long> commonResp = new CommonResp<>();
//        commonResp.setContent(register);
//        return commonResp;
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
