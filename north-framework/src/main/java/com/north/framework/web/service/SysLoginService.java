package com.north.framework.web.service;


import com.north.common.core.domain.model.LoginUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SysLoginService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


    public String login(String loginName, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(loginName, password)
        );

        if (!authenticate.isAuthenticated()) {
            throw new RuntimeException("登录失败");
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();


        log.info("用户 {} 权限信息: {}", loginName, authenticate.getAuthorities());
        if (loginUser != null) {
            return tokenService.createToken(loginUser);
        }

        throw new RuntimeException("登录失败");
    }

}
