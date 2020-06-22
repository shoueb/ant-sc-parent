package com.ant.admin.shiro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 菜单DO
 * @Author:  Liu
 * @Date: 2020/6/17 13:15
 */
@Getter
@Setter
@ToString
public class MenuDO implements Serializable {
    private static final long serialVersionUID = -2540713436683457324L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 父级菜单id
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;
    /**
     * 类型 0：目录 1：菜单 2：按钮
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer orderNum;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
