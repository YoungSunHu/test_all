package com.hqy.concurrency.PriducerAndConsumer;

public class ProducerConsumerVersion01 {

    private int i = 1;
    final private Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            System.out.println("p->" + (i++));
        }
    }


    private void consum() {
        synchronized (LOCK) {
            System.out.println("c->" + (i));
        }
    }

    public static void main(String[] args) {
        ProducerConsumerVersion01 producerConsumerVersion01 = new ProducerConsumerVersion01();

        new Thread("P") {
            @Override
            public void run() {
                while (true) {
                    producerConsumerVersion01.produce();
                }

            }
        }.start();

        new Thread("C") {
            @Override
            public void run() {
                while (true) {
                    producerConsumerVersion01.consum();
                }

            }
        };


    }


}
