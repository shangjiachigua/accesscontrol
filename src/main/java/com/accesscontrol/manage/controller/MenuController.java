package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.pojo.SysUser;
import com.accesscontrol.manage.service.MenuService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName MenuController.java
 * @createTime 2020年06月12日 16:54
 **/
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @RequestMapping("loadMenu")
    @ResponseBody
    public String loadMenu() {
        JSONObject result = new JSONObject();
        try {
            SysUser sysUser = getSysUser();
            System.out.println("sysUser.getUserName() = " + sysUser.getUserName());
            List<Map<String, Object>> menuList = menuService.getMenuList(sysUser.getUserId());
            result.put("code", "0000");
            result.put("data", menuList);
            result.put("userName",sysUser.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            result.put("code", "9999");
            result.put("msg", "网络异常");
        }
        return result.toString();
    }
}
