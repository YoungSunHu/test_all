package com.hqy.nettynio.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMessageEncoder extends MessageToByteEncoder<MessageProtcol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtcol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode 方法被调用!");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}
