package com.basic.project.service.impl;

import com.basic.project.dto.ResponseDTO;
import com.basic.project.entities.User;
import com.basic.project.mapper.PermissionMapper;
import com.basic.project.mapper.RolePermissionMapper;
import com.basic.project.mapper.UserMapper;
import com.basic.project.service.AuthenticationService;
import com.basic.project.utils.DigestUtil;
import com.basic.project.utils.JwtUtil;
import com.basic.project.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserMapper userMapper;
    private PermissionMapper permissionMapper;

    private RolePermissionMapper rolePermissionMapper;

    private ValueOperations<String, String> redisClient;

    @Autowired
    private void beanInject(UserMapper userMapper, PermissionMapper permissionMapper, RolePermissionMapper rolePermissionMapper, StringRedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
        this.redisClient = redisTemplate.opsForValue();
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public ResponseDTO<String> login(Map<String, String> params) {
        String username = params.get("username");
        String password = DigestUtil.getMD5String(params.get("password"));
        User user = userMapper.selectAccount(username);
        if(user == null) {
            return ResponseDTO.warning("用户不存在");
        }
        if(!password.equals(user.getPassword())) {
            return ResponseDTO.warning("密码输入错误");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getUserInfoId());
        String token = JwtUtil.create(claims);
        return ResponseDTO.success(token);
    }

    @Override
    public boolean hasPermission(String roleId, String uri) {
        String rolePermissionKey = getRolePermissionKey(roleId);
        String permissions = redisClient.get(rolePermissionKey);
        List<String> urls;
        if(StringUtil.isEmpty(permissions)) {
            String rolePermissionIds = rolePermissionMapper.findByRoleId(roleId);
            if(StringUtil.isEmpty(rolePermissionIds)) {
                return false;
            } else {
                List<String> permissionIds = Arrays.asList(rolePermissionIds.split(","));
                urls = permissionMapper.selectUrlByIds(permissionIds);
                redisClient.set(rolePermissionKey, String.join(",", urls), 2, TimeUnit.HOURS);
            }
        } else {
            urls = Arrays.asList(permissions.split(","));
        }
        return urls.contains(uri);
    }


    private String getRolePermissionKey(String roleId) {
        return "role_permission_" + roleId;
    }
}
