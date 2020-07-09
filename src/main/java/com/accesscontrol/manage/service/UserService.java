package com.accesscontrol.manage.service;

import com.accesscontrol.manage.mapper.SysUserMapper;
import com.accesscontrol.manage.pojo.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName UserService.java
 * @createTime 2020年06月09日 09:02
 **/
@Service
public class UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser getUser(String userId) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        SysUser whereUser = new SysUser();
        whereUser.setUserId(userId);
        queryWrapper.setEntity(whereUser);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
