package com.north.framework.web.service;


import com.north.common.core.domain.model.LoginUser;
import com.north.common.exception.LoginException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class SysLoginService {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


    public String login(String loginName, String password) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(loginName, password)
            );

            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            return tokenService.createToken(loginUser);
        } catch (BadCredentialsException e) {
            log.error("登录失败，用户名或密码错误", e);
            throw new LoginException("用户名或密码错误", e);
        } catch (AuthenticationException e) {
            log.error("登录失败，认证异常", e);
            throw new LoginException("认证异常，请联系管理员", e);
        } catch (Exception e) {
            log.error("登录失败", e);
            throw new LoginException("登录失败，请联系管理员", e);
        }
    }
}
