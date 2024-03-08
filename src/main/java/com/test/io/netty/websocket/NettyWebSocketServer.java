package com.test.io.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author trangle
 */
public class NettyWebSocketServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 处理真正的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        // 基于Http协议，需要使用Http的编码解码器
                        pipeline.addLast(new HttpServerCodec());
                        // 是以块的方式填写，添加处理器
                        pipeline.addLast(new ChunkedWriteHandler());
                        // http在数据传输中是分段的，HttpObjectAggregator支持将多个段聚合
                        // http在发送数据量过大时，就会发出多次请求
                        pipeline.addLast(new HttpObjectAggregator(8192));
                        // websocket数据是以帧（frame）的形式传递的
                        // 浏览器请求时： ws://localhost:9090/hello  表示请求的uri
                        // WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                        // 自定义
                        pipeline.addLast(new NettyWebSocketHandler());
                    }
                });

        ChannelFuture channelFuture = serverBootstrap.bind(9090).sync();
        channelFuture.channel().closeFuture().sync();

    }
}
