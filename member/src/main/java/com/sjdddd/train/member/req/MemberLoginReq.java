package com.sjdddd.train.member.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 沈佳栋
 * @Description: TODO
 * @DateTime: 2023/10/18 16:38
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginReq {

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String code;


}
