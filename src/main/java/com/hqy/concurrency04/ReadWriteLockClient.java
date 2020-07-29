package com.hqy.concurrency04;

public class ReadWriteLockClient {

    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new WirterWorker(sharedData,"abcde").start();
        new WirterWorker(sharedData,"ABCDE").start();
    }
}
