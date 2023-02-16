package com.basic.project.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.basic.project.entities.Permission;
import com.basic.project.entities.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserDetailBO {
    @Schema(description = "用户信息编号")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @Schema(description = "用户昵称")
    private String nickname;
    @Schema(description = "用户头像")
    private String avatar;
    @Schema(description = "角色信息")
    private Role role;
    @Schema(description = "用户权限")
    private List<Permission> authorities;
}
