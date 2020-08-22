package com.hqy.nettynio.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup, wokerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler())
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //1.加入一个netty 提供IdleStateHandler 处理空闲状态的处理器
                        //2.long readerIdleTime : 标识多长时间没有读,就会发送心跳检测包
                        //3.long writerIdleTime : 标识多长时间没有写,就会发送心跳检测包
                        //4.long allIdleTime : 标识多长时间没有读或写,就会发送心跳检测包
                        //IdleStateHandler 被触发后,就会传递给管道的下一个handler去处理 通过调用或者回调下一个handler的userEventTriggered,去处理
                        pipeline.addLast(new IdleStateHandler(3, 5, 7, TimeUnit.SECONDS));
                        //加入一个对空闲检测进一步
                        pipeline.addLast(new MyServerHandler());
                    }
                });
        ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
