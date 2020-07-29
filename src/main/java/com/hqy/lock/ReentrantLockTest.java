package com.hqy.lock;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
/*        IntStream.range(0, 2).forEach(
                i -> new Thread() {
                    @Override
                    public void run() {
                        needLock();
                    }
                }.start()
        );*/

        Thread t1 = new Thread(() -> testUnInterruptibly());
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> testUnInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();
        System.out.println("==============");
    }


    public static void needLock() {
        try {
            //lock.lock();
            //未拿到lock时可被打断
            lock.lockInterruptibly();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void testUnInterruptibly() {
        try {
            lock.lock();
            Optional.of("The thread-" + Thread.currentThread().getName() + " get lock and will do working.").ifPresent(System.out::println);
            while (true) {

            }
        } finally {
            lock.unlock();
        }
    }

    public static void needLockBySync() {
        synchronized (ReentrantLockTest.class) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
