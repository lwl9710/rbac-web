package com.basic.project.mapper;

import com.basic.project.bo.UserDetailBO;
import com.basic.project.entities.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.basic.project.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author bigcat
* @description 针对表【user_info(用户信息)】的数据库操作Mapper
* @createDate 2023-01-27 16:39:04
* @Entity com.basic.project.entities.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserDetailBO getUserDetailBO(@Param("id") String id);

    List<UserVo> selectUserVoList(Map<String, Object> params);

    Integer countUserVo();
}