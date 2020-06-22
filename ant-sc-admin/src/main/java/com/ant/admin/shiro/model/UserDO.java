package com.ant.admin.shiro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: 用户DO
 * @Author: Liu
 * @Date: 2020/6/15 09:17
 */
@Getter
@Setter
@ToString
public class UserDO implements Serializable {

    private static final long serialVersionUID = -3644026629302427859L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
