package com.hqy.concurrency.deadlock;

public class OtherService {

    private Object lock = new Object();

    private DeadLock deadLock;

    public void s1() {
        synchronized (lock) {
            System.out.println("s1方法执行");
        }
    }


    public void s2() {
        synchronized (lock) {
            System.out.println("s2方法执行");
            deadLock.m2();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
