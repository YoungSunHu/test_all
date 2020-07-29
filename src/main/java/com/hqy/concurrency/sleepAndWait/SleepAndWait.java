package com.hqy.concurrency.sleepAndWait;

public class SleepAndWait {

    public static void main(String[] args) {

    }


    public void m1() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
