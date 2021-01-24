package com.test.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author trangle
 * <p>
 * 自定义一个Handler，需要继承Netty规定好的某个适配器
 * 这样我们自定义的Handler才能被称为Handler
 */
public class NettyProtocolTcpServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol messageProtocol) throws Exception {
        int len = messageProtocol.getLength();
        byte[] content = messageProtocol.getContent();

        System.out.println("客户端IP地址 >> " + ctx.channel().remoteAddress() + "客户端发送的消息 >> " + new String(messageProtocol.getContent(), "UTF-8"));
    }

    /**
     * 数据读取完毕返回的消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器通道消息处理完毕 >>> ");
        // write + flush
        // 将数据写入到缓存并刷新
        // 一般要对发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务器收到消息 --- ", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常类，一般出现异常需要关闭通道
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
