package com.hqy.condition;

import java.util.concurrent.TimeUnit;

public class ComBetweenThreads {

    private static int data = 0;

    private static boolean noUse = true;

    private final static Object MONITOR = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            for (; ; ) {
                try {
                    buildData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (; ; ) {
                try {
                    useData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static void buildData() throws InterruptedException {
        synchronized (MONITOR) {
            while (noUse) {
                try {
                    MONITOR.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            data++;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("P=>" + data);
            noUse = true;
            MONITOR.notifyAll();
        }
    }

    private static void useData() throws InterruptedException {
        synchronized (MONITOR) {
            while (!noUse) {
                MONITOR.wait();
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println("C=>" + data);
            noUse = false;
            MONITOR.notifyAll();
        }
    }
}
