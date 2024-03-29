package com.wind.yrpcspringbootstarter.bootstrap;

import com.wind.yrpc.RpcApplication;
import com.wind.yrpc.config.RpcConfig;
import com.wind.yrpc.server.VertxHttpServer;
import com.wind.yrpcspringbootstarter.annotation.EnableRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Rpc 框架启动
 *
 * @author wind
 */
@Slf4j
public class RpcInitBootstrap implements ImportBeanDefinitionRegistrar {
    /**
     * Spring 初始化时执行，初始化 RPC 框架
     *
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 获取 EnableRpc 注解属性zh值
        boolean needServer = (boolean)importingClassMetadata.getAnnotationAttributes(EnableRpc.class.getName()).get("needServer");
        // RPC 框架初始化
        RpcApplication.init();
        // 获取全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        // 判断是否启动服务
        if(needServer){
            VertxHttpServer vertxHttpServer = new VertxHttpServer();
            vertxHttpServer.doStart(rpcConfig.getServerPort());
        }else {
            log.info("不启动 server");
        }
    }
}
