package com.hqy.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //得到一个Selector对象
        Selector selector = Selector.open();

        //绑定一个端口6666,在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //把serverSocketChannel注册到selector关心事件为OP_ACCEPT
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            //等待1秒,如果没有事件发生,返回
            if (selector.select(1000) == 0) {
                //没有事件发生
                System.out.println("服务器等待了1秒,无连接");
                continue;
            }
            //返回>0,就获取到selectionKey集合
            //如果返回的>0,表示已经获取到关注的事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历Set<SelectionKey>,使用迭代器遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = iterator.next();
                //根据key对应的通道发生的事件做相应的处理
                if (key.isAcceptable()) {
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将socketChannel注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()) {
                    //发生OP_READ事件
                    //通过key反向获取channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端" + new String(buffer.array()));
                }
                //手动从集合中移除当前的selectionKey 防止重复操作
                iterator.remove();
            }
        }
    }
}
