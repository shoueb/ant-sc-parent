package com.ant.admin.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ant.admin.shiro.dao.MenuDao;
import com.ant.admin.shiro.service.MenuService;

import java.util.List;

/**
 * @Description: TODO
 * @Author:  Liu
 * @Date: 2020/6/17 13:18
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<String> listPerms(String username) {
        return menuDao.listPerms(username);
    }
}
