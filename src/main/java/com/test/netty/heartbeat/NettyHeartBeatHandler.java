package com.test.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author trangle
 */
public class NettyHeartBeatHandler extends ChannelInboundHandlerAdapter {

    /**
     * 对事件进行处理
     *
     * @param ctx 上下文
     * @param evt 事件
     * @throws Exception 异常
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            String eventType = null;
            switch (idleStateEvent.state()) {
                case READER_IDLE: {
                    eventType = "读空闲";
                    break;
                }
                case WRITER_IDLE: {
                    eventType = "写空闲";
                    break;
                }
                case ALL_IDLE: {
                    eventType = "读写空闲";
                    break;
                }
                default: {
                    break;
                }
            }
            System.out.println(ctx.channel().remoteAddress() + "事件发生:" + eventType);
            ctx.channel().close();
        }
    }
}
