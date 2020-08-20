package com.hqy.nettynio.inboudHandlerAndOutboundHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        //long类型8个字节
        //先判断字节数
        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }

    }
}
