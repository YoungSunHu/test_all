package com.hqy.concurrency.ThreadSimpleAPI;

public class SynchronizedRunnable implements Runnable {

    private int index = 1;

    private final static int MAX = 500;

    private final Object MONITOR = new Object();

    //将run方法设置为同步
    @Override
    public synchronized void run() {

        while (true) {
            if (index > MAX) {
                break;
            }

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "的号码是" + (index++));
        }

    }
}
