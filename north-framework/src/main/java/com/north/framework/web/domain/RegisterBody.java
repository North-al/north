package com.north.framework.web.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterBody {
    @NotBlank(message = "登录名不能为空")
    @Size(min = 2, max = 30, message = "登录名长度必须在2到30个字符之间")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 72, message = "密码长度必须在6到72个字符之间")
    private String password;

    private String userName;
}
