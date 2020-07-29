package com.hqy.concurrency.ThreadSimpleAPI;

import java.util.Optional;

public class Test01 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "HQY线程01:");

        t.start();

        System.out.println();

    }
}
