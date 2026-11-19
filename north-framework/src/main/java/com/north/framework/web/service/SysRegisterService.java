package com.north.framework.web.service;


import com.north.common.exception.BusinessException;
import com.north.framework.web.domain.RegisterBody;
import com.north.system.domain.SysUser;
import com.north.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SysRegisterService {

    private final SysUserService sysUserService;

    public String register(RegisterBody registerBody) {

        SysUser existingUser =
                sysUserService.selectUserByLoginName(registerBody.getLoginName());

        if (existingUser != null) {
            throw new BusinessException("用户已存在");
        }

        SysUser sysUser = new SysUser();
        sysUser.setLoginName(registerBody.getLoginName());
        sysUser.setPassword(registerBody.getPassword());
        sysUser.setUserName(registerBody.getUserName());
        sysUser.setEmail("");
        sysUser.setStatus("0");
        int rows = sysUserService.addUser(sysUser);

        if (rows <= 0) {
            return "注册失败，请联系系统管理员";
        }

        return null;

    }
}
