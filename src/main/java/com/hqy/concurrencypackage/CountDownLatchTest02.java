package com.hqy.concurrencypackage;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest02 {


    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(1);

        new Thread() {
            @Override
            public void run() {
                System.out.println("Do some initial working....");
                try {
                    Thread.sleep(10000);
                    latch.await();
                    System.out.println("Do other working....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("asyn prepare for some idea");
                try {
                    Thread.sleep(20000);
                    System.out.println("data prepare for done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放所有等待的线程,数量置0
                    latch.countDown();
                }
            }
        }.start();

        Thread.currentThread().join();

    }


}
