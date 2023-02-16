package com.basic.project.service;

import com.basic.project.vo.PageVO;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author bigcat
* @description 针对表【permission(权限表)】的数据库操作Service
* @createDate 2023-01-27 05:21:16
*/
public interface PermissionService extends IService<Permission> {
    ResponseDTO<PageVO<Permission>> selectPageList(Map<String, Object> params);

    ResponseDTO<String> find(String roleId);

    List<Permission> getRolePermissions(String roleId);

}
