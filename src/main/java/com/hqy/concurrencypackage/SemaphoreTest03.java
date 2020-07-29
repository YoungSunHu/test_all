package com.hqy.concurrencypackage;

import com.mysql.jdbc.TimeUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest03 {

    /**
     * connection pool
     * when get the avialble connection
     * 1.Get 1000MS then throw exception
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(2);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 finished ");
            }
        };
        t1.start();

        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    semaphore.acquireUninterruptibly();
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 finished ");
            }
        };
        t2.start();
        TimeUnit.MILLISECONDS.sleep(50);
        t2.interrupt();

    }
}
