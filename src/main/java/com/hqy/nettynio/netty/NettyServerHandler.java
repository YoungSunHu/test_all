package com.hqy.nettynio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 1.自定义Handler需要继承netty的HandlerAdapter
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据
    /*
        ChannelHandlerContext 上下文对象,含有 pipeline,channel
        Object msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //加入这里有一个非常耗时的业务->异步执行->提交该channel 对应的NIOEventLoop的taskQueue中
        //Thread.sleep(10 * 1000);
        //1.解决方案 用户自定义的普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("server ctx=");
        //将msg转成ByteBuffer
        //ByteBuf是由netty提供
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送消息是:" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址" + ctx.channel().remoteAddress());
        super.channelRead(ctx, msg);
    }

    /**
     * 数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //write与flush两个方法的合并
        //数据写入缓存并刷新
        //一般讲,我们对这个发送的
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,客户端!", CharsetUtil.UTF_8));
    }


    /**
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
