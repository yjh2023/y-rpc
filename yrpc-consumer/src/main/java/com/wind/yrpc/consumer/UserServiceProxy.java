package com.wind.yrpc.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wind.yrpc.common.model.User;
import com.wind.yrpc.common.service.UserService;
import com.wind.yrpc.model.RpcRequest;
import com.wind.yrpc.model.RpcResponse;
import com.wind.yrpc.serializer.JdkSerializer;
import com.wind.yrpc.serializer.Serializer;

import java.io.IOException;

/**
 * 用户服务静态代理
 *
 * @author wind
 */
public class UserServiceProxy implements UserService{
    public User getUser(User user){
        // 指定一个序列化器
        final Serializer serializer = new JdkSerializer();
        // 构建 RPC 请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求
            // todo 这里的地址被硬编码了（可通过注册中心和服务发现机制解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return (User) rpcResponse.getData();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
