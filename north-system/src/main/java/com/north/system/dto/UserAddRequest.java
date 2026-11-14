package com.north.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserAddRequest {
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "状态不能为空")
    private String status;
}
