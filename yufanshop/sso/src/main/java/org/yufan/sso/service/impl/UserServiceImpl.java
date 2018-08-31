package org.yufan.sso.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.User;
import org.yufan.common.CookieUtil;
import org.yufan.common.JsonUtils;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.sso.mapper.UserMapper;
import org.yufan.sso.service.JedisService;
import org.yufan.sso.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
        user.setCount(new Long(0));
        userMapper.insertSelective(user);
        return ResultUtils.buildSuccess("register success");

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
        jedisService.set("USER_LOGIN" +token, tmp.getId().toString());
        jedisService.expire("USER_LOGIN" +token,60*60*2);
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("user_id", StringUtils.join(tmp.getId()));
        return ResultUtils.buildSuccess(map);
    }

    @Override
    public Result logout(String token) {
       String value= jedisService.get("USER_LOGIN" +token);
       if(value==null) return ResultUtils.buildFail(104,"has not login");
       jedisService.del("USER_LOGIN" +token);
       return ResultUtils.buildSuccess("logout success");
    }

    @Override
    public Result check(String token) {
        if(jedisService.get("USER_LOGIN" +token)!=null){
            return ResultUtils.buildSuccess(jedisService.get("USER_LOGIN" +token));
        }
        return ResultUtils.buildFail(301,null);
    }


    private User queryByUser(String username){
        User record=new User();
        record.setUsername(username);
        return userMapper.selectOne(record);
    }
}
