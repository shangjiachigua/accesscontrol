package com.accesscontrol.manage.shiro;


import com.accesscontrol.manage.common.Utils;
import com.accesscontrol.manage.pojo.SysUser;
import com.accesscontrol.manage.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser teller = (SysUser) principals.getPrimaryPrincipal();
        if (Utils.isNull(teller)) {
            return authorizationInfo;
        }
        //TODO
        /*Map<String, Object> params = new HashMap<>();
        params.put("tellerNo", teller.getTellerNo());

        //========权限配置============== 101:平台 102：区级 103：城管；104.居委
        Set<String> s = new HashSet<String>();

        List<Map<String, Object>> resultList =authorityService.queryButtonAuthority();
        for (Map<String, Object> map :resultList) {
            s.add(map.get("authorityCode")+"");
        }*/
        //s.add("C_01_M_01");
//        authorizationInfo.addRole(teller.getTellerNo());
        //authorizationInfo.addRole(teller.getTellerType());
        Set<String> s = new HashSet<String>();
        authorizationInfo.setStringPermissions(s);

        return authorizationInfo;
    }

    //身份认证，验证用户输入的账号和密码是否正确
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //获取用户的输入的账号.
        String userId = upToken.getUsername();
        String qryPassword = String.valueOf(upToken.getPassword());

        SysUser sysUser = userService.getUser(userId);

       /* if (Utils.isNotNull(teller)) {
//            Subject subject = SecurityUtils.getSubject();
//            String sessionIdNow = subject.getSession().getId().toString();
            //第二个参数 与token中的password进行对比，匹配上了就通过，匹配不上就报异常
            return new SimpleAuthenticationInfo(teller, qryPassword,getName() );
        } else {
            throw new UnknownAccountException("账户不存在!");
        }*/




        if (Utils.isNotNull(sysUser)) {
            // 获取数据库中的密码
            String passWord = sysUser.getPassWord();
            if (null == passWord || !passWord.equals(qryPassword)) {
                throw new AuthenticationException("密码错误");
            }
            return new SimpleAuthenticationInfo(sysUser, qryPassword, getName());
        } else {
            throw new UnknownAccountException("账户不存在!");
        }
    }
}
