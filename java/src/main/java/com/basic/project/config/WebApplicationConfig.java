package com.basic.project.config;

import com.basic.project.interceptors.AccessInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan(basePackages = "com.basic.project.mapper")
@EnableTransactionManagement
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    private AccessInterceptor accessInterceptor;

    @Autowired
    private void inject(AccessInterceptor accessInterceptor) {
        this.accessInterceptor = accessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/error", "/**/*swagger*/**", "/v3/api-docs/**", "/webjars/**", "/doc.html");
    }
}
