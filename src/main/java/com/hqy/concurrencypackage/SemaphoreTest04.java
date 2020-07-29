package com.hqy.concurrencypackage;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest04 {

    /**
     * connection pool
     * when get the avialble connection
     * 1.Get 1000MS then throw exception
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(2);

        new Thread() {
            @Override
            public void run() {
                try {
                    semaphore.drainPermits();
                    TimeUnit.SECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
