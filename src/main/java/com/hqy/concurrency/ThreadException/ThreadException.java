package com.hqy.concurrency.ThreadException;

public class ThreadException {

    private static final int A = 10;
    private static final int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5_000);
                    int result = A / B;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        //在线程中设置未补获的异常处理器
        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);
            System.out.println(thread);
        });
    }

}
