package com.sjdddd.train.member.controller;

import com.sjdddd.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public int count() {
        return memberService.count();
    }
}
