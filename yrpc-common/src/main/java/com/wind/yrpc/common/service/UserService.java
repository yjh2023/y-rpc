package com.wind.yrpc.common.service;

import com.wind.yrpc.common.model.User;

/**
 * 用户服务
 *
 * @author wind
 */
public interface UserService {
    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);
}
