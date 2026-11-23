package com.north.controller;

import com.north.common.core.domain.model.LoginUser;
import com.north.framework.web.domain.LoginBody;
import com.north.framework.web.domain.RegisterBody;
import com.north.framework.web.service.SysLoginService;
import com.north.framework.web.service.SysRegisterService;
import com.north.framework.web.service.TokenService;
import com.north.system.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final SysLoginService loginService;
    private final TokenService tokenService;
    private final SysRegisterService registerService;

    @Operation(security = {})
    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginBody loginBody) {
        log.info("登录请求: {}", loginBody);
        String token = loginService.login(
                loginBody.getLoginName(),
                loginBody.getPassword()
        );
        return Result.success(token);
    }

    @Operation(security = {})
    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody RegisterBody registerBody) {
        log.info("注册请求: {}", registerBody);
        String message = registerService.register(registerBody);

        if (message != null) {
            return Result.error(message);
        }

        return Result.success(null, "注册成功");
    }

    @PostMapping("/logout")
    public Result<Void> logout(
            @AuthenticationPrincipal LoginUser loginUser) {
        tokenService.deleteToken(loginUser.getToken());
        SecurityContextHolder.clearContext();
        return Result.success(null, "退出成功");
    }

    @GetMapping("/info")
    public Result<?> getInfo(
            @AuthenticationPrincipal LoginUser loginUser) {
        return Result.success(loginUser);
    }
}