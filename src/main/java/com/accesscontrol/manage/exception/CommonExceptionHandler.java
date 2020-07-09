package com.accesscontrol.manage.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: Lyf
 * @Date: 2020/2/27 17:09
 * @Description:
 */
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView handleAuthorizationException(AuthorizationException e){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("sys/error401");
        return mv;
    }
}
