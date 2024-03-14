package com.wind.yrpc.config;

import lombok.Data;

/**
 * RPC 框架配置
 *
 * @author wind
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "y-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机号
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
}
