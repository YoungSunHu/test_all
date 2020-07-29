package com.hqy.concurrency.ThreadSimpleAPI;

public class SynchronizedRunnableTest {

    final static SynchronizedRunnable synchronizedRunnable = new SynchronizedRunnable();

    public static void main(String[] args) {
        Thread thread01 = new Thread(synchronizedRunnable, "一号窗口");
        Thread thread02 = new Thread(synchronizedRunnable, "二号窗口");
        Thread thread03 = new Thread(synchronizedRunnable, "三号窗口");

        thread01.start();
        thread02.start();
        thread03.start();
    }
}
