package com.hqy.nettynio.groupchat02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GroupChatGroupServer {
    private int PORT;

    public GroupChatGroupServer(int PORT) {
        this.PORT = PORT;
    }

    //编写run方法,处理客户端的请求
    public void run() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //向pipeline中加入解码器
                        ch.pipeline().addLast("StringDecoder", new StringDecoder())
                                .addLast("StringEncoder", new StringEncoder())
                                .addLast(new GroupChatServerHandler());
                    }
                });
        System.out.println("netty 服务器启动!");
        ChannelFuture channelFuture = bootstrap.bind(PORT).sync();

    }

    public static void main(String[] args) {
        GroupChatGroupServer groupChatGroupServer = new GroupChatGroupServer(7000);
        try {
            groupChatGroupServer.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
