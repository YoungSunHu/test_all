package com.hqy.concurrency.ThreadSimpleAPI;


public class ThreadWindowRunnableTest {

    public static void main(String[] args) {

        final ThreadWindowRunnable threadWindowRunnable = new ThreadWindowRunnable();

        Thread thread01 = new Thread(threadWindowRunnable, "一号窗口");
        Thread thread02 = new Thread(threadWindowRunnable, "二号窗口");
        Thread thread03 = new Thread(threadWindowRunnable, "三号窗口");

        thread01.start();
        thread02.start();
        thread03.start();

    }

}
