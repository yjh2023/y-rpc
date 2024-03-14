package com.wind.yrpc.provider;

import com.wind.yrpc.RpcApplication;
import com.wind.yrpc.common.service.UserService;
import com.wind.yrpc.registry.LocalRegistry;
import com.wind.yrpc.server.HttpServer;
import com.wind.yrpc.server.VertxHttpServer;
/**
 * 扩展版服务提供者
 *
 * @author wind
 */
public class YRpcCoreProvider {
    public static void main(String[] args) {
        RpcApplication.init();

        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        HttpServer httpServer = new VertxHttpServer();

        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
