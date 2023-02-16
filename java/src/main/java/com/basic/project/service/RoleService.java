package com.basic.project.service;

import com.basic.project.dto.UpdatePermissionDTO;
import com.basic.project.vo.PageVO;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author bigcat
* @description 针对表【role(角色表)】的数据库操作Service
* @createDate 2023-01-27 02:04:06
*/
public interface RoleService extends IService<Role> {
    ResponseDTO<PageVO<Role>> selectPageList(Map<String, Object> params);
    Role findRoleByUserInfoId(String userInfoId);
    ResponseDTO<String> updatePermission(UpdatePermissionDTO updatePermissionDTO);
}
