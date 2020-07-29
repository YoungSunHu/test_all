package com.hqy.concurrency_TreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 始终以当前线程作为key值
 * @param <T>
 */
public class ThreadLocalSimulatorTest<T> {

    //定义一个全局的TreadLocal
    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>() {
        @Override
        public String initvalue() {
            return "No Value";
        }
    };

    //seed
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("=========================================================");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
