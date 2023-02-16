package com.basic.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.project.entities.RolePermission;
import com.basic.project.service.RolePermissionService;
import com.basic.project.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author bigcat
* @description 针对表【role_permission(角色权限表)】的数据库操作Service实现
* @createDate 2023-02-12 22:49:43
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




