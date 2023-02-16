package com.basic.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.project.mapper.RolePermissionMapper;
import com.basic.project.utils.StringUtil;
import com.basic.project.vo.PageVO;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Permission;
import com.basic.project.service.PermissionService;
import com.basic.project.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
* @author bigcat
* @description 针对表【permission(权限表)】的数据库操作Service实现
* @createDate 2023-01-27 05:21:16
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    private PermissionMapper permissionMapper;

    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private void inject(PermissionMapper permissionMapper, RolePermissionMapper rolePermissionMapper) {
        this.permissionMapper = permissionMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public ResponseDTO<PageVO<Permission>> selectPageList(Map<String, Object> params) {

        PageVO<Permission> pageList = new PageVO<>();
        pageList.setList(permissionMapper.selectPageList(params));
        if(params.get("pageNum") != null) {
            pageList.setTotal(permissionMapper.countPermission());
        }
        return ResponseDTO.success(pageList);
    }

    @Override
    public ResponseDTO<String> find(String roleId) {
        String permissions = rolePermissionMapper.findByRoleId(roleId);
        if(StringUtil.isEmpty(permissions)) {
            permissions = "";
        }
        return ResponseDTO.success(permissions);
    }

    @Override
    public List<Permission> getRolePermissions(String roleId) {
        String permissions = rolePermissionMapper.findByRoleId(roleId);
        if(StringUtil.isEmpty(permissions)) {
            permissions = "";
        }
        List<String> permissionIds = Arrays.asList(permissions.split(","));
        return permissionMapper.selectPermissionByIds(permissionIds);
    }
}



