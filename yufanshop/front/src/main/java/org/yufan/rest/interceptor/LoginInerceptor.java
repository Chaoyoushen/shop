package org.yufan.rest.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.yufan.bean.User;
import org.yufan.common.CookieUtil;
import org.yufan.rest.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInerceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    //在Handler执行之前执行 true:执行 false:不执行
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //首先判断用户是否登录(从cookie中取token)
        String token=CookieUtil.getById(httpServletRequest,"token");
        User user=userService.getUserByToken(token);
        if(null==user){
            httpServletResponse.sendRedirect("www.chaoyous.cn/login?redirect="+httpServletRequest.getRequestURI());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
