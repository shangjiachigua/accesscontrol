package com.accesscontrol.manage.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * SYS_USER
 * @author 
 */
@Data
public class SysUser implements Serializable {
    /**
     * 用户id（主键）
     */
    private String userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态（1.正常 9.删除）
     */
    private String status;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 创建人
     */
    private String createUser;

    private static final long serialVersionUID = 1L;
}