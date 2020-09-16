package com.hqy.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorExample1 {
    public static void main(String[] args) {
        try {
            useCachedThreadPool();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Thread 执行完后生命周期结束
     * 线程不会将runnable提交到线程队列中
     * 等价于 Executors.newFixedThreadPool(1)
     *
     * @throws InterruptedException
     */
    private static void useSingleThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    /**
     * Executors.newFixedThreadPool(10)
     * 不会自动销毁线程,始终保持传入的线程数量
     * 需要手动的shutdown
     *
     * @throws InterruptedException
     */
    private static void useFixedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).boxed().forEach(
                i -> executorService.execute(
                        () -> {
                            try {
                                TimeUnit.SECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                )
        );
    }

    private static void useCachedThreadPool() throws InterruptedException {
        /**
         *  These pools will typically improve the performance
         *  of programs that execute many short-lived asynchronous tasks.
         */
        //Executors.newCachedThreadPool()中的阻塞队列只会放一个
        //提交几个创建几个线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        //显示提交任务前的活跃线程数(需要配强转成ThreadPoolExecutor)
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> System.out.println("==========================="));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(
                i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " [" + i + "]");
                    });
                }
        );

        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }
}
