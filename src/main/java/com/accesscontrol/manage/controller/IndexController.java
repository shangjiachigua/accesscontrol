package com.accesscontrol.manage.controller;

import com.accesscontrol.manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tianwenwen
 * @version 1.0.0
 * @ClassName IndexController.java
 * @createTime 2020年06月09日 09:19
 **/
@Controller
@RequestMapping("test")
public class IndexController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("test")
    public String toTest() {
        return "layuitest/layuitest";
    }

    @RequestMapping("test2")
    public String toTest1() {
        return "layuitest/authorization_add";
    }

    @RequestMapping("testImage")
    public String testImage() {
        return "layuitest/image";
    }

    @RequestMapping("tolayuitest")
    public String layuiTest() {
        return "layuitest/layuitest";
    }
}
