package org.yufan.sso.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.User;
import org.yufan.common.JsonUtils;
import org.yufan.sso.mapper.UserMapper;
import org.yufan.sso.service.JedisService;
import org.yufan.sso.service.UserService;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisService jedisService;



    @Override
    public String register(User user){
        if(queryByUser(user.getUsername())!=null){
            return "用户名已注册";
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreatedtime(new Date());
        user.setUpdatedtime(user.getCreatedtime());

        userMapper.insertSelective(user);
        return "success";

    }

    @Override
    public String login(User user) {
        String token= DigestUtils.md5Hex(new Date().toString()+user.getUsername());
        jedisService.set("USER_LOGIN" +token, JsonUtils.objectToJson(user));
        jedisService.expire("USER_LOGIN" +token,60*60*2);
        return  token;
    }

    @Override
    public String queryUserByToken(String token) {
        return null;
    }

    @Override
    public User queryByUser(String username){
        User record=new User();
        record.setUsername(username);
        return userMapper.selectOne(record);
    }
}
