package com.hqy.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * newWorkStealingPool线程池的实现用到了ForkJoinPool，用到了分而治之，递归计算的算法
 */
public class ExecutorExample2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        //Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);
        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(
                i -> (Callable<String>) () -> {
                    System.out.println("Thread " + Thread.currentThread().getName());
                    sleep(2);
                    return "Task-" + i;
                }
        ).collect(Collectors.toList());
        //同时启动任务,返回futures的List
        List<Future<String>> futures = executorService.invokeAll(callableList);
        futures.stream().map(
                future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }
        ).forEach(System.out::println);

    }


    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
