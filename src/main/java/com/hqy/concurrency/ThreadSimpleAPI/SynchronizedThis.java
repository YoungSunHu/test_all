package com.hqy.concurrency.ThreadSimpleAPI;

import java.util.concurrent.locks.Lock;

public class SynchronizedThis {

    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();

        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();


    }

}


class ThisLock {

    private final Object LOCK = new Object();

    //synchronized方法中默认用的都是This锁
    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        //m2中使用的为自定义的LOCK锁
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
