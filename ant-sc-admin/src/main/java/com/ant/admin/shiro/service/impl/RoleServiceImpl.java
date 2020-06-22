package com.ant.admin.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ant.admin.shiro.dao.RoleDao;
import com.ant.admin.shiro.service.RoleService;

/**
 * @Description: TODO
 * @Author:  Liu
 * @Date: 2020/6/15 11:16
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public String getSignByUsername(String username) {
        return roleDao.getSignByUsername(username);
    }
}
