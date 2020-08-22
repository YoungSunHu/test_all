package com.hqy.nettynio.groupchat02;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {

    private final String HOST;

    private final int PORT;

    public GroupChatClient(String HOST, int PORT) {
        this.HOST = HOST;
        this.PORT = PORT;
    }

    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new StringDecoder())
                                    .addLast("encoder", new StringEncoder())
                                    //自定义业务处理handler
                                    .addLast(new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(HOST, PORT).sync();
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            System.out.println("客户端你启动完毕!");
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                //通过channel 发送至服务器端
                channel.writeAndFlush(s);
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new GroupChatClient("127.0.0.1", 7000).run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
