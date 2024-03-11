package com.wind.yrpc.provider;

import com.wind.yrpc.common.service.UserService;
import com.wind.yrpc.registry.LocalRegistry;
import com.wind.yrpc.server.VertxHttpServer;

/**
 * 简易版服务提供者示例
 *
 * @author wind
 */
public class YRpcProvider {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        // 启动 web 服务
        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8080);

    }
}
