package com.basic.project.controller;

import com.basic.project.annotations.Access;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.service.AuthenticationService;
import com.basic.project.utils.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "认证管理")
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    private void beanInject(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "用户登陆")
    @Access(Access.Policy.ANONYMOUS)
    @PostMapping("/login")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
            @SchemaProperty(name = "username", schema = @Schema(type = "string", description = "用户名 <font color='#f00'>*</font>")),
            @SchemaProperty(name = "password", schema = @Schema(type = "string", description = "密码 <font color='#f00'>*</font>"))
    })})
    public ResponseDTO<String> login(@RequestBody Map<String, String> params) {
        if(StringUtil.isEmpty(params.get("username"))) {
            return ResponseDTO.warning("请输入用户名");
        }
        if(StringUtil.isEmpty(params.get("password"))) {
            return ResponseDTO.warning("请输入密码");
        }
        return authenticationService.login(params);
    }
}
