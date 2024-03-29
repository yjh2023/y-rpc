package com.wind.yrpcspringbootstarter.annotation;

import com.wind.yrpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import com.wind.yrpcspringbootstarter.bootstrap.RpcInitBootstrap;
import com.wind.yrpcspringbootstarter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启动 Rpc 注解
 *
 * @author wind
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {
    /**
     * 需要启动 server
     *
     * @return
     */
    boolean needServer() default true;

}
