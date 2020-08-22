package com.hqy.nettynio.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder.decode被调用!");
        //需要将得到的二进制字节码->MessageProtovol 数据包(对象)
        int len = in.readInt();
        byte[] bytes = new byte[len];
        //读取数据放入字节数组
        in.readBytes(bytes);
        //封装协议包
        MessageProtcol messageProtcol = new MessageProtcol();
        messageProtcol.setContent(bytes);
        messageProtcol.setLen(len);

        out.add(messageProtcol);

    }


}
