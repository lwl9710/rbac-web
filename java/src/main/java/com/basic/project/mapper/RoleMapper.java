package com.basic.project.mapper;

import com.basic.project.entities.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author bigcat
* @description 针对表【role(角色表)】的数据库操作Mapper
* @createDate 2023-01-27 02:04:06
* @Entity com.basic.project.entities.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectPageList(Map<String, Object> params);

    Integer countRole();

    Role findRoleById(@Param("id") String id);

    Role findRoleByUserInfoId(@Param("userInfoId") String userInfoId);
}


