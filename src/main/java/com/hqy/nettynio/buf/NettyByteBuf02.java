package com.hqy.nettynio.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf02 {
    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World! 你好世界", CharsetUtil.UTF_8);

        //使用相关的方法
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            for (byte b : content) {
                System.out.println(b);
            }
            System.out.println(new String(content, CharsetUtil.UTF_8));
        }

    }
}
