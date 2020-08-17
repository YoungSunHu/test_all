package com.hqy.nettynio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.connect(new InetSocketAddress("127.0.0.1", 7001));
        String fileName = "";

        //得到一个文件channel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        //准备发送
        long startTime = System.currentTimeMillis();

        //在linux下一个transferTo方法就可以
        //在windows下,异常最多传8m,超过需要分段传输
        long l = fileChannel.transferTo(0, fileChannel.size(), open);

        System.out.println("发送总的字节数=" + l + " 耗时:" + (System.currentTimeMillis() - startTime));

        fileChannel.close();
    }
}
