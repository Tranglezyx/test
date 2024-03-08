package com.test.io.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @author trangle
 */
public class NettyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("收到客户端消息 :" + textWebSocketFrame.text());
        // 回复客户端，浏览器
        channelHandlerContext.writeAndFlush(new TextWebSocketFrame("服务器当前时间: " + LocalDateTime.now() + " >> " + textWebSocketFrame.text()));
    }

    /**
     * 当web客户端连接后，就会触发方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端ws连接 --- " + ctx.channel().id().asLongText());
        System.out.println("客户端ws连接 --- " + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端ws关闭 --- " + ctx.channel().id().asLongText());
        System.out.println("客户端ws关闭 --- " + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常 --- ");
        ctx.channel().close();
    }
}
