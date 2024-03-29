package com.wind.yrpcspringbootstarter.bootstrap;

import com.wind.yrpc.RpcApplication;
import com.wind.yrpc.config.RegistryConfig;
import com.wind.yrpc.config.RpcConfig;
import com.wind.yrpc.model.ServiceMetaInfo;
import com.wind.yrpc.registry.LocalRegistry;
import com.wind.yrpc.registry.Registry;
import com.wind.yrpc.registry.RegistryFactory;
import com.wind.yrpcspringbootstarter.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Rpc 服务提供者启动
 *
 * @author wind
 */
public class RpcProviderBootstrap implements BeanPostProcessor {
    /**
     * Bean 初始化后执行，注册服务
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        RpcService rpcService = beanClass.getAnnotation(RpcService.class);
        // 判断是否需要注册服务
        if(rpcService != null){
            // 获取服务基本信息
            Class<?> interfaceClass = rpcService.interfaceClass();
            // 默认值处理
            if(interfaceClass == void.class){
                interfaceClass = beanClass.getInterfaces()[0];
            }
            String serviceName = interfaceClass.getName();
            String serviceVersion = rpcService.serviceVersion();
            // 本地注册
            LocalRegistry.register(serviceName,beanClass);

            // 获取全局配置
            final RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(serviceVersion);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

            try {
                registry.registry(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + "服务注册失败" , e);
            }
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
