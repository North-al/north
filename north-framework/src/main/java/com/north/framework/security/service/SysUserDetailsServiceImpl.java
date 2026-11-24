package com.north.framework.security.service;


import com.north.common.core.domain.model.LoginUser;
import com.north.system.domain.SysUser;
import com.north.system.service.SysRoleService;
import com.north.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class SysUserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    @Override
    public @NullMarked UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.selectUserByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        if (!"0".equals(user.getStatus())) {
            throw new DisabledException("账号已停用");
        }

        List<String> permissions = sysUserService.selectPermissionsByLoginName(username);
        List<String> roles = sysRoleService.selectRoleKeysByUserId(user.getUserId());
        List<SimpleGrantedAuthority> authorities = permissions.stream().map(SimpleGrantedAuthority::new).toList();
        LoginUser loginUser = new LoginUser(user.getUserId(), user.getLoginName(), user.getStatus(), user.getPassword(), authorities);
        loginUser.setRoles(roles);

        return loginUser;
    }
}
