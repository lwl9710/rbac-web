package com.basic.project.mapper;

import com.basic.project.entities.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author bigcat
* @description 针对表【role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2023-02-12 22:49:43
* @Entity com.basic.project.entities.RolePermission
*/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    String findByRoleId(@Param("roleId") String roleId);
    int updatePermissionsByRoleId (@Param("roleId") String roleId, @Param("permissions") String permissions);
}




