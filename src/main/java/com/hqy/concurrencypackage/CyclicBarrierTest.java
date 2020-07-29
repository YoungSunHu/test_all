package com.hqy.concurrencypackage;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

    public static void main(String[] args) {

        //parties指的是线程tread,第二个参数可以传入一个回调函数,所有线程执行完毕后将会调用
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("all of finished!");
            }
        });
        new Thread() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(1000);
                    TimeUnit.SECONDS.sleep(15);
                    cyclicBarrier.await();
                    System.out.println("T1 finished.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    //Thread.sleep(1000);
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("T2 finished.");
                    cyclicBarrier.await();
                    System.out.println("The other tread finished too.");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while (true){
            System.out.println(cyclicBarrier.getNumberWaiting());
            System.out.println(cyclicBarrier.getParties());
            System.out.println(cyclicBarrier.isBroken());
        }

    }
}
