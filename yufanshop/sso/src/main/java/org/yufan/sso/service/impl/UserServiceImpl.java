package org.yufan.sso.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.User;
import org.yufan.common.JsonUtils;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
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
    public Result register(User user){
        if(user==null){
            return ResultUtils.buildFail(101,"empty");
        }
        if(queryByUser(user.getUsername())!=null){
            return ResultUtils.buildFail(103,"exist name");
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        userMapper.insertSelective(user);
        return ResultUtils.buildSuccess(200,"ok",null);

    }

    @Override
    public Result login(User user) {
        if(user==null||user.getUsername()==null||user.getPassword()==null){
            return ResultUtils.buildFail(101,"empty");
        }
        User tmp=queryByUser(user.getUsername());
        if(tmp==null||!DigestUtils.md5Hex(user.getPassword()).equals(tmp.getPassword())){
            return ResultUtils.buildFail(102,"wrong name or password");
        }
        String token= DigestUtils.md5Hex(new Date().toString()+user.getUsername());
        jedisService.set("USER_LOGIN" +token, JsonUtils.objectToJson(user));
        jedisService.expire("USER_LOGIN" +token,60*60*2);
        return ResultUtils.buildSuccess(200,null,token);
    }

    @Override
    public Result logout(String token) {
       String value= jedisService.get("USER_LOGIN" +token);
       if(value==null) return ResultUtils.buildFail(104,"has not login");
       jedisService.del("USER_LOGIN" +token);
       return ResultUtils.buildSuccess(200,"has login",null);
    }

    @Override
    public Result check(String token) {
        if(jedisService.get("USER_LOGIN" +token)!=null){
            return ResultUtils.buildSuccess(200,"has login",null);
        }
        return ResultUtils.buildFail(106,"need re-log");
    }


    private User queryByUser(String username){
        User record=new User();
        record.setUsername(username);
        return userMapper.selectOne(record);
    }
}