package com.test.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author trangle
 * <p>
 * netty初始化器，初始化器中可以增加handler
 */
public class NettyHttpServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 加入netty提供的处理http的httpServerCodec，解码器
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 消息聚合器，http传送的请求中可能是一片片的消息，此时就需要聚合器聚拢
        pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024));
        // 增加自定义的handler
        pipeline.addLast("httpServerHandler", new NettyHttpServerHandler());
    }
}
