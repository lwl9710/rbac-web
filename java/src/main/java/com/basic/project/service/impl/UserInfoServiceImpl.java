package com.basic.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.project.entities.UserInfo;
import com.basic.project.service.UserInfoService;
import com.basic.project.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author bigcat
* @description 针对表【user_info(用户信息)】的数据库操作Service实现
* @createDate 2023-01-27 16:39:04
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




