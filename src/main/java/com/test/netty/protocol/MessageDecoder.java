package com.test.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author trangle
 */
public class MessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("-- 解码 --");
        // 将二进制字节码转成具体数据对象
        int length = byteBuf.readInt();
        byte[] content = new byte[length];
        byteBuf.readBytes(content);
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLength(length);
        messageProtocol.setContent(content);
        list.add(messageProtocol);
    }
}
