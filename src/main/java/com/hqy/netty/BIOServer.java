package com.hqy.netty;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {

        //线程池机制
        ///思路
        //1.创建一个线程池
        //2.如果有客户端连接,就创建一个线程,与之通信
        ExecutorService service = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);

        while (true) {
            //监听,等待客户端连接
            Socket accept = serverSocket.accept();
            System.out.println("连接到一个客户端");
            service.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        System.out.println("线程信息 id=" + Thread.currentThread().getId() + " 线程名=" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            //获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环的读取客户端发送的数据
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出客户端发送的数据
                    System.out.println("线程信息 id=" + Thread.currentThread().getId() + " 线程名=" + Thread.currentThread().getName());
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭与client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
