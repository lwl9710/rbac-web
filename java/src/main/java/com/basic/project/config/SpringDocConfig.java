package com.basic.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    private static final String HEADER_NAME = "Authorization";//请求头名称

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(components())
                .info(new Info().title("spring-basic-project").version("v1.0.0"));
    }

    private Components components() {
        Components components = new Components();
        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.type(SecurityScheme.Type.APIKEY);
        securityScheme.name(HEADER_NAME);
        securityScheme.in(SecurityScheme.In.HEADER);
        securityScheme.description("访问令牌");
        components.addSecuritySchemes(HEADER_NAME, securityScheme);
        return components;
    }
}
