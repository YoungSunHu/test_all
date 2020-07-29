package com.hqy.concurrency02;

public class VilatileTest {

    private  static int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d] \n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();


        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                System.out.printf("Update the value to [%d] \n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();


    }
}
