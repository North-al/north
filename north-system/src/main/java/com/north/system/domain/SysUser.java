package com.north.system.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户领域对象
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SysUser {

    private Long userId;

    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "状态不能为空")
    private String status;

    /**
     * 暂时作为领域字段保留；不要把它返回给用户列表接口。
     */
    @ToString.Exclude
    private String password;
}