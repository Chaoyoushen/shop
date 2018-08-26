package org.yufan.sso.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.bean.User;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.exception.MyException;
import org.yufan.sso.service.JedisService;
import org.yufan.sso.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/sso")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JedisService jedisService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Result> register(@Valid User user, BindingResult bindingResult)throws MyException {

        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> list= new ArrayList<>();
            for (ObjectError error:errors) {
                String msg=error.getDefaultMessage();
                list.add(msg);
            }
            String message =StringUtils.join(list,",");
            return ResponseEntity.ok(ResultUtils.buildFail(105,message));
        }

        return ResponseEntity.ok(userService.register(user));
    }


    @RequestMapping("/login")
    @ResponseBody
    public ResponseEntity<Result> login(User user){

        return ResponseEntity.ok(userService.login(user));
    }

    @RequestMapping("/check")
    @ResponseBody
    public Result check(String token){
        return userService.check(token);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result logout(String token){
        return userService.logout(token);
    }


}
