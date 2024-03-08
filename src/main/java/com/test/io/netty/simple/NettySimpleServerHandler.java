package com.test.io.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author trangle
 * <p>
 * 自定义一个Handler，需要继承Netty规定好的某个适配器
 * 这样我们自定义的Handler才能被称为Handler
 */
public class NettySimpleServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象，包含了管道PipeLine，通道NioSocketChannel,连接地址
     * @param msg 客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 耗时处理，可以异步处理，可以提交到对应channel对应的NioEventLoop中的taskQueue中
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(" 三秒等待完毕  ---- ");
                ctx.writeAndFlush(Unpooled.copiedBuffer("服务端等待处理完成 >>> ", CharsetUtil.UTF_8));
                simpleHandler(ctx, (ByteBuf) msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 定时任务执行，第一个为Runable实现对象，第二个参数为时间，第三个为时间单位
        ctx.channel().eventLoop().schedule(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(" 三秒等待完毕  ---- ");
                ctx.writeAndFlush(Unpooled.copiedBuffer("服务端等待处理完成 >>> ", CharsetUtil.UTF_8));
                simpleHandler(ctx, (ByteBuf) msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, TimeUnit.SECONDS);
        // 简单处理
//        simpleHandler(ctx, (ByteBuf) msg);
    }

    private void simpleHandler(ChannelHandlerContext ctx, ByteBuf msg) {
        Channel channel = ctx.channel();
        // PipeLine本质是一个双向链表
        ChannelPipeline pipeline = ctx.pipeline();
        // ByteBuf是Netty特有的，不是NIO的
        ByteBuf byteBuf = msg;
        System.out.println("客户端IP地址 >> " + channel.remoteAddress() + "客户端发送的消息 >> " + byteBuf.toString(CharsetUtil.UTF_8));
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
