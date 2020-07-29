package com.hqy.concurrency.ThreadSimpleAPI;

import java.util.stream.IntStream;

public class TreadJoinTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            });
        });
        Thread thread02 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            });
        });
        try {
            //join
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.range(1, 1000).forEach(i -> {
            System.out.println(Thread.currentThread().getName() + "->" + i);
        });
    }
}
