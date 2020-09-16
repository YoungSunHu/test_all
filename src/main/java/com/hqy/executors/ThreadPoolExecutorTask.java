package com.hqy.executors;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTask {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), r -> {
            Thread t = new Thread(r);
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

        //手动关闭线程池,不手动关闭,ThreadPoolExecutor的线程仍会活跃
        //shutdown非阻塞的,是等到线程完成工作后完毕
        threadPoolExecutor.shutdown();
        //awaitTermination 指定完成的等待事件,时间到了关闭线程
        threadPoolExecutor.awaitTermination(1, TimeUnit.MINUTES);
        //立即打断所有的线程,返回等待队列(ArrayBlockingQueue)的线程List<Runnable>
        List<Runnable> runnables = threadPoolExecutor.shutdownNow();
        System.out.println("=================================================================");
    }
}
