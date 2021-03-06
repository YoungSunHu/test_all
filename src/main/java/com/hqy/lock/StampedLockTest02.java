package com.hqy.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

public class StampedLockTest02 {
    private final static StampedLock lock = new StampedLock();

    private final static List<Long> DATA = new ArrayList<>();


    public static void main(String[] args) {

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable readTask = () -> {
            for (; ; ) {
                read();
            }
        };
        Runnable writeTask = () -> {
            for (; ; ) {
                write();
            }
        };
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(readTask);
        executorService.submit(writeTask);
    }


    private static void read() {
        long stamped = lock.tryOptimisticRead();
        if (lock.validate(stamped)) {
            try {
                stamped = lock.readLock();
                Optional.of(DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamped);
            }
        }
    }

    public static void write() {
        long stamp = -1;
        try {
            stamp = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
