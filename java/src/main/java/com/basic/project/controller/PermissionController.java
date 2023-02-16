package com.basic.project.controller;

import com.basic.project.annotations.Access;
import com.basic.project.vo.PageVO;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Permission;
import com.basic.project.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "权限管理")
@RestController
@Access(Access.Policy.AUTHORITY)
@RequestMapping("/permission")
public class PermissionController {
    private PermissionService permissionService;

    @Autowired
    private void beanInject(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PutMapping("/add")
    @Operation(summary = "添加权限")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
            @SchemaProperty(name = "parentId", schema = @Schema(type = "string", description = "父级编号")),
            @SchemaProperty(name = "type", schema = @Schema(type = "string", description = "权限类型 1.普通权限")),
            @SchemaProperty(name = "url", schema = @Schema(type = "string", description = "权限url <font color='#f00'>*</font>")),
            @SchemaProperty(name = "description", schema = @Schema(type = "string", description = "权限描述"))
    })})
    public ResponseDTO<String> add(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResponseDTO.success("添加成功");
    }

    @Operation(summary = "删除权限")
    @DeleteMapping("/delete")
    public ResponseDTO<String> add(@RequestParam String id) {
        permissionService.removeById(id);
        return ResponseDTO.success("删除成功");
    }

    @Operation(summary = "修改权限")
    @PatchMapping("/update")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
            @SchemaProperty(name = "parentId", schema = @Schema(type = "string", description = "父级编号")),
            @SchemaProperty(name = "type", schema = @Schema(type = "string", description = "权限类型 1.普通权限")),
            @SchemaProperty(name = "url", schema = @Schema(type = "string", description = "权限url")),
            @SchemaProperty(name = "description", schema = @Schema(type = "string", description = "权限描述"))
    })})
    public ResponseDTO<String> update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return ResponseDTO.success("更新成功");
    }

    @Operation(summary = "权限列表")
    @PostMapping("/list")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
            @SchemaProperty(name = "pageNum", schema = @Schema(type = "number", description = "分页页码 <font color='#f00'>*</font>")),
            @SchemaProperty(name = "pageSize", schema = @Schema(type = "number", description = "分页大小 <font color='#f00'>*</font>"))
    })})
    public ResponseDTO<PageVO<Permission>> list(@RequestBody Map<String, Object> params) {
        return permissionService.selectPageList(params);
    }

    @Operation(summary = "获取角色权限")
    @GetMapping("/find")
    public ResponseDTO<String> find(@RequestParam String roleId) {
        return permissionService.find(roleId);
    }
}
