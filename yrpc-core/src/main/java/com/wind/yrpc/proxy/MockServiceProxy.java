package com.wind.yrpc.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服务代理（JDK 动态代理）
 *
 * @author wind
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}",method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成指定类型的默认值对象
     * @param methodReturnType
     * @return
     */
    private Object getDefaultObject(Class<?> methodReturnType) {
        // 基本类型
        if(methodReturnType.isPrimitive()){
            if(methodReturnType == boolean.class){
                return false;
            } else if (methodReturnType == short.class) {
                return (short) 0;
            } else if (methodReturnType == int.class) {
                return 0;
            } else if (methodReturnType == long.class) {
                return 0L;
            }
        }
        // 对象类型
        return null;
    }
}
