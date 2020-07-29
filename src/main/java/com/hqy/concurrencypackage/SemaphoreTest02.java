package com.hqy.concurrencypackage;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest02 {

    /**
     * connection pool
     * when get the avialble connection
     * 1.Get 1000MS then throw exception
     *
     * @param args
     */
    public static void main(String[] args) {

        final Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 2; i++) {
            new Thread() {
                @Override
                public void run() {
                    //super.run();
                    System.out.println(Thread.currentThread().getName() + " in");
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + " Get the semaphore");
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                    System.out.println(Thread.currentThread().getName() + " out");
                }
            }.start();
            while (true) {
                System.out.println("AP->" + semaphore.availablePermits());
                //queueLength记录block住的线程
                System.out.println("QL->" + semaphore.getQueueLength());
            }
        }

    }
}
