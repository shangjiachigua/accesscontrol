package com.accesscontrol.manage.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * SYS_MENU
 * @author 
 */
@Data
public class SysMenu implements Serializable {
    /**
     * 主键id
     */
    private String menuId;

    /**
     * 父级菜单id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单url
     */
    private String href;

    /**
     * 类型（0.目录 1.菜单 2.功能（按钮））
     */
    private String type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private BigDecimal orderNum;

    /**
     * 授权(多个用逗号分隔)
     */
    private String perms;

    /**
     * 呈现方式
     */
    private String target;

    /**
     * 主菜单
     */
    private String mainMenu;

    private static final long serialVersionUID = 1L;
}