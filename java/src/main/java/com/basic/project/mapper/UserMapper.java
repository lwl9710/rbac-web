package com.basic.project.mapper;

import com.basic.project.entities.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author bigcat
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-01-27 16:39:04
* @Entity com.basic.project.entities.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectAccount(@Param("username") String username);

    int deleteByUserInfoId(@Param("userInfoId") String userInfoId);
}




