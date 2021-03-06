package com.hqy.condition;

import sun.awt.SunHints;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConditionTest03 {

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition PRODUCER_COND = lock.newCondition();

    private final static Condition CONSUME_COND = lock.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private final static int MAX_CAPACITY = 100;

    public static void main(String[] args) throws InterruptedException {

        IntStream.range(0, 6).boxed().forEach(ConditionTest03::beginProduce);
        IntStream.range(0, 13).boxed().forEach(ConditionTest03::beginConsume);
/*        for (; ; ) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("===============================================");
            System.out.println("PRODUCE_COND.getWaitQueueLength->" + lock.getWaitQueueLength(PRODUCER_COND));
            System.out.println("CONSUME_COND.getWaitQueueLength->" + lock.getWaitQueueLength(CONSUME_COND));
            System.out.println("PRODUCER_COND.hasWaiters->" + lock.hasWaiters(PRODUCER_COND));
            System.out.println("CONSUME_COND.hasWaiters->" + lock.hasWaiters(CONSUME_COND));
        }*/
    }


    private static void beginProduce(int i) {
        new Thread(() -> {
            for (; ; ) {
                produce();
                sleep(2L);
            }
        }, "-P-" + i).start();
    }


    private static void beginConsume(int i) {
        new Thread(() -> {
            for (; ; ) {
                consume();
                sleep(2L);
            }
        }, "-P-" + i).start();
    }

    public static void produce() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.size() >= MAX_CAPACITY) {
                PRODUCER_COND.await();
            }
            long value = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "-P-" + value);
            System.out.println("PRODUCE_COND.getWaitQueueLength->" + lock.getWaitQueueLength(PRODUCER_COND));
            TIMESTAMP_POOL.addLast(value);
            CONSUME_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void consume() {
        try {
            lock.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                CONSUME_COND.await();
            }
            Long value = TIMESTAMP_POOL.removeFirst();
            System.out.println(Thread.currentThread().getName() + "-C-" + value);
            PRODUCER_COND.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void sleep(Long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
