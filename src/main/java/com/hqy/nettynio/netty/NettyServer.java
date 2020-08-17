package com.hqy.nettynio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组bossgroup wokergroup
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup wokerGroup = new NioEventLoopGroup();

        //创建服务器端的启动对象 配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();

        //使用链式编程进行设置
        bootstrap.group(boosGroup, wokerGroup)
                .channel(NioServerSocketChannel.class)//使用NioSocketChannel 作为服务器通道实现
                .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true)//保持队列连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    //创建一个通道测试对象
                    //给pipeline 设置处理器
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });//给我们的wokerGroup的EventLoop对应的管道设置处理器

        System.out.println("server is ready");

        //绑定端口并且同步处理 返回一个channelFuture对象
        //启动服务器
        ChannelFuture channelFuture = bootstrap.bind(6668).sync();

        //对关闭通道进行监听
        channelFuture.channel().closeFuture();

    }
}
