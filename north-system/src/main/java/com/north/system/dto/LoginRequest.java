package com.north.system.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "登录请求")
@Data
public class LoginRequest {

    @NotBlank(message = "登录名不能为空")
    @Schema(description = "登录名", example = "admin")
    private String loginName;


    @NotBlank(message = "密码不能为空")
    @Schema(description = "登录密码", example = "仅用于请求示例，请勿使用真实密码")
    private String password;
}
