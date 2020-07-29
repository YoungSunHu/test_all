package com.hqy.concurrency.PriducerAndConsumer;

import java.util.stream.Stream;

public class ProducerConsumerVersion03 {

    private int i = 0;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;


    public void produce() {

        synchronized (LOCK) {
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P生产了一个数据:->" + i);
            //唤醒这个锁相关的所有线程
            LOCK.notifyAll();
            isProduced = true;


        }
    }

    public void consum() {
        synchronized (LOCK) {

            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C消费了一个数据->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }


    public static void main(String[] args) {
        ProducerConsumerVersion03 producerConsumerVersion02 = new ProducerConsumerVersion03();

        Stream.of("P1", "P2").forEach(
                n -> {
                    new Thread(n) {
                        @Override
                        public void run() {
                            while (true) {
                                producerConsumerVersion02.produce();
                                try {
                                    Thread.sleep(6000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
        );

        Stream.of("C1", "C2").forEach(
                n -> {
                    new Thread(n) {
                        @Override
                        public void run() {
                            while (true) {
                                producerConsumerVersion02.consum();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                }
        );


    }

}