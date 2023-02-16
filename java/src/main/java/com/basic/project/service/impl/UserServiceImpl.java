package com.basic.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.project.dto.ResponseDTO;
import com.basic.project.bo.UserDetailBO;
import com.basic.project.entities.Role;
import com.basic.project.entities.User;
import com.basic.project.entities.UserInfo;
import com.basic.project.mapper.UserInfoMapper;
import com.basic.project.service.PermissionService;
import com.basic.project.service.UserService;
import com.basic.project.mapper.UserMapper;
import com.basic.project.utils.StringUtil;
import com.basic.project.vo.PageVO;
import com.basic.project.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
* @author bigcat
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-01-27 16:39:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    private UserMapper userMapper;
    private UserInfoMapper userInfoMapper;

    private PermissionService permissionService;

    @Autowired
    private void beanInject(UserInfoMapper userInfoMapper, UserMapper userMapper, PermissionService permissionService) {
        this.userMapper = userMapper;
        this.userInfoMapper = userInfoMapper;
        this.permissionService = permissionService;
    }

    @Override
    public ResponseDTO<UserDetailBO> getUserDetail(String id) {
        UserDetailBO userDetailBO = userInfoMapper.getUserDetailBO(id);
        Role role = userDetailBO.getRole();
        if(role != null && !StringUtil.isEmpty(role.getId())) {
            userDetailBO.setAuthorities(permissionService.getRolePermissions(role.getId()));
        }
        return ResponseDTO.success(userDetailBO);
    }

    @Override
    public ResponseDTO<PageVO<UserVo>> list(Map<String, Object> params) {
        PageVO<UserVo> pageList = new PageVO<>();
        pageList.setList(userInfoMapper.selectUserVoList(params));
        pageList.setTotal(userInfoMapper.countUserVo());
        return ResponseDTO.success(pageList);
    }

    @Transactional
    @Override
    public ResponseDTO<String> delete(String id) {
        this.userMapper.deleteByUserInfoId(id);
        this.userInfoMapper.deleteById(id);
        return ResponseDTO.success("删除成功");
    }

    @Override
    public ResponseDTO<String> update(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
        return ResponseDTO.success("更新成功");
    }
}




