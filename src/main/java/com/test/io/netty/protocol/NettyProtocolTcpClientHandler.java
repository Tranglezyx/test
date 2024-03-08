package com.test.io.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author trangle
 * <p>
 * 客户端handler类，处理与服务器的相关事件
 */
public class NettyProtocolTcpClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    /**
     * 当通道有读取事件时会触发
     *
     * @param ctx
     * @param messageProtocol
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol messageProtocol) throws Exception {
        System.out.println("服务器IP地址 >> " + ctx.channel().remoteAddress() + "服务端发送的消息 >> " + new String(messageProtocol.getContent(), "UTF-8"));
    }

    /**
     * 当通道准备好了就绪时就会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 向服务器发送消息
        // 模拟TCP协议拆包发送，服务端接收处理数据可能是多次也可能是一次
        for (int i = 0; i < 10; i++) {
            String str = UUID.randomUUID().toString() + " ";
            MessageProtocol msg = new MessageProtocol();
            msg.setContent(str.toString().getBytes());
            msg.setLength(str.length());
            ctx.writeAndFlush(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常 >>> " + cause.getMessage());
        ctx.close();
    }
}
