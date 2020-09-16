package com.hqy.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample1 {


    public static void main(String[] args) {
        isShutDown();
    }

    /**
     * shudowm执行完毕之后不可再执行execut
     */
    private static void isShutDown() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        System.out.println(executorService.isShutdown());
        executorService.shutdown();
        System.out.println(executorService.isShutdown());

    }
}
