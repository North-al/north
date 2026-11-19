package com.north.controller;

import com.north.framework.web.domain.LoginBody;
import com.north.framework.web.service.SysLoginService;
import com.north.framework.web.service.TokenService;
import com.north.system.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final SysLoginService loginService;

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginBody loginBody) {
        String token = loginService.login(
                loginBody.getLoginName(),
                loginBody.getPassword()
        );
        return Result.success(token);
    }
}