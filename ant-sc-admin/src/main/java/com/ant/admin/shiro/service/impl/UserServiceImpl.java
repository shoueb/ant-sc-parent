package com.ant.admin.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ant.admin.shiro.dao.UserDao;
import com.ant.admin.shiro.model.UserDO;
import com.ant.admin.shiro.service.UserService;

/**
 * @Description: TODO
 * @Author:  Liu
 * @Date: 2020/6/15 10:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDO getById(Long id) {
        return userDao.get(id);
    }

    @Override
    public UserDO queryUserByUserName(String username) {
        return userDao.queryUserByUserName(username);
    }
}
