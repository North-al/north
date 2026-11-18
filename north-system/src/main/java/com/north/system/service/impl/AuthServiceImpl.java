package com.north.system.service.impl;

import com.north.system.domain.SysUser;
import com.north.system.dto.LoginRequest;
import com.north.system.dto.RegisterRequest;
import com.north.system.mapper.UserMapper;
import com.north.system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public SysUser login(LoginRequest request) {
        if(request == null || isBlank(request.getLoginName()) || isBlank(request.getPassword())) {
            throw new IllegalArgumentException("登录名和密码不能为空");
        }

        SysUser user = userMapper.getUserByLoginName(request.getLoginName());
        if (user.getStatus().equals("0")) {
            throw new IllegalArgumentException("用户已被禁用");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("账户或密码错误");
        }

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long register(RegisterRequest request) {
        if (request == null
                || isBlank(request.getLoginName())
                || isBlank(request.getPassword())
                || isBlank(request.getConfirmPassword())) {
            throw new IllegalArgumentException("注册信息不能为空");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("两次输入的密码不一致");
        }

        if (userMapper.countByLoginName(request.getLoginName()) > 0) {
            throw new IllegalArgumentException("登录名已存在");
        }

        SysUser user = new SysUser();
        user.setLoginName(request.getLoginName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus("0");

        int rows = userMapper.insertUser(user);
        if (rows != 1) {
            throw new IllegalStateException("注册失败");
        }

        return user.getUserId();
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
