package com.basic.project.controller;

import com.basic.project.annotations.Access;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.bo.UserDetailBO;
import com.basic.project.entities.UserInfo;
import com.basic.project.service.UserService;
import com.basic.project.vo.PageVO;
import com.basic.project.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Access(Access.Policy.AUTHORITY)
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    private void beanInject(UserService userService) {
        this.userService = userService;
    }

    @Access(Access.Policy.CERTIFICATE)
    @GetMapping("/detail")
    @Operation(summary = "获取用户详情", security = {@SecurityRequirement(name="Authorization")})
    public ResponseDTO<UserDetailBO> getUserDetail(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        return userService.getUserDetail(id);
    }

    @Operation(summary = "用户列表")
    @PostMapping("/list")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
            @SchemaProperty(name = "pageNum", schema = @Schema(type = "number", description = "分页页码 <font color='#f00'>*</font>")),
            @SchemaProperty(name = "pageSize", schema = @Schema(type = "number", description = "分页大小 <font color='#f00'>*</font>"))
    })})
    public ResponseDTO<PageVO<UserVo>> list(@RequestBody Map<String, Object> params) {
        return userService.list(params);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/delete")
    public ResponseDTO<String> delete(@RequestParam String id) {
        return userService.delete(id);
    }

    @Operation(summary = "更新用户信息")
    @PatchMapping("/update")
    public ResponseDTO<String> update(@RequestBody UserInfo userInfo) {
        return userService.update(userInfo);
    }
}
