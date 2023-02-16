package com.basic.project.interceptors;

import com.basic.project.annotations.Access;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Role;
import com.basic.project.service.AuthenticationService;
import com.basic.project.service.RoleService;
import com.basic.project.utils.JwtUtil;
import com.basic.project.utils.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class AccessInterceptor implements HandlerInterceptor {
    private ObjectMapper objectMapper;
    private AuthenticationService authenticationService;
    private RoleService roleService;

    @Autowired
    private void inject(ObjectMapper objectMapper, AuthenticationService authenticationService, RoleService roleService) {
        this.objectMapper = objectMapper;
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            Access annotation = method.getAnnotation(Access.class);
            Access.Policy policy;
            // 方法体没有标识权限的时候向上取类的标识
            if(annotation == null) {
                annotation = method.getDeclaringClass().getAnnotation(Access.class);
            }
            // 标识不存在的时候赋值默认标识
            if(annotation == null) {
                policy = Access.Policy.CERTIFICATE;
            } else {
                policy = annotation.value();
            }

            // 非匿名接口进行校验
            if(policy != Access.Policy.ANONYMOUS) {
                String token = request.getHeader("Authorization");
                if(StringUtil.isEmpty(token)) {
                    // 没有token
                    setResponseContent(response, ResponseDTO.unauthorized("未认证"));
                    return false;
                } else {
                    try {
                        Map<String, Object> claims = JwtUtil.parser(token);
                        String id = (String) claims.get("id");
                        if(StringUtil.isEmpty(id)) {
                            // 获取不到用户信息需要重新授权
                            setResponseContent(response, ResponseDTO.unauthorized());
                            return false;
                        } else {
                            if(policy == Access.Policy.AUTHORITY) {
                                Role role = roleService.findRoleByUserInfoId(id);
                                if(!role.getRoleName().equals("admin")) {
                                    boolean hasPermission = authenticationService.hasPermission(role.getId(), request.getRequestURI());
                                    if(!hasPermission) {
                                        // 没有权限
                                        setResponseContent(response, ResponseDTO.denied());
                                        return false;
                                    }
                                }
                            }
                            request.setAttribute("id", id);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // 认证异常
                        setResponseContent(response, ResponseDTO.unauthorized());
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void setResponseContent(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("Application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(data));
    }
}
