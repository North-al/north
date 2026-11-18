package com.north.system.service;

import com.north.system.domain.SysUser;
import com.north.system.dto.LoginRequest;
import com.north.system.dto.RegisterRequest;

public interface AuthService {
    /**
     * 校验登录凭据。
     * <p>
     * 注意：当前尚未创建 Token 或 Session 登录态。
     */
    SysUser login(LoginRequest request);

    /**
     * 注册用户，返回新用户 ID。
     */
    Long register(RegisterRequest request);
}
