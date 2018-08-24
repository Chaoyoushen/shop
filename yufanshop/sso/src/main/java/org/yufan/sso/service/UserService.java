package org.yufan.sso.service;


import org.yufan.bean.User;
import org.yufan.exception.MyException;

public interface UserService {


    public String register(User user);

    public String login(User user);

    public String queryUserByToken(String token);

    public User queryByUser(String username);




}
