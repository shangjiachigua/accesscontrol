package com.accesscontrol.manage.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Shiro 配置
 * <p>
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 */
@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {


        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置不会被拦截的链接 顺序判断
        //静态文件
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/laydate/**", "anon");
        filterChainDefinitionMap.put("/layer/**", "anon");

        //不需要拦截的url
        filterChainDefinitionMap.put("/login/*", "anon");
        filterChainDefinitionMap.put("/index/*", "anon");
        filterChainDefinitionMap.put("/welcome/*", "anon");

//        List<Map<String, Object>> resultList =authorityService.queryButtonAll();
//        for (Map<String, Object> map :resultList) {
//            filterChainDefinitionMap.put(map.get("d_actionUrl").toString().substring(2), "perms["+map.get("authorityCode")+"]");
//        }


//        filterChainDefinitionMap.put("/shop/shopHome", "authc,perms[5001]");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        /*filterChainDefinitionMap.put("/**", "authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/login/loginMain");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/welcome/jumpWelcomePage");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/errorPage");*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @RequiresAuthentication 使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。
     * @RequiresPermissions 当前Subject需要拥有某些特定的权限时，才能执行被该注解标注的方法。
     * 如果当前Subject不具有这样的权限，则方法不会被执行。
     * @RequiresRoles 当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。
     * 如果当天Subject不同时拥有所有指定角色，则方法不会执行还会抛出AuthorizationException异常。
     * @RequiresUser 当前Subject必须是应用的用户，才能访问或调用被该注解标注的类，实例，方法。
     * @RequiresGuest 使用该注解标注的类，实例，方法在访问或调用时，
     * 当前Subject可以是“gust”身份，不需要经过认证或者在原先的session中存在记录。
     * <p>
     * 默认匹配所有类
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     *
     * 功能描述: shiro注解生效
     *
     * @param: []
     * @return: org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @auther: tian
     * @date: 2020/4/24 11:23
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }


    /**
     * 重新命名cookie的名字
     * 不然会和默认Cookie名称冲突
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        //DefaultSecurityManager
        SimpleCookie simpleCookie = new SimpleCookie();
        //如果在Cookie中设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法读取到Cookie信息，这样能防止XSS×××。
        simpleCookie.setHttpOnly(true);
        simpleCookie.setName("SHRIOSESSIONID");
        simpleCookie.setMaxAge(86400000 * 3);
        return simpleCookie;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}