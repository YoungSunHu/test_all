package com.hqy.concurrency.LOCK;

import java.util.Optional;
import java.util.stream.Stream;

public class LOCKTest {
    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3", "T4").forEach(
                name -> {
                    new Thread(() -> {
                        try {
                            booleanLock.lock(10L);
                            Optional.of(Thread.currentThread().getName() + " have the lock").ifPresent(System.out::println);
                            work();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (LOCK.TimeOutException e) {
                            Optional.of(Thread.currentThread().getName() + " time out");
                        } finally {
                            booleanLock.unlock();
                        }
                    }, name).start();
                }
        );
    }

    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        Thread.sleep(40_000);
    }
}
