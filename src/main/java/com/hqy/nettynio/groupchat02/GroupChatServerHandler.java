package com.hqy.nettynio.groupchat02;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组,管理所有的channel
    //GlobalEventExecutor全局事件执行器,是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //表示一旦连接建立,第一个被执行
    //将当前的channel加入到channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将客户加入聊天的信息推送给其他的在线的客户端
        //channelGroup.writeAndFlush 会自动遍历所有的channel
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入聊天!");
        channelGroup.add(channel);
    }

    //表示channel 处于活动状态,提示xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了!");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离线了!");
    }

    //断开连接,将某某客户离开信息推送会当前在线的客户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //每执行一次handlerRemoved channelGroup的数量会减少
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了\n");
        System.out.println("当前channelGroup size" + channelGroup.size());
    }

    //表示channel 处于活动状态,提示xx离线了
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取当前channel
        Channel channel = ctx.channel();
        channelGroup.forEach(
                ch -> {
                    if (ch != channel) {
                        ch.writeAndFlush("[客户]" + ch.remoteAddress() + " 发送了消息:" + msg + "\n");
                    } else {
                        ch.writeAndFlush("[自己发送了消息:]" + msg + "\n");
                    }
                }
        );
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.close();
    }
}
