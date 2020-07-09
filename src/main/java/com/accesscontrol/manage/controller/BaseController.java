package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.pojo.SysUser;
import org.apache.shiro.SecurityUtils;

public class BaseController {


    public SysUser getSysUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

}
