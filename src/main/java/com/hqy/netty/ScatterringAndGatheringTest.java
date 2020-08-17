package com.hqy.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * sacttering: 将数据写入到buffer时,可以采用buffer数组,依次写入 [分散]
 * Gathering: 从buuffer中的读取数据时,可以采用buffer数组,依次读
 */
public class ScatterringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        //使用ServiceSocktetChannel 和 SocketChannel
        ServerSocketChannel channel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        System.out.println("程序已启动!");
        //绑定端口到socket并启动
        channel.socket().bind(inetSocketAddress);

        //创建一个buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);


        //等待客户端连接
        SocketChannel socketChannel = channel.accept();
        int messageLength = 8;//假定从客户端读取8个字节
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(byteBuffers);//累计读取到字节数
                byteRead += l;
                System.out.println("byteRead=" + byteRead);
                //使用流打印
                Arrays.asList(byteBuffers).stream().map(buffer -> "position=" + buffer.position() + " limit=" + buffer.limit()).forEach(System.out::println);
            }
            //将所有的buffer反转
            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());
            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                socketChannel.write(byteBuffers);
                byteWrite += 1;
            }
            //将所有的buffer进行clear操作
            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWrite + ",messageLength=" + messageLength);
        }

    }
}
