package com.hqy.concurrency.LOCK;

public class SychronizedProblem {
    public static void main(String[] args) throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                SychronizedProblem.run();
            }
        }.start();
        Thread.sleep(1000);

        Thread t2 = new Thread() {
            @Override
            public void run() {
                SychronizedProblem.run();
            }
        };
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }


    private synchronized static void run() {
        System.out.println(Thread.currentThread().getName());
        while (true) {

        }
    }
}
