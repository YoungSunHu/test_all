package com.hqy.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter
 * HttpObject 客户端和服务器端相互通信的数据封装成HttpObject
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //channelRead0 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        //判断 msg 是不是一个 httprequest 请求
        if (msg instanceof HttpRequest) {

            System.out.println("pipeline hashCode:" + ctx.pipeline().hashCode() + " TestHandlerHashCode:" + this.hashCode());


            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            //
            HttpRequest request = (HttpRequest) msg;
            //获取uri
            URI uri = new URI(request.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("/favicon.ico 路径拦截");
                return;
            }
            //回复信息给浏览器[http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器!", CharsetUtil.UTF_8);

            //构造一个http的相应,即HttpResponse
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将构建好的response返回
            ctx.writeAndFlush(response);
        }
    }
}
