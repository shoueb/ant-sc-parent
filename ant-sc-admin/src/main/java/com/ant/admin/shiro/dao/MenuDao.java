package com.ant.admin.shiro.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: MenuDao
 * @Author: Liu
 * @Date: 2020/6/17 13:17
 */
@Mapper
public interface MenuDao {

    /**
     * 根据用户名获取权限集合
     * @param username
     * @return
     */
    List<String> listPerms(String username);
}
