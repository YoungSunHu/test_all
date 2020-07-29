package com.hqy.concurrencypackage;

import java.util.concurrent.CountDownLatch;

public class CyclicBarrierTest03 {

    static class MyCountDownLatch extends CountDownLatch {

        private final Runnable runnable;

        /**
         * Constructs a {@code CountDownLatch} initialized with the given count.
         *
         * @param count    the number of times {@link #countDown} must be invoked
         *                 before threads can pass through {@link #await}
         * @param runnable
         * @throws IllegalArgumentException if {@code count} is negative
         */
        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }

        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0) {
                this.runnable.run();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

    }
}
