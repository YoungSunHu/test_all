package com.hqy.nettynio.groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer {
    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PROT = 6667;

    public GroupChatServer() {
        try {
            //得到选择器
            selector = Selector.open();
            //ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PROT));
            //将该channel注册到selector
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            //循环处理
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    //遍历得到的selectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        //监听到accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            //将该channel注册到selector中
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " 上线");
                        }
                        if (key.isReadable()) {
                            //通道发生read事件,即通道是可读状态
                            //处理读
                            readData(key);
                        }
                        //当前的key删除,防止重复操作
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待连接....");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    //读取客户端消息
    public void readData(SelectionKey selectionKey) {
        //定义一个SocketChannel
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) selectionKey.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from client : " + msg);
                //向其他的客户端转发消息()
                sendInfoToOtherClient(msg, socketChannel);
            }
        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + "离线了!");
                //取消注册
                selectionKey.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //转发消息给其他客户(通道)
    private void sendInfoToOtherClient(String msg, SocketChannel socketChannel) {
        System.out.println("服务器转发消息...");
        //遍历所有注册到selector上的SocketChannel,并排除自己的通道
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            //通过key 取出通道channel
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != socketChannel) {
                //转型
                SocketChannel destChannel = (SocketChannel) targetChannel;
                //将msg存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer的数据写入通道的
                try {
                    destChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        //创建一个服务器对象
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }

}
