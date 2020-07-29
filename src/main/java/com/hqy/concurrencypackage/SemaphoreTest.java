package com.hqy.concurrencypackage;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {

        final SemaphoreLock lock = new SemaphoreLock();

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is Running ");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get the #SemaphroeLock ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " Released ");
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is Running ");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " get the #SemaphroeLock ");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " Released #SemaphroeLock ");
            }
        }.start();

    }

    static class SemaphoreLock {
        private final Semaphore semaphore = new Semaphore(1);

        public void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock() {
            semaphore.release();
        }
    }


}
