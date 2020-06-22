package com.ant.admin.shiro.service;

import java.util.List;

/**
 * @Description: TODO
 * @Author: LIU
 * @Date: 2020/6/17 13:17
 */
public interface MenuService {

    /**
     * 由用户名查询权限
     * @param username
     * @return
     */
    List<String> listPerms(String username);
}
