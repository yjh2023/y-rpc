package com.wind.yrpcspringbootstarter.annotation;

import com.wind.yrpc.constant.RpcConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务消费者注解（用于服务注入）
 *
 * @author wind
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcReference {
    /**
     * 服务接口类
     *
     * @return
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     *
     * @return
     */
    String serviceVersion() default RpcConstant.DEHAULT_SERVICE_VERSION;

    /**
     * 模拟调用
     *
     * @return
     */
    boolean mock() default false;
}
