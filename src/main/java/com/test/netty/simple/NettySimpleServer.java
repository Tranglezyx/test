package com.test.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author trangle
 */
public class NettySimpleServer {

    public static void main(String[] args) throws InterruptedException {
        // EventLoopGroup默认的线程数量是核心数量 * 2
        // 默认使用轮训方式接收处理
        // 只处理accept连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 处理真正的任务
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务器启动对象，可以设置启动参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置两个线程组
            serverBootstrap.group(bossGroup, workerGroup)
                    // 设置服务器的通道类型
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列等待连接的个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 给workerGroup的EventLoop设置对应的管道（PipeLine)设置处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 创建一个通道测试对象
                        // 给PipeLine设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettySimpleServerHandler());
                        }
                    });
            System.out.println(" --- 服务器准备OK --- ");

            // 绑定端口并且同步
            ChannelFuture channelFuture = serverBootstrap.bind(9090).sync();
            // 增加监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("绑定端口成功");
                    } else {
                        System.out.println("绑定端口失败");
                    }
                }
            });
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
