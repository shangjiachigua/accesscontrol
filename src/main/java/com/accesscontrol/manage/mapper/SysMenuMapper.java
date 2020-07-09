package com.accesscontrol.manage.mapper;

import com.accesscontrol.manage.pojo.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> getSysMenuList(@Param("userId") String userId);
}