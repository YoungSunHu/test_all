package com.hqy.nettynio.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {

    private int PORT;

    public MyServer(int PORT) {
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
                        ch.pipeline().addLast(new HttpServerCodec())
                                //是以块方式写,添加chunkedWriteHandler处理器
                                .addLast(new ChunkedWriteHandler())
                                //1.http数据在传输过程中是分段,HttpObjectAggregator,就是可以将多个分段聚合起来
                                //2.这就是为什么当浏览器发送大量数据时,就会发送多次http请求
                                .addLast(new HttpObjectAggregator(8192))
                                //1.websocket数据是以frame形式传递
                                //2.可以看到WebSocketFrame 下面有6个子类
                                //3.浏览器请求时 ws://localhost:7000/hello
                                //4.WebSocketServerProtocolHandler核心功能是将http协议升级为ws协议,报持长连接
                                .addLast(new WebSocketServerProtocolHandler("/hello"))
                                .addLast(new MyTestWebSocketFrameHandler());
                    }
                });
        System.out.println("netty 服务器启动!");
        ChannelFuture channelFuture = bootstrap.bind(PORT).sync();
    }

    public static void main(String[] args) throws InterruptedException {
        MyServer myServer = new MyServer(7000);
        myServer.run();
    }
}
