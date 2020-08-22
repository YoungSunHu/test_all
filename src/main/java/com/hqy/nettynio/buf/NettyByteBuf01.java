package com.hqy.nettynio.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf01 {
    public static void main(String[] args) {

        //创建一个对象 该对象包含一个数组arr,是一个byte[10]
        //buffer部署要反转
        //底层维护了readerindex和writeindexx
        ByteBuf buffer1 = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            buffer1.writeByte(i);
        }
        System.out.println("capacity = " + buffer1.capacity());

    }
}
