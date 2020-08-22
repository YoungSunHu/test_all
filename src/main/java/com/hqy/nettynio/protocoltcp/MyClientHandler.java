package com.hqy.nettynio.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtcol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用十次客户端发送10条数据 "今天天气冷,吃火锅!"
        for (int i = 0; i < 5; i++) {
            String msg = "今天天气冷,吃火锅!";
            byte[] content = msg.getBytes(CharsetUtil.UTF_8);
            int len = content.length;
            //创建协议包
            MessageProtcol messageProtcol = new MessageProtcol();
            messageProtcol.setLen(len);
            messageProtcol.setContent(content);
            ctx.writeAndFlush(messageProtcol);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtcol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.printf("客户端接收消息:长度:%d,内容%s", len, new String(content, "utf-8"));
        System.out.println("客户端接收消息数量:" + (++this.count));
    }
}
