package com.ant.admin.shiro.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ant.admin.shiro.model.UserDO;

/**
 * @Description: TODO
 * @Author: Liu
 * @Date: 2020/6/15 09:56
 */
@Mapper
public interface UserDao {
    /**
     * 由用户id获取用户信息
     * @param userId
     * @return
     */
    UserDO get(Long userId);

    /**
     * 由用户名获取用户信息
     * @param username
     * @return
     */
    UserDO queryUserByUserName(String username);
}
