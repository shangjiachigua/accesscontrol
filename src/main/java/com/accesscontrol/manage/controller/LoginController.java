package com.accesscontrol.manage.controller;


import com.accesscontrol.manage.service.UserService;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserService userService;

    private static HashMap cacheMap = new HashMap();

    //跳转登录页
    @RequestMapping(value = "/toLoginPage")
    public String login() {
        return "login";
    }


    //登录
    @RequestMapping(value = "/confirmLogin")
    @ResponseBody
    public String confirmLogin(String userId, String password, HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userId, password);
            currentUser.login(token);//
            result.put("code", "0000");
            result.put("msg", "登录成功");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.toString());
            result.put("code", "9999");
            result.put("msg", "登陆失败");
        }
        return result.toString();
    }

    //登录成功跳转页面
    @RequestMapping(value = "/toIndexPage")
    public String index() {
        return "index";
    }

}
