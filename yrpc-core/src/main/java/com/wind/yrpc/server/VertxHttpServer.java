package com.wind.yrpc.server;


import io.vertx.core.Vertx;

/**
 * Vertx HTTP 服务器
 *
 * @author wind
 */
public class VertxHttpServer implements HttpServer{
    /**
     * 启动服务
     *
     * @param port
     */
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        server.requestHandler(new HttpServerHandler());

        server.listen(port,result -> {
            if(result.succeeded()){
                System.out.println("Server is now listening on port " + port);
            }else {
                System.out.println("Failed to start server: " + result.cause());
            }
        });


    }
}
