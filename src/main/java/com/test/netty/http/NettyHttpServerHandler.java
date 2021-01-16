package com.test.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author trangle
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 读取客户端数据
     *
     * @param channelHandlerContext 上下文，可以拿到channel，pipeLine
     * @param httpObject            客户端与服务器端发送的数据的封装对象
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            System.out.println("客户端地址 》》" + channelHandlerContext.channel().remoteAddress());
            // 回复信息给浏览器
            ByteBuf content = Unpooled.copiedBuffer("这里是服务器 --- ", CharsetUtil.UTF_8);

            // 构建一个http返回结果
            DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            // 构造返回头
            HttpHeaders headers = fullHttpResponse.headers();
            headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            headers.set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            // 将构建好的数据返回
            channelHandlerContext.writeAndFlush(fullHttpResponse);
        }
    }
}
