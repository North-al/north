package com.north.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户领域对象
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SysUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long userId;

    @Schema(description = "登录名", example = "alice")
    @NotBlank(message = "登录名不能为空")
    private String loginName;

    @Schema(description = "用户名", example = "Alice")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @Schema(description = "邮箱", example = "alice@example.com")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "状态", example = "0")
    @NotBlank(message = "状态不能为空")
    private String status;

    /**
     * 暂时作为领域字段保留；不要把它返回给用户列表接口。
     */
    @JsonIgnore
    @ToString.Exclude
    private String password;
}