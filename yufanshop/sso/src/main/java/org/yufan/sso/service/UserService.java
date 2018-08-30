package org.yufan.sso.service;


import org.yufan.bean.User;
import org.yufan.common.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface UserService {


    public Result register(User user);

    public Result login(User user);

    public Result logout(String token);

    public Result check(String token);







}
