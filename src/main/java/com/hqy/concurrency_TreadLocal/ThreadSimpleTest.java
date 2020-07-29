package com.hqy.concurrency_TreadLocal;

public class ThreadSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal() {
        @Override
        protected String initialValue() {
            return "Young";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("Young");
        Thread.sleep(1000);
        String value = threadLocal.get();
        System.out.println(value);
    }


}
