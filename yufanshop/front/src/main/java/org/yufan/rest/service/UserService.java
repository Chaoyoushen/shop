package org.yufan.rest.service;

import org.yufan.bean.User;

public interface UserService {
    public User getUserByToken(String token);
}
