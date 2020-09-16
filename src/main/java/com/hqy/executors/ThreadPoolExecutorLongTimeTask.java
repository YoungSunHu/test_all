package com.hqy.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorLongTimeTask {
    public static void main(String[] args) throws InterruptedException {
        //需要在ThreadFactory中把新生成的线程设置为守护线程, threadPoolExecutor.awaitTermination()关闭的是本身线程
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), r -> {
            Thread t = new Thread(r);
            //设置为守护线程
            t.setDaemon(true);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());


        //提交20个线程
        IntStream.range(0, 20).boxed().forEach(
                i -> {
                    threadPoolExecutor.execute(
                            () -> {
                                try {
                                    TimeUnit.SECONDS.sleep(10);
                                    System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done.");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );

        threadPoolExecutor.awaitTermination(5,TimeUnit.SECONDS);
    }
}
