package com.wind.yrpc.provider;

import com.wind.yrpc.RpcApplication;
import com.wind.yrpc.common.service.UserService;
import com.wind.yrpc.config.RegistryConfig;
import com.wind.yrpc.config.RpcConfig;
import com.wind.yrpc.model.ServiceMetaInfo;
import com.wind.yrpc.registry.LocalRegistry;
import com.wind.yrpc.registry.Registry;
import com.wind.yrpc.registry.RegistryFactory;
import com.wind.yrpc.server.HttpServer;
import com.wind.yrpc.server.VertxHttpServer;
/**
 * 扩展版服务提供者
 *
 * @author wind
 */
public class YRpcCoreProvider {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        String serviceName = UserService.class.getName();
        // 注册本地服务
        LocalRegistry.register(serviceName,UserServiceImpl.class);
        // 获取全局配置
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 注册服务到注册中心
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.registry(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(rpcConfig.getServerPort());
    }
}
