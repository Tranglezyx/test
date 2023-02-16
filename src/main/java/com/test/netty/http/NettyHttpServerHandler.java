package com.test.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 用于具体处理消息处理，怎么处理客户端消息，怎么回复都在此类中处理
 *
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
        if (httpObject instanceof FullHttpRequest) {
            System.out.println("客户端地址 》》" + channelHandlerContext.channel().remoteAddress());

            ByteBuf byteBuf = ((FullHttpRequest) httpObject).content();
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String jsonStr = new String(bytes, "UTF-8");
            System.out.println("请求参数：" + jsonStr);
            // 回复信息给浏览器
            ByteBuf responseContent = Unpooled.copiedBuffer("已收到请求，你的请求内容是 --- " + jsonStr, CharsetUtil.UTF_8);
            // 构建一个http返回结果
            DefaultFullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, responseContent);
            // 构造返回头
            HttpHeaders headers = fullHttpResponse.headers();
            headers.set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            headers.set(HttpHeaderNames.CONTENT_LENGTH, responseContent.readableBytes());

            // 将构建好的数据返回
            channelHandlerContext.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        }
    }
}
