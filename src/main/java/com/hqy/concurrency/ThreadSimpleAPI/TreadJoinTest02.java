package com.hqy.concurrency.ThreadSimpleAPI;

import java.util.stream.IntStream;

public class TreadJoinTest02 {


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            });
        });


    }
}
