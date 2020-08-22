package com.hqy.nettynio.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {
    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).handler(new MyClientInitializer());
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 7000);
            connect.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
