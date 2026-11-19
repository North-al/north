package com.north.common.core.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private Long userId;
    private String loginName;
    private String status;

    @ToString.Exclude
    private String password;

    private String token;
    private Long loginTime;
    private Long expireTime;

    @ToString.Exclude
    private Collection<? extends GrantedAuthority> authorities =
            Collections.emptyList();

    public LoginUser(
            Long userId,
            String loginName,
            String status,
            String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.loginName = loginName;
        this.password = password;
        this.status = status;
        this.authorities = authorities == null
                ? Collections.emptyList()
                : authorities;
    }

    @Override
    public @NullMarked Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @NullMarked
    public String getUsername() {
        return loginName;
    }

    /**
     * 返回账号是否未过期
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 返回账号是否未锁定
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    /**
     * 返回凭据（通常是密码）是否未过期。
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 返回账号是否启用
     * */
    @Override
    public boolean isEnabled() {
        return "0".equals(status);
    }
}