package com.accesscontrol.manage.service;

import com.accesscontrol.manage.mapper.SysMenuMapper;
import com.accesscontrol.manage.pojo.SysMenu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MenuService.java
 * @createTime 2020年06月09日 09:20
 **/

@Service
public class MenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;


    public List<Map<String, Object>> getMenuList(String userId) {
        List<Map<String, Object>> menuList = new ArrayList<>();
        Map<String, Object> menuMap = null;
        List<SysMenu> sysMenus = sysMenuMapper.getSysMenuList(userId);

        for (SysMenu sysMenu : sysMenus) {
            menuMap = new HashMap<>();
            menuMap.put("catalog", sysMenu.getTitle());
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.select("title","href").eq("parent_id", sysMenu.getMenuId()).orderByAsc("order_Num");
            menuMap.put("menu", sysMenuMapper.selectList(wrapper));
            menuList.add(menuMap);
        }

        return menuList;
    }
}
