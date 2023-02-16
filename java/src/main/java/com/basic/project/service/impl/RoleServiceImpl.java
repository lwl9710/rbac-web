package com.basic.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.project.dto.UpdatePermissionDTO;
import com.basic.project.entities.RolePermission;
import com.basic.project.mapper.RolePermissionMapper;
import com.basic.project.utils.StringUtil;
import com.basic.project.vo.PageVO;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.Role;
import com.basic.project.service.RoleService;
import com.basic.project.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
* @author bigcat
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2023-01-27 02:04:06
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{
    private RolePermissionMapper rolePermissionMapper;
    private RoleMapper roleMapper;

    private StringRedisTemplate redisTemplate;

    @Autowired
    private void inject(RoleMapper roleMapper, RolePermissionMapper rolePermissionMapper, StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.roleMapper = roleMapper;
        this.rolePermissionMapper = rolePermissionMapper;
    }
    @Override
    public ResponseDTO<PageVO<Role>> selectPageList(Map<String, Object> params) {
        PageVO<Role> pageList = new PageVO<>();
        pageList.setList(roleMapper.selectPageList(params));
        if(params.get("pageNum") != null) {
            pageList.setTotal(roleMapper.countRole());
        }
        return ResponseDTO.success(pageList);
    }

    @Override
    public Role findRoleByUserInfoId(String userInfoId) {
        return roleMapper.findRoleByUserInfoId(userInfoId);
    }

    @Override
    public ResponseDTO<String> updatePermission(UpdatePermissionDTO updatePermissionDTO) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(updatePermissionDTO.getRoleId());
        rolePermission.setPermissions(String.join(",", updatePermissionDTO.getPermissions()));
        redisTemplate.delete(getRolePermissionKey(updatePermissionDTO.getRoleId()));
        if(StringUtil.isEmpty(rolePermissionMapper.findByRoleId(updatePermissionDTO.getRoleId()) )) {
            rolePermissionMapper.insert(rolePermission);
        } else {
            rolePermissionMapper.updatePermissionsByRoleId(rolePermission.getRoleId(), rolePermission.getPermissions());
        }
        return ResponseDTO.success("更新成功");
    }

    private String getRolePermissionKey(String roleId) {
        return "role_permission_" + roleId;
    }
}




