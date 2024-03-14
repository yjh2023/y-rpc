package com.wind.yrpc.consumer;

import com.wind.yrpc.common.model.User;
import com.wind.yrpc.common.service.UserService;
import com.wind.yrpc.proxy.ServiceProxyFactory;

/**
 * 扩展版服务消费者
 *
 * @author wind
 */
public class YRpcCoreConsumer {
    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setUsername("wind");
        // 调用
        User resUser = userService.getUser(user);
        if (resUser != null) {
            System.out.println(resUser.getUsername());
        } else {
            System.out.println("user == null");
        }
    }
}
