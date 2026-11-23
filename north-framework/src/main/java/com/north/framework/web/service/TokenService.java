package com.north.framework.web.service;

import com.north.common.core.domain.model.LoginUser;
import com.north.common.utils.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {
    private final RedisCache redisCache;
    private final byte[] secret;
    private final Duration expire;

    public TokenService(
            RedisCache redisCache,
            @Value("${north.jwt.secret}") String secret,
            @Value("${north.jwt.expire-minutes:30}") long expireMinutes) {

        this.redisCache = redisCache;
        this.secret =
                secret.getBytes(StandardCharsets.UTF_8);
        this.expire =
                Duration.ofMinutes(expireMinutes);
    }

    public String createToken(LoginUser loginUser) {
        long loginTime = System.currentTimeMillis();

        String sessionId =
                UUID.randomUUID().toString();

        String jwt = Jwts.builder()
                .subject(loginUser.getUserId().toString())
                .claim("loginName", loginUser.getLoginName())
                .claim("sid", sessionId)
                .issuedAt(new Date(loginTime))
                .expiration(
                        new Date(
                                loginTime + expire.toMillis()
                        )
                )
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();

        loginUser.setToken(jwt);
        loginUser.setLoginTime(loginTime);
        loginUser.setExpireTime(
                loginTime + expire.toMillis()
        );

        loginUser.setPassword(null);

        redisCache.setCacheObject(
                "north:login:" + sessionId,
                loginUser,
                expire
        );

        return jwt;
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public void deleteToken(String token) {
        Claims claims = parseToken(token);
        String sessionId = claims.get("sid", String.class);
        redisCache.deleteObject("north:login:" + sessionId);
    }
}
