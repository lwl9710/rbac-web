package com.basic.project.service;

import com.basic.project.dto.ResponseDTO;
import com.basic.project.bo.UserDetailBO;
import com.basic.project.entities.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.project.entities.UserInfo;
import com.basic.project.vo.PageVO;
import com.basic.project.vo.UserVo;

import java.util.Map;

/**
* @author bigcat
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-01-27 16:39:04
*/
public interface UserService extends IService<User> {
    ResponseDTO<UserDetailBO> getUserDetail(String id);
    ResponseDTO<PageVO<UserVo>> list(Map<String, Object> params);
    ResponseDTO<String> delete(String id);
    ResponseDTO<String> update(UserInfo userInfo);
}
