package com.hqy.concurrency02;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 1.所有的对象都会有一个WaitSet,用来存放调用了该对象使用wait方法之后进入block状态线程
 * 2.线程被notify之后不一定立即得到执行
 * 3.线程从waitset中唤醒的顺序不一定是FIFO
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    private static void work() {
        synchronized (LOCK) {
            try {
                System.out.println("Begin...");
                LOCK.wait();
                System.out.println("Threads are coming....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                work();
            }
        }.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //需要拿着锁去执行notifyAll
        synchronized (LOCK) {
            LOCK.notifyAll();
        }


        /*IntStream.rangeClosed(1, 10).forEach(i -> {
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    synchronized (LOCK) {
                        try {
                            Optional.of(Thread.currentThread().getName() + " will come to wait set").ifPresent(System.out::println);
                            //当一个对象BLOCK住时,线程就会就会放入这个对象的WaitSet中
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + " will leave set").ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        IntStream.rangeClosed(1, 10).forEach(i -> {
            synchronized (LOCK) {
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/


    }

}
