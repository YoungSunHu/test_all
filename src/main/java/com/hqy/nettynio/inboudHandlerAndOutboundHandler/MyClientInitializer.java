package com.hqy.nettynio.inboudHandlerAndOutboundHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.nio.channels.Pipe;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //加入一个出站handler
        pipeline.addLast(new MyLongToByteEncoder());
        //出站的handler,进行编码
        pipeline.addLast(new MyClientHandler());
    }
}
