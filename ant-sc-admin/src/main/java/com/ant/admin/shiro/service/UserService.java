package com.ant.admin.shiro.service;

import com.ant.admin.shiro.model.UserDO;

/**
 * @Description: TODO
 * @Author: Liu
 * @Date: 2020/6/15 10:14
 */
public interface UserService {
    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return user对象
     */
    UserDO getById(Long id);

    /**
     * 用户名查询用户信息
     * @param username
     * @return
     */
    UserDO queryUserByUserName(String username);

}