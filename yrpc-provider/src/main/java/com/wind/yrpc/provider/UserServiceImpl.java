package com.wind.yrpc.provider;

import com.wind.yrpc.common.model.User;
import com.wind.yrpc.common.service.UserService;

/**
 * 用户服务实现类
 */
public class UserServiceImpl implements UserService {
    public User getUser(User user) {
        System.out.println("用户名：" + user.getUsername());
        return user;
    }
}
