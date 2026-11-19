package com.north.framework.security.filter;

import com.north.common.core.domain.model.LoginUser;
import com.north.common.utils.RedisCache;
import com.north.framework.web.service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final RedisCache redisCache;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = getBearerToken(request);

            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                Claims claims = tokenService.parseToken(token);
                String sessionId = claims.get("sid", String.class);

                LoginUser loginUser = redisCache.getCacheObject("north:login:" + sessionId);

                if (loginUser != null) {
                    loginUser.setToken(token);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ignored) {
            // 无效、过期或已退出的 Token 将保持匿名状态。
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String getBearerToken(HttpServletRequest request) {
        String header =
                request.getHeader("Authorization");

        if (!StringUtils.hasText(header)
                || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7).trim();
    }
}
