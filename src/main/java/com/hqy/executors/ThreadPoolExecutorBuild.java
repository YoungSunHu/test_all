package com.hqy.executors;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class ThreadPoolExecutorBuild {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = buildThreadPoolExecutor();

        int activeCount = 1;
        int queueSize = -1;

        while (true) {
            System.out.println(threadPoolExecutor.getActiveCount());
            System.out.println(threadPoolExecutor.getCorePoolSize());
            System.out.println(threadPoolExecutor.getQueue());
            System.out.println(threadPoolExecutor.getMaximumPoolSize());
        }
    }

    /**
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), r -> {
            Thread t = new Thread(r);
            return t;
        }, new ThreadPoolExecutor.AbortPolicy());


        System.out.println("The ThreadPoolExecutor create done.");
        threadPoolExecutor.execute(() -> sleepSeconds(100));
        return threadPoolExecutor;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
