package com.north.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "注册请求")
@Data
public class RegisterRequest {

    @NotBlank(message = "登录名不能为空")
    @Size(max = 30, message = "登录名长度不能超过 30")
    @Schema(description = "登录名", example = "alice")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @Schema(description = "确认密码")
    private String confirmPassword;
}