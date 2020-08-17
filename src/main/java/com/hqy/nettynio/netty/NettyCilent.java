package com.hqy.nettynio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyCilent {
    public static void main(String[] args) throws InterruptedException {
        //客户端需要一个事件循环组
        NioEventLoopGroup eventExecutors = null;

        try {
            //客户端需要一个事件循环组
            eventExecutors = new NioEventLoopGroup();

            //创建客户端的启动对象
            //客户端使用的不是 ServerBoostrap BootStrap
            Bootstrap bootstrap = new Bootstrap();

            //设置相关参数
            bootstrap.group(eventExecutors)//设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());//加入自己的处理器
                        }
                    });

            System.out.println("客户端准备完成!");

            //启动客户端去连接服务器端
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 6668).sync();

            //给关闭通道增加一个连接进行监听
            connect.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }


    }
}
