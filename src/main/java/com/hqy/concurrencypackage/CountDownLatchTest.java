package com.hqy.concurrencypackage;

import java.util.Random;
import java.util.concurrent.*;

public class CountDownLatchTest {

    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        //
        int[] data = query();
        //
        for (int i = 0; i < data.length; i++) {
            executor.execute(new SimpleRunnable(data, i));
        }
        //executor.shutdown();
        latch.await();
        System.out.println("all of finish done.");
        //executor.awaitTermination(1, TimeUnit.HOURS);
        executor.shutdown();
    }

    static class SimpleRunnable implements Runnable {

        private final int[] data;

        private final int index;

        public SimpleRunnable(int[] data, int index) {
            this.data = data;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int value = data[index];
            if (value % 2 == 0) {
                data[index] = value * 2;
            } else {
                data[index] = value * 10;
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        }
    }

    private static int[] query() {
        return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }
}
