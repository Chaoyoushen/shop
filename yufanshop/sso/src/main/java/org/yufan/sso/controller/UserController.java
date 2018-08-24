package org.yufan.sso.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.bean.User;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.sso.service.JedisService;
import org.yufan.sso.service.UserService;

import javax.validation.Valid;



@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisService jedisService;

    @RequestMapping("/register")
    @ResponseBody
    public Result register(@Valid User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String message =bindingResult.toString();
            return ResultUtils.buildFail(100,message);
        }
        userService.register(user);
        return ResultUtils.buildSuccess();
    }


    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password){
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return "name or password null";
        }
        User record =new User();
        record.setUsername(username);
        record.setPassword(password);
        User user =userService.queryByUser(username);
        if(user==null){
            return "用户名或密码错误";
        }
        return userService.login(user);
    }

    @RequestMapping("/check")
    @ResponseBody
    public String check(String token){
        if(jedisService.get("USER_LOGIN"+token)==null){
            return "please re-login";
        }else {
            return "you are login-in";
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(String token){
        if(jedisService.get("USER_LOGIN"+token)==null){
            return "no information";
        }else{
            jedisService.del("USER_LOGIN"+token);
            return "success";
        }
    }


}
