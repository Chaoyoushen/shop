package org.yufan.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yufan.bean.User;
import org.yufan.common.HttpClientUtil;
import org.yufan.common.JsonUtils;
import org.yufan.common.Result;
import org.yufan.rest.mapper.UserMapper;
import org.yufan.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByToken(String token) {
        String json= HttpClientUtil.doGet("http://sso.chaoyous.cn/check?token="+token);
        Result result= JsonUtils.jsonToPojo(json,Result.class);
        if(result.getStatus()==200){
            String id=(String) result.getData();
            Long user_id=Long.parseLong(id);
            User user=userMapper.selectByPrimaryKey(user_id);
            return user;
        }
        return null;
    }
}
