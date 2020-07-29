package com.hqy.concurrency.deadlock;

public class DeadLock {

    private OtherService otherService;
    private final Object lock = new Object();

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1() {
        synchronized (lock) {
            System.out.println("m1执行了!");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("m2执行了!");
        }
    }

}
