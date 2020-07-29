package com.hqy.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    private final static ReentrantReadWriteLock readWiriteLock = new ReentrantReadWriteLock();

    private final static Lock readLock = readWiriteLock.readLock();

    private final static Lock writeLock = readWiriteLock.writeLock();


    private final static ReentrantLock lock = new ReentrantLock(true);

    private final static List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(ReadWriteLockTest::read);
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(ReadWriteLockTest::read);
        thread2.start();

    }


    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            data.forEach(System.out::println);
            System.out.println(Thread.currentThread().getName() + "==========================");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }


}
