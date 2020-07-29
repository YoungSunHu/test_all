package com.hqy.concurrencypackage;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest03 {


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(0);
        latch.await();
        System.out.println("====================");
    }


}
