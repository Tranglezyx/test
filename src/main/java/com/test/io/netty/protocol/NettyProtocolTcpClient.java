package com.test.io.netty.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author trangle
 */
public class NettyProtocolTcpClient {

    public static void main(String[] args) throws InterruptedException {
        // 客户端只需要一个事件循环组
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            // 创建一个客户端启动对象
            Bootstrap bootstrap = new Bootstrap();

            // 设置相关参数
            // 设置事件组,也可以说是设置线程组
            bootstrap.group(eventLoopGroup)
                    // 设置客户端通道实现类（反射）
                    .channel(NioSocketChannel.class)
                    // 设置handler实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 加入编码器
                            socketChannel.pipeline().addLast(new MessageEncoder());
                            // 设置自己实现的handler类
                            socketChannel.pipeline().addLast(new NettyProtocolTcpClientHandler());
                        }
                    });
            System.out.println("客户端已启动完成 --- ");

            // 启动客户端连接服务端
            // 关于ChannelFuture要分析，设计Netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect("localhost",9090).sync();
            // 设置关闭通道时的监听动作
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
