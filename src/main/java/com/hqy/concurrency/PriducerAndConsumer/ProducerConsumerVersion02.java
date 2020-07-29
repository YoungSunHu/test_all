package com.hqy.concurrency.PriducerAndConsumer;

import java.util.stream.Stream;

public class ProducerConsumerVersion02 {

    private int i = 0;

    final private Object LOCK = new Object();

    private volatile boolean isProduced = false;


    public void produce() {

        synchronized (LOCK) {
            if (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P生产了一个数据:->" + i);
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consum() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("C消费了一个数据:->" + i);
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ProducerConsumerVersion02 producerConsumerVersion02 = new ProducerConsumerVersion02();

        Stream.of("P1", "P2").forEach(
                n -> {
                    new Thread() {
                        @Override
                        public void run() {
                            while (true) {
                                producerConsumerVersion02.produce();
                            }
                        }
                    }.start();
                }
        );

        Stream.of("C1", "C2").forEach(
                n -> {
                    new Thread() {
                        @Override
                        public void run() {
                            while (true) {
                                producerConsumerVersion02.consum();
                            }
                        }
                    }.start();
                }
        );


    }

}