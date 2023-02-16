package com.basic.project.controller;

import com.basic.project.annotations.Access;
import com.basic.project.dto.UpdatePermissionDTO;
import com.basic.project.vo.PageVO;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Role;
import com.basic.project.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@Access(Access.Policy.AUTHORITY)
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    private void beanInject(RoleService roleService) {
        this.roleService = roleService;
    }

    @PutMapping("/add")
    @Operation(summary = "添加角色")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
        @SchemaProperty(name = "roleName", schema = @Schema(type = "string", description = "角色名称 <font color='#f00'>*</font>")),
        @SchemaProperty(name = "roleDescription", schema = @Schema(type = "string", description = "角色描述 <font color='#f00'>*</font>"))
    })})
    public ResponseDTO<String> add(@RequestBody Role role) {
        roleService.save(role);
        return ResponseDTO.success("添加成功");
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/delete")
    public ResponseDTO<String> add(@RequestParam String id) {
        roleService.removeById(id);
        return ResponseDTO.success("删除成功");
    }

    @Operation(summary = "修改角色")
    @PatchMapping("/update")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
        @SchemaProperty(name = "id", schema = @Schema(type = "string", description = "角色编号 <font color='#f00'>*</font>")),
        @SchemaProperty(name = "roleName", schema = @Schema(type = "string", description = "角色名称")),
        @SchemaProperty(name = "roleDescription", schema = @Schema(type = "string", description = "角色描述"))
    })})
    public ResponseDTO<String> update(@RequestBody Role role) {
        roleService.updateById(role);
        return ResponseDTO.success("更新成功");
    }

    @Operation(summary = "查找角色")
    @GetMapping("/find")
    public ResponseDTO<Role> find(@RequestParam String id) {
        return ResponseDTO.success(roleService.getById(id));
    }

    @Operation(summary = "角色列表")
    @PostMapping("/list")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(schemaProperties = {
        @SchemaProperty(name = "pageNum", schema = @Schema(type = "number", description = "分页页码 <font color='#f00'>*</font>")),
        @SchemaProperty(name = "pageSize", schema = @Schema(type = "number", description = "分页大小 <font color='#f00'>*</font>"))
    })})
    public ResponseDTO<PageVO<Role>> list(@RequestBody Map<String, Object> params) {
        return roleService.selectPageList(params);
    }

    @Operation(summary = "更新角色权限")
    @PatchMapping("/updatePermission")
    public ResponseDTO<String> updatePermission(@RequestBody UpdatePermissionDTO updatePermissionDTO) {
        return roleService.updatePermission(updatePermissionDTO);
    }
}
