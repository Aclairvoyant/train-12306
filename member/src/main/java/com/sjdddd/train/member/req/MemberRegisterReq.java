package com.sjdddd.train.member.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/16 16:26
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRegisterReq {

    private String mobile;
}
