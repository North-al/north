package com.north.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_AUTH = "bearerAuth";

    @Bean
    public OpenAPI northOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("North 管理系统 API")
                        .description("North 管理系统接口文档")
                        .version("1.0.0")).components(new Components()
                        .addSecuritySchemes(
                                BEARER_AUTH,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("输入登录接口返回的 JWT")
                        ))
                .addSecurityItem(
                        new SecurityRequirement().addList(BEARER_AUTH)
                );

    }
}
