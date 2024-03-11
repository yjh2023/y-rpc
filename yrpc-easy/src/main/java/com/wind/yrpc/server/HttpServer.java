package com.wind.yrpc.server;

/**
 * HTTP 服务器接口
 *
 * @author wind
 */
public interface HttpServer {
    /**
     * 启动服务器
     *
     * @param port
     */
    void doStart(int port);
}
