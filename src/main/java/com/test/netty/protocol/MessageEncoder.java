package com.test.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author trangle
 */
public class MessageEncoder extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol, ByteBuf byteBuf) throws Exception {
        System.out.println("  ---  编码  ---  ");
        byteBuf.writeInt(messageProtocol.getLength());
        byteBuf.writeBytes(messageProtocol.getContent());
    }
}
