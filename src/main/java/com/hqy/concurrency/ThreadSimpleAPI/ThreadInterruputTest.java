package com.hqy.concurrency.ThreadSimpleAPI;

public class ThreadInterruputTest {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {

   /*     Thread thread = new Thread() {

            @Override
            public void run() {
                super.run();
                while (true) {

                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            System.out.println("收到中断信号");
                            e.printStackTrace();
                        }
                    }

                }
            }
        };

        thread.start();
        Thread.sleep(100);
        System.out.println("线程是否中断:" + thread.isInterrupted());
        thread.interrupt();
        System.out.println("线程是否中断:" + thread.isInterrupted());*/

        Thread t1 = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {

                }
            }
        };

        t1.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main.interrupt();
                System.out.println("线程t2打断t1");
            }
        };
        t2.start();
        System.out.println("t1线程是否被打断" + t1.isInterrupted());
        t1.join();
        System.out.println("t1线程是否被打断" + t1.isInterrupted());

    }
}
