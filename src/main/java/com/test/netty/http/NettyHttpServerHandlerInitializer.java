package com.test.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author trangle
 */
public class NettyHttpServerHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 加入netty提供的处理http的httpServerCodec，解码器
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 增加自定义的handler
        pipeline.addLast("httpServerHandler", new NettyHttpServerHandler());
    }
}
