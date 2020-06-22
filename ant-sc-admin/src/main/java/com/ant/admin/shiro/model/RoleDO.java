package com.ant.admin.shiro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description: 角色DO
 * @Author: Liu
 * @Date: 2020/6/15 11:03
 */
@Getter
@Setter
@ToString
public class RoleDO implements Serializable {
    private static final long serialVersionUID = -6547237482012505750L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String roleSign;
    /**
     * 用户id
     */
    private Long userId;


}
