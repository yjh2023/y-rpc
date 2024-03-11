package com.wind.yrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 *
 * @author wind
 */
public class LocalRegistry {
    /**
     * 注册信息存储
     */
    public static final Map<String,Class<?>> map = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 注册服务
     *
     * @param serviceName
     * @param implClass
     */
    public static void register(String serviceName,Class<?> implClass){
        map.put(serviceName,implClass);
    }

    /**
     * 获取服务
     *
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName){
        return map.get(serviceName);
    }

    /**
     * 移除服务
     *
     * @param serviceName
     */
    public static void remove(String serviceName){
        map.remove(serviceName);
    }
}
