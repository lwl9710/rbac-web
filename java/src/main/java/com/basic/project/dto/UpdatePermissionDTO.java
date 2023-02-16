package com.basic.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class UpdatePermissionDTO {
    @Schema(description = "角色编号")
    private String roleId;
    @Schema(description = "权限列表")
    private List<String> permissions;
}
