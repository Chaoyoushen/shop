package org.yufan.rest.service.impl;

import org.springframework.stereotype.Service;
import org.yufan.bean.User;
import org.yufan.common.HttpClientUtil;
import org.yufan.common.JsonUtils;
import org.yufan.common.Result;
import org.yufan.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByToken(String token) {
        String json= HttpClientUtil.doGet("sso.chaoyous.cn/check?token="+token);
        Result result= JsonUtils.jsonToPojo(json,Result.class);
        if(result.getStatus()==200){
            User user=(User)result.getData();
            return user;
        }
        return null;
    }
}
