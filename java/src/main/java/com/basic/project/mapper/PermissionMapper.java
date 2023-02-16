package com.basic.project.mapper;

import com.basic.project.entities.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author bigcat
* @description 针对表【permission(权限表)】的数据库操作Mapper
* @createDate 2023-01-27 05:21:16
* @Entity com.basic.project.entities.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> selectPageList(Map<String, Object> params);

    Integer countPermission();

    List<String> selectUrlByIds(@Param("ids") List<String> ids);

    List<Permission> selectPermissionByIds(@Param("ids") List<String> ids);
}




