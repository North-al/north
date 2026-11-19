package com.north.framework.web.service;

import com.north.common.core.domain.model.LoginUser;
import com.north.common.utils.RedisCache;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

@Service
public class TokenService {
    private final RedisCache redisCache;

    @Value("${north.jwt.secret}")
    private final String secret;
    @Value("${north.jwt.expire-minutes:30}")
    private final Duration expire;


    public TokenService(
            RedisCache redisCache,
            @Value("${north.jwt.secret}") String secret,
            @Value("${north.jwt.expire-minutes:30}") long expireMinutes) {
        this.redisCache = redisCache;
        this.secret = secret;
        this.expire = Duration.ofMinutes(expireMinutes);
    }

    public String createToken(LoginUser loginUser) {
        long loginTime = System.currentTimeMillis();
        Date now = new Date(loginTime);
        Date expireAt = new Date(loginTime + expire.toMillis());

        String jwt = Jwts.builder()
                .subject(loginUser.getUserId().toString())
                .claim("loginName", loginUser.getLoginName())
                .claim("loginTime", loginTime)
                .issuedAt(now)
                .expiration(expireAt)
                .signWith(
                        Keys.hmacShaKeyFor(
                                secret.getBytes(StandardCharsets.UTF_8)
                        )
                )
                .compact();

        loginUser.setToken(jwt);
        loginUser.setLoginTime(loginTime);
        loginUser.setExpireTime(expireAt.getTime());

        redisCache.setCacheObject(
                "north:login:" + jwt,
                loginUser,
                expire
        );

        return jwt;
    }
}
