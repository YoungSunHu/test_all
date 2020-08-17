package com.hqy.netty;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel {
    public static void main(String[] args) throws IOException {

        String str = "Hello";
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file.txt");

        //通过fileOutput获取对应的 FileChannel
        //FileChannel的真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入ByteBuffer
        byteBuffer.put(str.getBytes());

        //bytebuffer进行反转,进行读取操作
        byteBuffer.flip();

        //将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
}
