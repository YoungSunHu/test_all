package com.hqy.nettynio.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtcol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtcol msg) throws Exception {
        //接收到数据,并进行处理
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务器接收到信息如下:");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, CharsetUtil.UTF_8));
        System.out.println("服务器接受到消息包数量 =" + (++this.count));
        //回复消息
        String responseContent = UUID.randomUUID().toString();
        byte[] bytes = responseContent.getBytes("utf-8");
        //构建一个协议包
        MessageProtcol messageProtcol = new MessageProtcol();
        messageProtcol.setLen(bytes.length);
        messageProtcol.setContent(bytes);
        ctx.writeAndFlush(messageProtcol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
